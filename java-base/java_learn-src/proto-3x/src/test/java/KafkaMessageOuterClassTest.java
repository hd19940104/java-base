import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.tencent.bling.KafkaMessageOuterClass;
import org.junit.Test;

public class KafkaMessageOuterClassTest {

    @Test
    public void EncodeDecodeMsg() {
        ByteString data = ByteString.copyFromUtf8("hello kafka");
        KafkaMessageOuterClass.KafkaMessage m = KafkaMessageOuterClass.KafkaMessage.newBuilder()
                .setKind(1)
                .setDesc("hello")
                .setData(data)
                .build();
        byte[] ms = m.toByteArray();

        try {
            KafkaMessageOuterClass.KafkaMessage p = KafkaMessageOuterClass.KafkaMessage.parseFrom(ms);
            System.out.println("proto kind = " + p.getKind() + " des = " + p.getDesc());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }


    }
}
