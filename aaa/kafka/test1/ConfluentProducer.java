package aaa.kafka.test1;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 18-12-25 下午11:08
 */
public class ConfluentProducer {

    public static final String SCHEMA = "{\n" +
            "    \"type\": \"record\",\n" +
            "    \"name\": \"User\",\n" +
            "    \"fields\": [\n" +
            "        {\"name\": \"id\", \"type\": \"int\"},\n" +
            "        {\"name\": \"name\",  \"type\": \"string\"},\n" +
            "        {\"name\": \"age\", \"type\": \"int\"}\n" +
            "    ]\n" +
            "}";

    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        prop.put("bootstrap.servers", "127.0.0.1:9092");
        prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        prop.put("schema.registry.url", "http://127.0.0.1:8081");

        KafkaProducer<String, GenericRecord> producer = new KafkaProducer<>(prop);
        Random random = new Random();

        Schema schema = new Schema.Parser().parse(SCHEMA);
        for (int i = 100; i < 200; i++) {
            String name = "name" + i;
            int age = random.nextInt(50);

            GenericRecord user = new GenericData.Record(schema);
            user.put("id",i);
            user.put("name",name);
            user.put("age",age);

            ProducerRecord<String, GenericRecord> record = new ProducerRecord<>("test4", user);
            producer.send(record);
            TimeUnit.MILLISECONDS.sleep(200);
        }

        producer.close();
    }

}
