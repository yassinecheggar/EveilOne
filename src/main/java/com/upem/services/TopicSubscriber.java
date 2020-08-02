package com.upem.services;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
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
	@Lazy
	UserRepository repouser;

	@Autowired
	@Lazy
	DeviceRepository repodevice;

	@Autowired
	@Lazy
	AntenneRepository antenneRepository;

	@Autowired
	DataRepository datarep;

	@Autowired
	@Lazy
	LocalisarionRepository localirep;
	private static int a1;
	private static int a2;
	private static int a3;
	private boolean first= true;

	public static Vector<String>dataString= new Vector<String>(); //this will contain received MQTT msg the data is added from the SimpleMQttCAllback

	private static String host = "tcp://mr2aqty0xnech1.messaging.solace.cloud:20966";
	private static String username = "solace-cloud-client";
	private static String password = "usa9boldpiapdjqr9b7gii14h";

	final CountDownLatch latch = new CountDownLatch(1);
	
	public TopicSubscriber() {
		// TODO Auto-generated constructor stub
	}
	
	
	//we use this function to allow us to get the data that we receive from MQTT callback and we add it to our database 
	//@Scheduled(fixedRate=10000)
	@Async
	public Future<String> addDataBase() throws InterruptedException {
		//System.out.println("addDataabse");
		//System.out.println(dataString.size());
		Vector<String>dataString1 = new Vector<String>(); 
		
			dataString1 =  (Vector<String>) dataString.clone();
			
			for (String s : dataString1) {

				getPayload(s);
				dataString.remove(s);

			}
			
		//	System.out.println("im done here");
		return new AsyncResult<String>("return value");
	}

	//this function will be executed every 2s and will check if new data is stored for the 3 antennas if so
	// it will  calculate the location using the given function and will store the result in the database 
	//@Scheduled(fixedRate=20000)
	public void getlocalisation() {
		
	//	System.out.println("getlocalisation");
		
		int size1;
		int size2;
		int size3;
		
		if(first == true) {

			a1=datarep.getallDataByAntenne(1);
			a2=datarep.getallDataByAntenne(2);
			a3=datarep.getallDataByAntenne(3);
			
			first = false;
			// System.out.println(" is first");
		}
		
		size1=datarep.getallDataByAntenne(1);
		size2=datarep.getallDataByAntenne(2);
		size3=datarep.getallDataByAntenne(3);
		
		if((a1<size1)&&(a2<size2)&&(a3<size3)) {
			
				
				a1=size1;
				a2= size2;
				a3= size3;
		

			try {

				DeviceData data1 = datarep.getDataByAntenne(1);
				Antenne n1= antenneRepository.getbyId(1);
				System.out.println(data1);
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
				System.out.println(e.getMessage());
			}

		}
		
	}

	//here we pasre the Mqtt msg to  create  an object and persist i
	@Async
	public  List<String> getPayload(String string){
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

	//this function will be lunched at the start after that spring beans are done the Spring beans
	//@PostConstruct
	@Async
	public void MQTTlistner() throws MqttException {
		MqttConnectOptions connOpts = new MqttConnectOptions();
		connOpts.setCleanSession(true);
		connOpts.setUserName(username);
		connOpts.setPassword(password.toCharArray());
		SimpleMqttCallBack E=null;
		MqttClient client= new MqttClient(host, MqttClient.generateClientId());
		client.setCallback( new SimpleMqttCallBack() );
		client.connect(connOpts);
		client.subscribe("EveiOne",0);//topic + Qos (0,1,2)


		System.out.println("Connected");

	}
	
	
}