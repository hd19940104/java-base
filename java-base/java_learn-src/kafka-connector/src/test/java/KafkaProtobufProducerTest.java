import com.tencent.bling.KafkaMessageOuterClass;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;
// import serializer.protobuf.KafkaRowMsg;
import serializer.protobuf.ProtobufSerializer;

import java.util.Properties;

public class KafkaProtobufProducerTest {
    public static final String brokerList = "9.134.3.226:9092";
    public static final String topic = "bling-007";

    @Test
    public void MsgProducer() throws Exception{
        // System.out.println("hello kafka!");
        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ProtobufSerializer.class);

        //KafkaProducer<String, KafkaRowMsg> kafkaProducer = new KafkaProducer<>(props);
        //
        //int count = 0;
        //for (int i = 0; i <10 ; i++) {
        //    count++;
        //    KafkaMessageOuterClass.KafkaMessage km = KafkaMessageOuterClass.KafkaMessage.newBuilder()
        //            .setProtoHead(1)
        //            .setDataLen(2)
        //            .build();
        //    KafkaRowMsg krm = new KafkaRowMsg(km);
        //    // ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic,  0, "123","test "+count);
        //    ProducerRecord<String, KafkaRowMsg> record = new ProducerRecord<String, KafkaRowMsg>(topic, krm);
        //    kafkaProducer.send(record);
        //
        //    // ProducerRecord<String, String> record2 =  new ProducerRecord<>(topic, "hello, Kafka!!!!");
        //    // kafkaProducer.send(record2);
        //    // kafkaProducer.flush();
        //    System.out.println("发送：" + count);
        //    Thread.sleep(1000);    // exception
        //}

        KafkaProducer<String, KafkaMessageOuterClass.KafkaMessage> kafkaProducer = new KafkaProducer<>(props);

        int count = 0;
        for (int i = 0; i <10 ; i++) {
            count++;
            KafkaMessageOuterClass.KafkaMessage km = KafkaMessageOuterClass.KafkaMessage.newBuilder()
                    .setKind(i)
                    .setDesc("protobuf")
                    .build();
            // ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic,  0, "123","test "+count);
            ProducerRecord<String, KafkaMessageOuterClass.KafkaMessage> record = new ProducerRecord<>(topic, km);
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
