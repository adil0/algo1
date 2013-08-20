import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Collections;

public class dijkstra {
	 int n,s;
	 int t=0;
	 int k=0;
	 int sccSz;
	 List<Integer>[] adj;
	 List<Integer>[] wt;
	 List<Integer> vst;
	 List<Integer> nonVst;
	 int[] A,B;
	 
	 
	public dijkstra(String fileName, int sz) throws IOException,NumberFormatException{
		n=sz;
		adj = (List<Integer>[])new List[n];
		wt = (List<Integer>[])new List[n];
		A=new int[n];
		B=new int[n];
		vst = new ArrayList<Integer>();
		nonVst = new ArrayList<Integer>();
		for (int i = 1;i < n; i++){
		adj[i] = new ArrayList<Integer>();
		wt[i] = new ArrayList<Integer>();
		A[i]=0;
		B[i]=0;
		nonVst.add(i);
		}
		
		A[1]=0;		
		vst.add(1);
		nonVst.remove(1);
		
		readFile(fileName);
		System.out.println("File Reading Done");
//		System.out.println(adj[1]);
//		System.out.println(wt[1]);
		
		// compute the shortest distances 
		for(int i=2;i<4;i++){
			computeDist();
		}
		
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
	 
	 public void computeDist(){
		 int minVal=100000;
		 int minListIndex=0;
		 int minIndex=0;
		 
		 for(int i=1;i<n;i++){
			 B[i]=0;
		 }
		 
		 Iterator<Integer> it = vst.iterator();
		 while(it.hasNext()){
			 int k=it.next();
			 
			 for(int i=0;i<adj[k].size();i++){
				 if(vst.contains(adj[k].get(i))){
					System.out.println("Shortest distance already calculated"); 
				 }else{
					 if( B[adj[k].get(i)]==0 && A[k] + wt[k].get(i) >= B[adj[k].get(i)]){
						 B[adj[k].get(i)] = A[k] + wt[k].get(i);
						 if(B[adj[k].get(i)] <= minVal){
							 minVal=B[adj[k].get(i)];
							 minListIndex=k;
							 minIndex=adj[k].get(i);
						 }
					 }else if(B[adj[k].get(i)] !=0 && A[k] + wt[k].get(i) < B[adj[k].get(i)]){
						 B[adj[k].get(i)] = A[k] + wt[k].get(i);
						 if(B[adj[k].get(i)] <= minVal){
							 minVal=B[adj[k].get(i)];
							 minListIndex=k;
							 minIndex=adj[k].get(i);
						 }
					 }
				 }//end else				 
			 }//end for			 
		 }// end while
		 // calculate the minimum and add that to the visited vertex
		 	vst.add(minListIndex);
		 	nonVst.remove(minListIndex);
		 	A[minIndex]=B[minIndex];
		 	System.out.printf("Shortest diastance to vertex %d=%d",minIndex,A[minIndex]);
	 }// end computeDist
	
} // end class
