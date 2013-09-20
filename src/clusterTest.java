import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class clusterTest {
	 int n,edgeCount;
	 long mc=0;
	 int reqEdge=4;
	 PriorityQueue<Integer> q = new PriorityQueue<Integer>();
	 
	public clusterTest(String fileName) throws NumberFormatException,IOException{
		readFile(fileName);
//		System.out.println("n="+n);		
		edgeCount=n;		
		
		while(edgeCount > reqEdge){
			System.out.println(q.peek());
			q.poll();
			edgeCount--;
		}
		System.out.println("Edge Count="+edgeCount);
		System.out.println("Max spacing="+ q.peek());
		
	}

	public void readFile(String fileName) throws IOException, NumberFormatException{
		FileInputStream in = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			int lc=0;
			int weigh,src,dest;
			String line = null;
			while (true)
			{
				line = br.readLine();
				if (line == null)
				{
					break;
				}
				String[] strLine = (line.split(" "));
				
				if(lc==0){
					n=Integer.parseInt(strLine[0]);	
				}else{
				src=Integer.parseInt(strLine[0]);
				dest=Integer.parseInt(strLine[1]);					
				weigh=Integer.parseInt(strLine[2]);
				q.offer(weigh);
				}
				lc++;
			}// end while
	}// end readLine
	 
	

	
	
	public static void main(String[] args) throws NumberFormatException,IOException {
//		clusterTest c1 = new clusterTest("/home/adil/Downloads/clustering1.txt");
		clusterTest c2 = new clusterTest("/home/adil/Downloads/assgn2test1.txt");
	}

}
