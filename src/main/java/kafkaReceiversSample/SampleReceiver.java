package kafkaReceiversSample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.apache.kafka.common.TopicPartition;

public class SampleReceiver {

	
	public static void main(String args[]){
		Properties props = new Properties();
		props.put("bootstrap.servers", "10.172.20.27:9092");
		props.put("group.id","VibushananR");
		props.put("key.deserializer",
				"org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer",
				"org.apache.kafka.common.serialization.StringDeserializer");
		props.put("auto.commit.offset", false);
		
		KafkaConsumer<String,String> consumer = new KafkaConsumer<String,String>(props);
	
		/*Map<MetricName, ? extends Metric> g = consumer.metrics();
		
		Set<?> ln = g.entrySet();
		System.out.println("---------Metrics-----------------");
		
		
		for(Object j : ln ){
			
			System.out.println("Name :"+j+" Value: "+g.get(j));
		}
		System.out.println("----------------------------------");
		consumer.subscribe(Collections.singletonList("test1"));
		*/
		
		Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<TopicPartition, OffsetAndMetadata>();
	
		int count = 0;
		
		try{
			
			while(true){
				
				ConsumerRecords<String, String> records = consumer.poll(10000);
				
				System.out.println(records.partitions());
				System.out.println(records.count());
								
				Iterator<ConsumerRecord<String, String>> itr = records.iterator();
				
				while(itr.hasNext()){
					ConsumerRecord<String, String> i = itr.next();
					
					System.out.println("Message  :"+count +i.toString());
					
					
					if(count % 5 ==0){
						System.out.println("Commititng");
						System.out.println(i.topic());
						System.out.println(i.partition());
						
						System.out.println(i.offset());
						currentOffsets.put(new TopicPartition(i.topic(),i.partition()), new OffsetAndMetadata(i.offset()));
						
						System.out.println(currentOffsets.values());
						consumer.commitSync(currentOffsets);
						count=0;
					}
					
					
					
				}
				
				count++;
				
				
				
			}
			
		}finally{
			consumer.close();
		}
		
	}
}
