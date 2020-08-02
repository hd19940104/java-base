package com.hadoop.mapreduce.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * KEYIN:输入数据key文件行号
 * VALUEIN:每行输入数据
 * KEYOUT:输出数据key
 * VALUEOUT：输出的value类型
 * @author 一只会飞的小猴子
 *
 */
public class WordcountMapper extends Mapper<LongWritable, Text, Text, IntWritable>
{
    
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
    {
        //获取这一行数据
        String line = value.toString();
        //获取每一个单词
        String[] words = line.split(" ");
        for (String word : words)
        {
            //输出每一个单词
            context.write(new Text(word),new IntWritable(1));
        }
        
        
        
    }
    
}
