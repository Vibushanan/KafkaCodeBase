package com.slk.kafka.KafkaSamples;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

public class EmployeeSerializer implements Serializer<Employee>{

	public void close() {
		// TODO Auto-generated method stub
		
	}

	public void configure(Map<String, ?> arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	public byte[] serialize(String topic, Employee data) {
		ByteBuffer buffer = null;
		
		byte[] serializedData;
		int dataSize;
		try {
		if(data == null){
			return null;
		}else{
			
			if(data.getEmployeeName()!=null){
				
					serializedData = data.getEmployeeName().getBytes("UTF-8");
					dataSize = serializedData.length;
			}else{
				serializedData = new byte[0];
				dataSize=0;
			}
			
			
		}
		
		 buffer = ByteBuffer.allocate(4 + 4 + dataSize);
		buffer.putInt(data.getEmployeeID());
		buffer.putInt(dataSize);
		buffer.put(serializedData);
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buffer.array();
	}

}
