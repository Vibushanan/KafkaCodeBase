package com.slk.kafka.KafkaSamples;

import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

public class EmployeePartitioner implements Partitioner{

	public void configure(Map<String, ?> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}

	public int partition(String topic, Object key, byte[] keyBytes, Object value,
			byte[] valueBytes, Cluster cluster) {
		
		List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
		
		System.out.println(partitions.size());
		
		int numpartitions = 0;
		
		numpartitions= partitions.size();
		
		if(key.equals("SLK")||key == "SLK"){
			return 0;
		}
			
		
		
		return 1;
	}
	

}
