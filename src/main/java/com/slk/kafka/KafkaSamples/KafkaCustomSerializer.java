package com.slk.kafka.KafkaSamples;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/*
 * This is a custom Kafka producer with custom serializer and pratitiner
 * 
 */

public class KafkaCustomSerializer {

	public static void main(String[] args) {
		

		Properties props = new Properties();
		props.put("bootstrap.servers", "10.172.20.27:9092");
		props.put("retries", 0);
		props.put("group.id", "fromJava");
		
		
		props.put("partitioner.class",
				"com.slk.kafka.KafkaSamples.EmployeePartitioner");
		props.put("key.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer",
				"com.slk.kafka.KafkaSamples.EmployeeSerializer");
		
		
		Employee emp1 = new Employee(1, "AAAAAA");
		Employee emp2 = new Employee(2, "BBBBBB");
		
		
		Producer<String, Employee> producer = new KafkaProducer<String, Employee>(
				props);
		RecordMetadata metadata = null;
		RecordMetadata metadata1 = null;
		try {
		metadata = producer.send(
					new ProducerRecord<String, Employee>("test1", "SLK", emp1)).get();
			System.out.println("----1st message------------");
			System.out.println("Partition  " + metadata);
			System.out.println("Partition  " + metadata.partition());
			System.out.println("-------end---------");

			metadata1 = producer.send(
					new ProducerRecord<String, Employee>("test1", "TCS", emp2)).get();
					

			System.out.println("---2 Message-------------");
			System.out.println("Partition  " + metadata1.partition());
			System.out.println("----------------");

		} catch (InterruptedException e) {

			e.printStackTrace();

			System.out.println(e.getLocalizedMessage());
		} catch (ExecutionException e) {

			e.printStackTrace();

		}
		System.out.println("Finished");
		producer.close();

	}

}
