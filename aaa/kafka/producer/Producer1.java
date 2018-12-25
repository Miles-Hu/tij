package aaa.kafka.producer;


import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.Test;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 18-12-24 下午11:00
 */
public class Producer1 {

    private static Properties kafkaProps = new Properties();
    private static KafkaProducer<String,String> producer;
    static {
        kafkaProps.put("bootstrap.servers", "127.0.0.1:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(kafkaProps);
    }


    @Test
    public void sync() {
        try {
            for (int i = 0; i < 1000; i++) {
                ProducerRecord<String, String> record = new ProducerRecord<>("test2", null, "Hello, Dear"+i);
                RecordMetadata recordMetadata = producer.send(record).get();
                System.out.println(recordMetadata.toString());
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void async() {
        ProducerRecord<String, String> record = new ProducerRecord<>("test2", null, "Hello, Dear7 ^_^");
        try {
            System.out.println(producer.send(record, new DemoProducerCallback()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class DemoProducerCallback implements Callback{
        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if (e != null) {
                e.printStackTrace();
            }
            System.out.println(recordMetadata.toString());
        }
    }

}
