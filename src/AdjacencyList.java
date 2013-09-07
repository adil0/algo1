import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;


public class AdjacencyList {
	 int n;
	 int a,b;
	 int k=1;
	 List<Integer>[] adj;
	 Random generator = new Random();
	 int vertexCount;
	 int minCutEdgeCount;
	 
	public AdjacencyList(String fileName, int sz)throws IOException, NumberFormatException{
		n=sz;
		vertexCount=(n-1);
		 adj = (List<Integer>[])new List[n];
		 for (int i = 1; i < n; i++)
		 adj[i] = new ArrayList<Integer>();
		 readFile(fileName);
		 
		 for(int p=1;p<n;p++){
//			 System.out.println(outEdges(p));
//			 System.out.println(inEdges(p));
		 }
		 
		 while(vertexCount > 2){
			 a = 1 + generator.nextInt(n-1);
			 b = 1 + generator.nextInt(n-1);
			 
			 if(a!=b){
//				 System.out.printf("%d %d \n",a,b);	 
				 contractEdge(a, b);
			 }
			 
//			 System.out.println(vertexCount);
		 }//end while
		 
		 while(k<n){
			 if(adj[k]!=null){
				 System.out.println(k);
				 System.out.println(adj[k]);
				 System.out.printf("size=%d \n",adj[k].size());
			 }
			 k++;
		 }		 
		 

//		 minCutEdgeCount= countEgde(k);
//		 System.out.println("Edges in min cut:" + minCutEdgeCount);
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
					k=Integer.parseInt(strLine[j]);
					addEdge(i, k);
				}
				i++;
			}
			in.close();
	}
	 
	public void addEdge(int i, int j) {
	        adj[i].add(Integer.valueOf(j));
	    }
    
	public void removeEdge(int i, int j) {
		if(adj[i] != null){
			if(adj[i].contains(Integer.valueOf(j))){
				adj[i].remove(Integer.valueOf(j));
			}
		}
    }// end removeEdge
	
	 public boolean hasEdge(int i, int j) {
	       return adj[i].contains(j);
	 }// end hasEdge
	 
	 List<Integer> outEdges(int i) {
	        return adj[i];
	 }// end outEdge
	 
	 public List<Integer> inEdges(int i) {
	   	List<Integer> edges = new ArrayList<Integer>();
        for (int j = 1; j < n; j++)       	
        	if (adj[j] != null && adj[j].contains(i))    edges.add(j);
	        return edges;
	    }// end inEdgeedges
	 
	 public void contractEdge(int i, int j){
		 if(adj[i] != null && adj[j]!=null){
			 if(adj[i].contains(Integer.valueOf(j))){
			 ListIterator<Integer> it= adj[j].listIterator();
			 while(it.hasNext()){
				 int k=it.next();
				 if(!adj[i].contains(Integer.valueOf(k))){
					 addEdge(i, k);
				 }//end if
			 }//end while
			 
			 if(adj[i].contains(Integer.valueOf(j))){
//				 System.out.printf("j= %d \n",j);
				 removeEdge(i, j);
			 }
			 if(adj[i].contains(Integer.valueOf(i))){
//				 System.out.printf("i= %d \n",i+1);
				 removeEdge(i, i);
			 }
			 adj[j]=null;
			 
			 // replace the removed vertex from the graph and add single in its place	
			 for(int p=1;p<n;p++){
				if(adj[p] != null ){
					if(adj[p].contains(Integer.valueOf(j))){
						adj[p].remove(Integer.valueOf(j));
//						System.out.println("After removing to "+ p);
//						System.out.println(adj[p]);
						adj[p].add(Integer.valueOf(i));
//						System.out.println("After adding to "+ p);
//						System.out.println(adj[p]);
					}
				}
			 }
			 
//			 System.out.println(adj[i]);
			 vertexCount--;	
		 }//end inner if 
		 }// end outer if
	 } // end contract edge
	 
	 
	 public int countEgde(int i)
	 {
		int count;		
		if(adj[i]==null){
			count=0;			
		}else{
			count=adj[i].size();
		}
		return count;
	 }// end countEdge
}