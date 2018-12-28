package aaa.kafka.test2;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 18-12-26 下午10:33
 */
public class Producer {

    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.put("bootstrap.servers", "127.0.0.1:9092");
        prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(prop);

        try{
            for (int i = 10; i < 20; i++) {
                ProducerRecord<String, String> record = new ProducerRecord<>("test5", i + "Hello Miiles ^_^");
                producer.send(record);
            }
        }finally {
            producer.close();
        }
    }

}
