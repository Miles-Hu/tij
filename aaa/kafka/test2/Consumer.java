package aaa.kafka.test2;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 18-12-26 下午10:36
 */
public class Consumer {

    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.put("bootstrap.servers", "127.0.0.1:9092");
        prop.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        prop.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        prop.put("group.id", "g1");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(prop);

        consumer.subscribe(Collections.singleton("test5"));

        try{
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                for (ConsumerRecord r : records) {
                    System.out.printf("topic=%s, partition=%d, value=%s, offset=%d \n",
                            r.topic(),r.partition(),r.value(),r.offset());
                }
            }
        }finally {
            consumer.close();
        }

    }

}
