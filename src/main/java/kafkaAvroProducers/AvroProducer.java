/*package kafkaAvroProducers;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;


public class AvroProducer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
		          io.confluent.kafka.serializers.KafkaAvroSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
		          io.confluent.kafka.serializers.KafkaAvroSerializer.class);
		props.put("schema.registry.url", "http://localhost:8081");
		KafkaProducer producer = new KafkaProducer(props);

		String key = "key1";
		String userSchema = "{\"type\":\"record\"," +
		                    "\"name\":\"myrecord\"," +
		                    "\"fields\":[{\"name\":\"f1\",\"type\":\"string\"}]}";
		Schema.Parser parser = new Schema.Parser();
		Schema schema = parser.parse(userSchema);
		GenericRecord avroRecord = new GenericData.Record(schema);
		avroRecord.put("f1", "value1");

		record = new ProducerRecord<Object, Object>("topic1", key, avroRecord);
		try {
		  producer.send(record);
		} catch(SerializationException e) {
		  // may need to do something with it
		}
	}

}
*/