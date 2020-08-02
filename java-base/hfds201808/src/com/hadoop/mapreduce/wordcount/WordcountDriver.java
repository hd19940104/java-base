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
        //��ȡjob������Ϣ
        Job job = Job.getInstance(configuration);
        //����jarλ��
        job.setJarByClass(WordcountDriver.class);
        //����mapper��reducer��class��
       job.setMapperClass(WordcountMapper.class);
       job.setReducerClass(WordcountReducer.class);
        //�������mapper����������
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //�������������������
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //����������������·��
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //�ύ
        job.submit();
        
        
    }
    
    
}
