package com.vibushanan.mapreduce;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.log4j.Logger;

public class WordMapper extends Mapper<LongWritable, Text, Text, Text>{
	 private Logger logger = Logger.getLogger(WordMapper.class);

	 private Configuration i;
	 Map<String,String> dictionary = new HashMap<String,String>();
	 @Override
	 protected void setup(Context context) throws IOException, InterruptedException {
		 try{
			 
			  i = context.getConfiguration();
			 Path[] dictionary  = DistributedCache.getLocalCacheFiles(context.getConfiguration());
			 logger.info("dictionary  "+dictionary.length);
			 
				 readFile(dictionary[0]);
			 		 
		 }catch(Exception ex) {
			 ex.printStackTrace();
			 System.err.println("Exception in mapper setup: " + ex.getLocalizedMessage());
			 System.err.println("Exception in mapper setup: " + ex.getMessage());
		 }

	 }
	
	
	
	@Override
	protected void map(LongWritable key, Text value,
			Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		
		String[] file1 = line.split("\\t");
		
			String message = file1[3];
			
			String[] words = message.split(" ");
			 //Path[] dictionaryp  = DistributedCache.getLocalCacheFiles(context.getConfiguration("mapred.cache.files"));
			for(String word : words){
			
				char[] wordCharArray = word.toCharArray();
				
				context.write(new Text(""+i), new Text(i.get("mapred.cache.files")));
				/*for(int i = 0;i<wordCharArray.length;i++){
					
					;*/
					
					
					/*if(isUmlaut(wordCharArray[i])){
						
						StringBuilder normalWord = new StringBuilder(word.substring(0, i-1));
						
						normalWord.append(dictionary.get(new StringBuilder().append(wordCharArray[i]).toString()));
						
						normalWord.append(word.substring(i+1));
						System.out.println("Emitting............");
						context.write(new Text(word), new Text(normalWord.toString()));
						continue;
					}*/
				//}
			}
			
		
		
		
	}	
		
	
	
	
	private boolean isUmlaut(char c){
		
		if(dictionary.containsKey(new StringBuilder().append(c).toString())){
			return true;
		}
		return false;
	}
	
	private void readFile(Path filePath) {
		try{
			 System.err.println("reading "+filePath);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath.toString()));
			String umlauts  = null;
			while((umlauts  = bufferedReader.readLine()) != null) {
				 System.err.println("umlauts "+umlauts);
				String[] umlautChars = umlauts.split(";");
				System.err.println("adding  "+umlautChars[0]);
				dictionary.put(umlautChars[0], umlautChars[1]);
			}

		}catch(IOException ex) {
			ex.printStackTrace();
		}

	
	}

}
