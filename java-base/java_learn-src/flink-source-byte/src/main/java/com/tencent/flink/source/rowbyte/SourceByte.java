package com.tencent.flink.source.rowbyte;

import org.apache.flink.api.common.serialization.AbstractDeserializationSchema;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.util.Properties;

public class SourceByte {
    private static final String brokerList = "9.134.3.226:9092";
    private static final String topic = "bling-007";

    public static void main(String[] args) {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //checkpoint配置
        env.enableCheckpointing(5000);
        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
        env.getCheckpointConfig().setMinPauseBetweenCheckpoints(500);
        env.getCheckpointConfig().setCheckpointTimeout(60000);
        env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
        env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        // env.getConfig().enableForceKryo();
        // env.registerTypeWithKryoSerializer(KafkaMessageOuterClass.class, ProtobufSerializer.class);

        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");   // 不定义会出问题
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);


        FlinkKafkaConsumer011<byte[]> kafkaConsumer = new FlinkKafkaConsumer011<>(topic,
                new AbstractDeserializationSchema<byte[]>() {
                    @Override
                    public byte[] deserialize(byte[] bytes) {
                        return bytes;
                    }
                },
                properties);
        kafkaConsumer.setStartFromLatest();
        DataStream<byte[]> commStream = env.addSource(kafkaConsumer);

        // commStream.process(new ByteProcessFunction()).setParallelism(1).print(); // Process 输出为字符串直接打印调试

        commStream.process(new ByteProcessFunction()).setParallelism(1).print();
        try {
            env.execute();     // 必须要这个，不然flink任务不会执行
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
