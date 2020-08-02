package com.hadoop.mapreduce.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class WordcountReducer extends Reducer<Text, IntWritable, Text, IntWritable>
{
    
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException
    {
        //统计所有单词个数
        int count = 0;
        for (IntWritable value : values)
        {
            count += value.get();
            
        }
        
        //输出
        context.write(key, new IntWritable(count));
        
        
        
    }
    
}
