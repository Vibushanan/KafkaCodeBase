package customSerDeser;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;
import org.codehaus.jackson.map.ObjectMapper;

import com.slk.kafka.KafkaSamples.Employee;

public class EmployeeSerializer implements Serializer<Employee>{

	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub
		
	}

	public byte[] serialize(String topic, Employee data) {
		 byte[] retVal = null;
		 
		 ObjectMapper objectMapper = new ObjectMapper();
		 try {
		      retVal = objectMapper.writeValueAsString(data).getBytes();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    return retVal;
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
