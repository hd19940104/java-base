package com.zixue.mapreduce.wordcount;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
public class WordCounrDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

		Configuration configuration = new Configuration();
		// 获取job对象信息
		Job job = Job.getInstance(configuration);
		// 设置jar位置
		job.setJarByClass(WordCounrDriver.class);
		// 设置mapper和reducer的class类
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReduce.class);
		// 设置输出mapper的数据类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		// 设置最终数据输出类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		  // 设置输出的outputFormat
       // job.setOutputFormatClass(SequenceFileOutputFormat.class);
		
		// 设置输入和输出数据路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		
	/*	//指定InputFormat 的类型
		job.setInputFormatClass(CombineTextInputFormat.class);
		//设置最大和最小切片的大小
		CombineTextInputFormat.setMaxInputSplitSize(job, 4);
		CombineTextInputFormat.setMinInputSplitSize(job, 1);*/
		
		
		/*//指定InputFormat 的类型
		job.setInputFormatClass(NLineInputFormat.class);
		NLineInputFormat.setNumLinesPerSplit(job, 2);*/
		
		
		// 9 指定需要使用combiner，以及用哪个类作为combiner的逻辑
		job.setCombinerClass(WordcountCombiner.class);

		
		// 8 设置reduce个数 分区  在驱动中配置加载分区
		job.setPartitionerClass(WordCountPartitioner.class);
		job.setNumReduceTasks(2);

		// 提交
		job.submit();
		
		boolean waitForCompletion = job.waitForCompletion(true);
		System.exit(waitForCompletion?0:1);
		//hadoop jar wordcount.jar com.zixue.mapreduce.wordcount.WordCounrDriver   /user/hadoop/wordcount/flow.txt /user/hadoop/wordcount/output1

	}
}
