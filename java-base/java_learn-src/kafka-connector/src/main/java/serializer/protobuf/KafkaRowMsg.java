package serializer.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import com.tencent.bling.KafkaMessageOuterClass;

import java.io.Serializable;

public class KafkaRowMsg implements Serializable {
    private static final long serialVersionUID = 468062760765055608L;
    public KafkaMessageOuterClass.KafkaMessage data;
    public KafkaRowMsg(KafkaMessageOuterClass.KafkaMessage m){
        super();
        data = m;
    }

    public KafkaRowMsg(byte[] m) {
        super();
        try {
            data = KafkaMessageOuterClass.KafkaMessage.parseFrom(m);
        } catch (InvalidProtocolBufferException e) {
            // TODO write to log
            e.printStackTrace();
        }
    }

    public byte[] encode(){
        return this.data.toByteArray();
    }
}
