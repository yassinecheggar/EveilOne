package com.upem.services;

import java.util.Vector;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.upem.repository.UserRepository;

@Component
public class SimpleMqttCallBack implements MqttCallback {

	private String msg;
	private TopicSubscriber topicSubscriber;

	public void connectionLost(Throwable throwable) {

		System.out.println("Connection to MQTT broker lost!");

	}


	public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
		msg = new String(mqttMessage.getPayload());
		System.out.println("Message received:\n\t"+ msg );
		TopicSubscriber.dataString.add(msg);
		System.out.println(msg);


	}

	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {


	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}



}