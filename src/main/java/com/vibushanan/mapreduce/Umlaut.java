package com.vibushanan.mapreduce;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Umlaut  extends Configured implements Tool{

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new Umlaut(), args);
		System.exit(exitCode);

	}

	public int run(String[] arg0) throws Exception {
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
		Job job = new Job(conf, "WordCounter1");
		job.setJobName("WordCounter1");
		job.setJarByClass(Umlaut.class);
		
		FileInputFormat.addInputPath(job, new Path("/user/data/hadoopip/"));
		FileOutputFormat.setOutputPath(job, new Path("/user/data/hadoopop/frequentWords"));
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setMapperClass(WordMapper.class);
		job.setReducerClass(WordReducer.class);
		job.setNumReduceTasks(0);
		DistributedCache.addCacheFile(new Path
				("hdfs://10.41.220.72:/user/data/hadoopDic/dictionary").toUri(), conf);
		
		int returnValue = job.waitForCompletion(true) ? 0:1;
		System.out.println("job.isSuccessful " + job.isSuccessful());
		return returnValue;
		
	}

}
