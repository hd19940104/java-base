import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.util.Properties;



// bin/kafka-topics.sh --create --zookeeper 9.134.3.226:2181 --replication-factor 1 --partitions 3 --topic bling-007
// bin/kafka-console-consumer.sh --bootstrap-server 9.134.3.226:9092 --topic bling-007
// bin/kafka-console-producer.sh --broker-list 9.134.3.226:9092 --topic bling-007
public class KafkaProducerTest {
    public static final String brokerList = "9.134.3.226:9092";
    public static final String topic = "bling-007";

    @Test
    public void MsgProducer() throws Exception{
        // System.out.println("hello kafka!");
        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(props);

        int count = 0;
        for (int i = 0; i <10 ; i++) {
            count++;
            // ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic,  0, "123","test "+count);
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic,"test "+count);
            kafkaProducer.send(record);

            // ProducerRecord<String, String> record2 =  new ProducerRecord<>(topic, "hello, Kafka!!!!");
            // kafkaProducer.send(record2);
            // kafkaProducer.flush();
            System.out.println("发送：" + count);
            Thread.sleep(1000);    // exception
        }
        kafkaProducer.close();
    }
}
