package aaa.kafka.test1;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 18-12-25 下午11:21
 */
public class ConfluentConsumer {

    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.put("bootstrap.servers", "127.0.0.1:9092");
        prop.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        prop.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer");
        prop.put("group.id", "g2");
        prop.put("schema.registry.url", "http://127.0.0.1:8081");

        KafkaConsumer<String, GenericRecord> consumer = new KafkaConsumer<>(prop);
        consumer.subscribe(Collections.singleton("test4"));
        try{
            while (true) {
                ConsumerRecords<String, GenericRecord> records = consumer.poll(1000);
                for (ConsumerRecord<String, GenericRecord> r : records) {
                    GenericRecord user = r.value();
                    System.out.printf("id=%d, name=%s, age=%d, offset=%d \n",
                            user.get("id"),user.get("name"),user.get("age"),r.offset());
                }
            }
        }finally {
            consumer.close();
        }

    }

}
