package com.upem.services;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class testzbi {

	private static String host = "tcp://mr2aqty0xnech1.messaging.solace.cloud:20966";
	private static String username = "solace-cloud-client";
	private static String password = "usa9boldpiapdjqr9b7gii14h";
	public void zbi() throws MqttException {


		MqttClient client = new MqttClient( 
				host, //URI 
				MqttClient.generateClientId(), //ClientId 
				new MemoryPersistence()); //Persistence


		MqttConnectOptions options = new MqttConnectOptions();
		options.setUserName("username");
		options.setPassword("password".toCharArray());
		client.connect(options);



	}

	public static void main(String[] args) throws MqttException {
		MqttConnectOptions connOpts = new MqttConnectOptions();
		connOpts.setCleanSession(true);
		connOpts.setUserName(username);
		connOpts.setPassword(password.toCharArray());

		MqttClient client=new MqttClient(host, MqttClient.generateClientId());
		client.setCallback( new SimpleMqttCallBack() );
		client.connect(connOpts);
		client.subscribe("T/GettingStarted/pubsub",0);



	}

}
