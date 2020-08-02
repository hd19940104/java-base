package com.zixue.mapreduce.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * 对每一个maptask的输出局部汇总（Combiner）
 * @author houdo
 *
 */
public class WordcountCombiner  extends Reducer<Text, IntWritable, Text, IntWritable>{
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Context context) throws IOException, InterruptedException {

		int count = 0;
		for(IntWritable v :values){
			count = v.get();
		}
		
		context.write(key, new IntWritable(count));
	}

}
