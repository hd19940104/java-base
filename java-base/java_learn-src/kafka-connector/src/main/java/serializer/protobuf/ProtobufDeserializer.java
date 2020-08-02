package serializer.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import com.tencent.bling.KafkaMessageOuterClass;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class ProtobufDeserializer implements Deserializer<KafkaMessageOuterClass.KafkaMessage> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // nothing to do
    }

    @Override
    public KafkaMessageOuterClass.KafkaMessage deserialize(String topic, byte[] data) {
        KafkaMessageOuterClass.KafkaMessage res = null;
        try {
            res = KafkaMessageOuterClass.KafkaMessage.parseFrom(data);
        } catch (InvalidProtocolBufferException e) {
            // TODO write to log
            e.printStackTrace();
        }
        return res;
    }


    @Override
    public void close() {
        // nothing to do
    }
}
