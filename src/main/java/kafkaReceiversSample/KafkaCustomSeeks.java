package kafkaReceiversSample;

import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;

import kafka.consumer.SimpleConsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

//Kafka Consumer with Seeks

public class KafkaCustomSeeks {



	public static void main(String[] args) {
	
		
		Properties props = new Properties();
		props.put("bootstrap.servers", "10.41.220.72:9092");
		props.put("group.id","Vibushanan1");
		props.put("key.deserializer",
				"org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer",
				"org.apache.kafka.common.serialization.StringDeserializer");

		KafkaConsumer<String,String> consumer = new KafkaConsumer<String,String>(props);
			
		TopicPartition tp = new TopicPartition("test", 0);
		consumer.assign(Collections.singletonList(tp));
		
		//consumer.seek(tp, 0L);
		
		consumer.seekToBeginning(tp);
		
		// consumer.seekToEnd(tp);
		
		while(true){
			
			
			ConsumerRecords<String, String> records = consumer.poll(10000);
			
			Iterator<ConsumerRecord<String, String>> itr = records.iterator();
			
			while(itr.hasNext()){
						
				ConsumerRecord<String, String> i = itr.next();
				
			
						
				System.out.println("Received Message  :"+i.toString());
			}
		}
		
	}


}
