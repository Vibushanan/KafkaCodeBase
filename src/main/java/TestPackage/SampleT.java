package TestPackage;

import java.util.HashMap;
import java.util.Map;

public class SampleT {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
String str = "srhus";
String st = "U123	   E345	123456789	   Last year he visited Århus";



System.out.println(st.split("\\t").length);
/*char[] charArray = str.toCharArray();

Map<String,String> un = new HashMap<String,String>();
un.put("Å", "As");
un.put("Ø", "Oa");
for(char c : charArray){
	
	if (un.containsKey(new StringBuilder().append(c).toString())){
		System.out.println("XXXXXXXXXXXXX");
	}
}*/

	}

}
