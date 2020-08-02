package serializer.protobuf;

import com.tencent.bling.KafkaMessageOuterClass;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class ProtobufSerializer implements Serializer<KafkaMessageOuterClass.KafkaMessage> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey){}

    @Override
    public byte[] serialize(String topic, KafkaMessageOuterClass.KafkaMessage data) {
        return data.toByteArray();
    }

    @Override
    public void close() {}

}
