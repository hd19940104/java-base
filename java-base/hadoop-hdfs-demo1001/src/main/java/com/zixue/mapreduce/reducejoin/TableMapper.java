package com.zixue.mapreduce.reducejoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class TableMapper extends Mapper<LongWritable, Text, Text, TableBean>{
    Text k = new Text();
    TableBean tableBean = new TableBean();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取文件名
        FileSplit split = (FileSplit) context.getInputSplit();
        String name = split.getPath().getName();
        //判断1001	01	1
        String line = value.toString();
        if (name.startsWith("order")){
            String[] fields = line.split("\t");
            tableBean.setOrderId(fields[0]);
            tableBean.setPid(fields[1]);
            tableBean.setAmount(Integer.parseInt(fields[2]));
            tableBean.setPname("");
            tableBean.setFlag("0");
            k.set(fields[1]);
            context.write(k, tableBean);
        }else {//01	小米
            String[] fields = line.split("\t");
            tableBean.setOrderId("");
            tableBean.setPid(fields[0]);
            tableBean.setAmount(0);
            tableBean.setPname(fields[1]);
            tableBean.setFlag("1");
            k.set(fields[0]);
            context.write(k, tableBean);
        }
    }
}
