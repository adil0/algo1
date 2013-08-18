import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class median {
	List<Integer> intArray=new ArrayList<Integer>();
	PriorityQueue<Integer> queue=new PriorityQueue<Integer>();
	int[] m; 
	
	public median(String fileName) throws NumberFormatException, IOException{
		int rem;
		int sum=0;
		readFile(fileName);
		m=new int[intArray.size()];
		
		
		for(int i=0;i<intArray.size();i++){
		calculateMedian(i);
		sum=sum+m[i];
		}
		System.out.println(sum);
		rem=sum%10000;
		System.out.println(rem);
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
				intArray.add(Integer.parseInt((line.trim())));				
		}
			in.close();		
	}// end readFile
	
	public void calculateMedian(int i){
		PriorityQueue<Integer> queueSec=new PriorityQueue<Integer>();
		int k;
		int t=i;
		queue.offer(intArray.get(i));
		queueSec.addAll(queue);
		
		if( (t+1) %2==0){
			k=(t+1)/2;
			while(t > k){
				queueSec.poll();
				t--;
			}
			m[i]=queueSec.peek();
		}else{
			k= (t+2)/2;
			while((t+1)>k){
				queueSec.poll();
				t--;
			}
			m[i]=queueSec.peek();
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		median m1= new median("/home/adil/Downloads/Median.txt");
	}

}
