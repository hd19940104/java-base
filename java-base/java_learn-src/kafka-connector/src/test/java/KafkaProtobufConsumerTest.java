import com.tencent.bling.KafkaMessageOuterClass;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Test;
import serializer.protobuf.ProtobufDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaProtobufConsumerTest {
    public static final String brokerList = "9.134.3.226:9092";
    public static final String topic = "bling-007";

    @Test
    public void MsgConsume() {
        // System.out.println("hello consumer!");
        Properties kafkaProps = new Properties();
        kafkaProps.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        kafkaProps.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");   // 不定义会出问题
        kafkaProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        kafkaProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ProtobufDeserializer.class);

        // KafkaConsumer<String, String> consumer = new KafkaConsumer<String,String>(props);
        // consumer.subscribe(Arrays.asList(topic1, topic2));
        KafkaConsumer<String, KafkaMessageOuterClass.KafkaMessage> consumer = new KafkaConsumer<>(kafkaProps);
        consumer.subscribe(Collections.singletonList(topic));


        while (true){
            ConsumerRecords<String, KafkaMessageOuterClass.KafkaMessage> records =
                    consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, KafkaMessageOuterClass.KafkaMessage> record : records) {
                System.out.println(record.value().getKind());
            }

        }
    }
}
