package aaa.kafka.consumer;

import org.apache.kafka.clients.consumer.CommitFailedException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;

import java.util.Collections;
import java.util.Properties;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 18-12-25 下午1:23
 */
public class Consumer1 {

    private static Properties props = new Properties();
    private static KafkaConsumer<String,String> consumer;
    static {
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "miles");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("enable.auto.commit", "false");
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singleton("test2"));
    }

    @Test
    public void test1() {
        try{
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                for (ConsumerRecord<String, String> r : records) {
                    System.out.printf("topic = %s, partition = %s, offset = %d, key = %s, value = %s \n",
                                        r.topic(),r.partition(),r.offset(),r.key(),r.value());
                }
                try {
                    consumer.commitSync();
                } catch (CommitFailedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            consumer.close();
        }
    }

}
