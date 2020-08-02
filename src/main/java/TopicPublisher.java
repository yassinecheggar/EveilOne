
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


public class TopicPublisher {
    
    public void run(String... args) {
        System.out.println("TopicPublisher initializing...");

        String host = args[0];
     

        try {
            // Create an Mqtt client
            MqttClient mqttClient = new MqttClient(host, MqttClient.generateClientId());
           
            
            // Connect the client
            System.out.println("Connecting to Solace messaging at " + host);
           
            System.out.println("Connected");

            // Create a Mqtt message
            String content = "Hello world from MQTT!";
            MqttMessage message = new MqttMessage(content.getBytes());
            // Set the QoS on the Messages - 
            // Here we are using QoS of 0 (equivalent to Direct Messaging in Solace)
            message.setQos(0);
            
            System.out.println("Publishing message: " + content);
            
            // Publish the message
            mqttClient.publish("test2", message);
            
            // Disconnect the client
            mqttClient.disconnect();
            
            System.out.println("Message published. Exiting");

            
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }

    public static void main(String[] args) {
        
        new TopicPublisher().run("tcp://broker.mqttdashboard.com:1883");
    }
}