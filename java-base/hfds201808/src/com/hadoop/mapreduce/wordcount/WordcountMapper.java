package com.hadoop.mapreduce.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * KEYIN:��������key�ļ��к�
 * VALUEIN:ÿ����������
 * KEYOUT:�������key
 * VALUEOUT�������value����
 * @author һֻ��ɵ�С����
 *
 */
public class WordcountMapper extends Mapper<LongWritable, Text, Text, IntWritable>
{
    
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
    {
        //��ȡ��һ������
        String line = value.toString();
        //��ȡÿһ������
        String[] words = line.split(" ");
        for (String word : words)
        {
            //���ÿһ������
            context.write(new Text(word),new IntWritable(1));
        }
        
        
        
    }
    
}
