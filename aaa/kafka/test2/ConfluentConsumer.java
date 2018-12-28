package aaa.kafka.test2;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 18-12-26 下午10:51
 */
public class ConfluentConsumer {

    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.put("bootstrap.servers", "127.0.0.1:9092");
        prop.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        prop.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer");
        prop.put("group.id", "g1");
        prop.put("schema.registry.url", "http://127.0.0.1:8081");
        prop.put("enable.auto.commit", "false");

        KafkaConsumer<String, GenericRecord> consumer = new KafkaConsumer<>(prop);
        consumer.subscribe(Collections.singleton("test6"));

        try{
            while (true) {
                ConsumerRecords<String, GenericRecord> records = consumer.poll(1000);
                for (ConsumerRecord<String, GenericRecord> r : records) {
                    GenericRecord v = r.value();
                    System.out.printf("name=%s, id=%d, age=%d, topic=%s, offset=%d \n",
                            v.get("name"),v.get("id"),v.get("age"),r.topic(),r.offset());
                }
                consumer.commitAsync();
            }
        }finally {
            consumer.commitSync();
            consumer.close();

        }
    }

}
