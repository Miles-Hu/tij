package aaa.kafka.test2;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 18-12-26 下午10:42
 */
public class ConfluentProducer {

    public static final String SCHEMA = "{\"type\": \"record\", \"name\": \"User\", " +
            "\"fields\": [{\"name\": \"id\", \"type\": \"int\"}, " +
            "{\"name\": \"name\",  \"type\": \"string\"}, {\"name\": \"age\", \"type\": \"int\"}]}";

    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.put("bootstrap.servers", "127.0.0.1:9092");
        prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        prop.put("schema.registry.url", "http://127.0.0.1:8081");

        KafkaProducer<String, GenericRecord> producer = new KafkaProducer<>(prop);

        Schema schema = new Schema.Parser().parse(SCHEMA);

        Random random = new Random();
        try{
            for (int i = 10; i < 20; i++) {
                String name = "name" + i;
                int age = random.nextInt(50);
                GenericRecord user = new GenericData.Record(schema);
                user.put("id",i);
                user.put("name",name);
                user.put("age",age);

                ProducerRecord<String, GenericRecord> record = new ProducerRecord<>("test6", user);
                producer.send(record);
            }
        }finally {
            producer.close();
        }

    }

}
