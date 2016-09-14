package com.slk.kafka.KafkaSamples;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;



public class JavaKafkaProducers {

	public static void main(String[] args) {
		Properties props = new Properties();

		props.put("bootstrap.servers", "10.41.220.72:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 0);
		props.put("metadata.fetch.timeout.ms", 30000);
		props.put("key.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");

		props.put("value.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<String, String>(
				props);
		producer.send(
				new ProducerRecord<String, String>("test", "Keysss "
					, "This is from eclipse "));
	/*	for (int i = 0; i < 10; i++) {
			System.out.println("Sending the message " + i);

			RecordMetadata metadata = null;

			try {

				metadata = producer.send(
						new ProducerRecord<String, String>("test", "Key "
								+ Integer.toString(i), "Value "
								+ Integer.toString(i))).get();
			} catch (InterruptedException e) {

				e.printStackTrace();

				System.out.println(e.getLocalizedMessage());
			} catch (ExecutionException e) {

				e.printStackTrace();
			}

			System.out.println("Stats");
			System.out.println("----------------");
			System.out.println(metadata.topic());
			System.out.println(metadata.partition());
			System.out.println("----------------");
		}*/

		System.out.println("Finished");
		producer.close();
	}

	
	private class DemoProducerCallback implements Callback{

		public void onCompletion(RecordMetadata metadata, Exception exception) {
			if(exception!= null){
				exception.printStackTrace();
			}
			
		}
		
	}
}

