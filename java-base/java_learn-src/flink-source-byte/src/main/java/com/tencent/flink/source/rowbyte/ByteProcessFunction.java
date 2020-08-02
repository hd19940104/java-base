package com.tencent.flink.source.rowbyte;

import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;


/**
 * The implementation of the ProcessFunction that maintains the count and timeouts
 */

public class ByteProcessFunction extends ProcessFunction<byte[], String> {

    /** The state that is maintained by this process function */
    /** process function维持的状态 */
    private ValueState<ByteProcessState> state;

    @Override
    public void open(Configuration parameters) throws Exception {
        // state = getRuntimeContext().getState(new ValueStateDescriptor<>("myState", ByteProcessState.class));
        System.out.println("ByteProcessFunction open call");
    }

    @Override
    public void processElement(byte[] value, Context ctx, Collector<String> out) throws Exception {
        out.collect(new String(value));
        // retrieve the current count
        // 获取当前的count
        //ByteProcessState current = state.value();
        //if (current == null) {
        //    current = new ByteProcessState();
        //    current.key = value.f0;
        //}
        //
        //// update the state's count
        //// 更新 state 的 count
        //current.count++;
        //
        //// set the state's timestamp to the record's assigned event time timestamp
        //// 将state的时间戳设置为记录的分配事件时间戳
        //current.lastModified = ctx.timestamp();
        //
        //// write the state back
        //// 将状态写回
        //state.update(current);
        //
        //// schedule the next timer 60 seconds from the current event time
        //// 从当前事件时间开始计划下一个60秒的定时器
        //ctx.timerService().registerEventTimeTimer(current.lastModified + 60000);
    }

    @Override
    public void onTimer(long timestamp, OnTimerContext ctx, Collector<String> out) throws Exception {
        System.out.println("ByteProcessFunction onTimer call");
        // get the state for the key that scheduled the timer
        //获取计划定时器的key的状态
        //ByteProcessState result = state.value();
        //
        //// 检查是否是过时的定时器或最新的定时器
        //if (timestamp == result.lastModified + 60000) {
        //    // emit the state on timeout
        //    out.collect(new Tuple2<String, Long>(result.key, result.count));
        //}
    }
}
