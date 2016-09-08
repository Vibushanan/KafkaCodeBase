package com.vibushanan.mapreduce;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class WordReducer extends Reducer<Text, Text, Text, Text> {
	@Override
	protected void reduce(Text key, Iterable<Text> values,
			Context context)
			throws IOException, InterruptedException {
	
		int sum = 0;
		Iterator<Text> valuesIt = values.iterator();
		StringBuilder str = new StringBuilder();
		while(valuesIt.hasNext()){
			str.append(valuesIt.next().toString());
		}
		context.write(key, new Text(str.toString()));
	}	
}
