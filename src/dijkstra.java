import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class dijkstra {
	 int n,s;
	 int t=0;
	 int k=0;
	 int sccSz;
	 List<Integer>[] adj;
	 List<Integer>[] wt;
	 List<Integer> vst;
	 List<Integer> nonVst;
	 int[] A;
	 
	 
	public dijkstra(String fileName, int sz) throws IOException,NumberFormatException{
		n=sz;
		adj = (List<Integer>[])new List[n];
		wt = (List<Integer>[])new List[n];
		A=new int[n];
		vst = new ArrayList<Integer>();
		nonVst = new ArrayList<Integer>();
		for (int i = 0; i < n; i++){
		adj[i] = new ArrayList<Integer>();
		wt[i] = new ArrayList<Integer>();
		A[i]=1000000;
		nonVst.add(i);
		}
		nonVst.remove(0);
		A[1]=0;		
		vst.add(1);
		nonVst.remove(1);
		
		readFile(fileName);
		System.out.println("File Reading Done");
		System.out.println(adj[1]);
		System.out.println(wt[1]);
	}// end constructor

	
	public void readFile(String fileName) throws IOException, NumberFormatException{
		FileInputStream in = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			int i=1;
			int k;
			String line = null;
			while (true)
			{
				line = br.readLine();
				if (line == null)
				{
					break;
				}
				String[] strLine = (line.split("\t"));
				
				for(int j=1;j<strLine.length;j++){
					String[] ver=strLine[j].split(",");
					k=Integer.parseInt(ver[0]);
					int weight=Integer.parseInt(ver[1]);
					addEdge(i, k);
					addWeight(i,weight);
				}
				i++;
			}
	}
	 
	public void addEdge(int i, int j) {
	        adj[i].add(j);
	    }
	
	public void addWeight(int i, int j) {
        wt[i].add(j);
    }
	
	
	 public boolean hasEdge(int i, int j) {
	       return adj[i].contains(j);
	 }// end hasEdge
	 
	 List<Integer> outEdges(int i) {
	        return adj[i];
	 }// end outEdge
	 
	 public void computeDist(int i){
		 if(vst.contains(i)){
			 System.out.println("Shortest Dist. already calculated");
			 return;
		 }
		 
		 
		 Iterator<Integer> it = vst.iterator();
		 while(it.hasNext()){
			 int k=it.next();
			 
			 if(adj[k].contains(k)){
				 
			 }
		 }
	 }// end compute dist
	
} // end class
