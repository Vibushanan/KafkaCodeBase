package customSerDeser;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.codehaus.jackson.map.ObjectMapper;

import com.slk.kafka.KafkaSamples.Employee;

public class EmployeeDeserializer implements Deserializer<Employee>{

	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub
		
	}

	public Employee deserialize(String topic, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
	    Employee user = null;
	    try {
	      user = mapper.readValue(data, Employee.class);
	    } catch (Exception e) {

	      e.printStackTrace();
	    }
	    return user;
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}

}
