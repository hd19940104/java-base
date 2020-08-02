package com.hadoop.mapreduce.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordcountDriver
{
    
    public static void main(String[] args) throws Exception
    {
        
        Configuration configuration = new Configuration();
        //获取job对象信息
        Job job = Job.getInstance(configuration);
        //设置jar位置
        job.setJarByClass(WordcountDriver.class);
        //设置mapper和reducer的class类
       job.setMapperClass(WordcountMapper.class);
       job.setReducerClass(WordcountReducer.class);
        //设置输出mapper的数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //设置最终数据输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //设置输入和输出数据路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //提交
        job.submit();
        
        
    }
    
    
}
