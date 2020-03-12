package com.upem.services;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

import javax.annotation.PostConstruct;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.upem.models.Antenne;
import com.upem.models.DeviceData;
import com.upem.models.localisation;
import com.upem.repository.AntenneRepository;
import com.upem.repository.DataRepository;
import com.upem.repository.DeviceRepository;
import com.upem.repository.LocalisarionRepository;
import com.upem.repository.UserRepository;

@Component
public class TopicSubscriber {

	@Autowired
	UserRepository repouser;

	@Autowired
	DeviceRepository repodevice;

	@Autowired
	AntenneRepository antenneRepository;

	@Autowired
	DataRepository datarep;
	
	@Autowired
	LocalisarionRepository localirep;

	public static Vector<String>dataString= new Vector<String>();

	private static String host = "tcp://mr2aqty0xnech1.messaging.solace.cloud:20966";
	private static String username = "solace-cloud-client";
	private static String password = "usa9boldpiapdjqr9b7gii14h";


	@Scheduled(fixedRate=10000)
	public void addDataBase() {

		System.out.println(dataString.size());
		if(dataString.size()>=3 ) {
			for (String s : dataString) {

				getPayload(s);

			}
			dataString.clear(); 
		} 
	}

	@Scheduled(fixedRate=60000)
	public void getlocalisation() {

		try {
			
		
		DeviceData data1 = datarep.getDataByAntenne(1);
		Antenne n1= antenneRepository.getbyId(1);

		DeviceData data2 = datarep.getDataByAntenne(2);
		Antenne n2= antenneRepository.getbyId(2);

		DeviceData data3 = datarep.getDataByAntenne(3);
		Antenne n3= antenneRepository.getbyId(3);

		Integer rss1 = Integer.parseInt(data1.getRssi());
		Integer rss2= Integer.parseInt(data2.getRssi());
		Integer rss3 = Integer.parseInt(data3.getRssi());

		Double longi = ( n1.getLeng()*(rss1) + n2.getLeng()*(rss2) + n3.getLeng()*(rss3) ) / (rss1 + rss2 + rss3);
		Double alt = (n1.getAlt()*(rss1) + n2.getAlt()*(rss2) + n3.getAlt()*(rss3) ) / (rss1 + rss2 + rss3);
		
		
		System.out.println(longi);
		System.out.println(alt);
		localisation loc = new localisation();
		loc.setAltitude(alt);
		loc.setLongitude(longi);
		loc.setDeviceloca(repodevice.getbyIdMAc("azerty"));
		loc.setDate(new Timestamp(System.currentTimeMillis()));
		localirep.save(loc);
		}catch (Exception e) {
			System.out.println("no  data yet");
		}
	}

	public synchronized List<String> getPayload(String string){
		DeviceData data =new DeviceData();
		List<String> list = Arrays.asList(string.split(";"));
		try {


			if(list!=null) {

				if(list.size()>4) {

					data.setDatadevice(repodevice.getbyIdMAc(list.get(0))); 
					data.setTemp(list.get(1));
					data.setHum(list.get(2));
					data.setAntenne(antenneRepository.getbyId(Integer.parseInt(list.get(3))));
					data.setRssi(list.get(4));
					data.setDate(new Timestamp(System.currentTimeMillis()));
					System.out.println(data);
					datarep.save(data);
				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return list ;

	}


	@PostConstruct
	@Async
	public void daddd() throws MqttException {
		MqttConnectOptions connOpts = new MqttConnectOptions();
		connOpts.setCleanSession(true);
		connOpts.setUserName(username);
		connOpts.setPassword(password.toCharArray());
		SimpleMqttCallBack E=null;
		MqttClient client= new MqttClient(host, MqttClient.generateClientId());
		client.setCallback( new SimpleMqttCallBack() );
		client.connect(connOpts);
		client.subscribe("EveiOne",0);


		System.out.println("done");

	}


}