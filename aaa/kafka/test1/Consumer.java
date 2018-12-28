package aaa.kafka.test1;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.junit.Test;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 18-12-25 下午10:41
 */
public class Consumer {

    private static Properties prop = new Properties();
    private static KafkaConsumer<String, String> consumer;

    public static void main(String[] args) {
        prop = new Properties();
        prop.put("bootstrap.servers", "127.0.0.1:9092");
        prop.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        prop.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        prop.put("group.id", "g1");

        consumer = new KafkaConsumer<>(prop);
        consumer.subscribe(Collections.singleton("test3"));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                for (ConsumerRecord<String, String> r : records) {
                    System.out.printf("topic=%s, partition=%d, key=%s, value=%s, offset=%d \n",
                            r.topic(), r.partition(), r.key(), r.value(), r.offset());
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            TimeUnit.SECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        consumer.wakeup();
                    }
                }).start();
            }
        } catch (WakeupException e) {
            e.printStackTrace();
        }finally {
            consumer.close();
        }
    }
}
