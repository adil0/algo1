import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class twoSum {
	List <Double> doubleArray=new ArrayList<Double>();
	Set <Double> set;
	int count=0;
	
	public twoSum(String fileName) throws NumberFormatException, IOException{
		readFile(fileName);
		
		set=new HashSet<Double>(doubleArray);
		int t=-10000;
		while(t<=10000){
			for(Double s:set){
				if(set.contains(t-s)){
					count++;
					break;
				}
			}//end for
			System.out.println("t:"+t);
			t++;
		}
		
	System.out.println("count:"+count);
	}// end constructor
	
	public void readFile(String fileName) throws IOException, NumberFormatException{
		FileInputStream in = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
						
			String line = null;
			while (true)
			{
				line = br.readLine();
				if (line == null)
				{
					break;
				}
				doubleArray.add(Double.parseDouble((line.trim())));				
		}
			in.close();		
	}// end readFile

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		twoSum s1= new twoSum("/home/adil/Downloads/algo1_programming_prob_2sum.txt");

	}// end main

}// end class
