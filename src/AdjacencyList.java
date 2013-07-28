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
	 int n=200;
	 int a,b;
	 int k=0;
	 List<Integer>[] adj;
	 Random generator = new Random();
	 int vertexCount=n;
	 int minCutEdgeCount;
	 
	public AdjacencyList(String fileName)throws IOException, NumberFormatException{

		 adj = (List<Integer>[])new List[n];
		 for (int i = 0; i < n; i++)
		 adj[i] = new ArrayList<Integer>();
		 readFile(fileName);
		 		 
		 while(vertexCount > 2){
			 a = generator.nextInt(n);
			 b = generator.nextInt(n);			 
			 contractEdge(a, b);
//			 System.out.println(vertexCount);
		 }//end while
		 
		 while(k<n){
			 if(adj[k]!=null){
				 System.out.println(adj[k]);
				 System.out.println(adj[k].size());
			 }
			 k++;
		 }		 
		 
//		 removeSelfLoops(k);
//		 minCutEdgeCount= countEgde(k);
//		 System.out.println("Edges in min cut:" + minCutEdgeCount);
	}// end constructor
	
	public void readFile(String fileName) throws IOException, NumberFormatException{
		FileInputStream in = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		List<Integer> intArray = new ArrayList<Integer>(); 
			
			int i=0;
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
	}
	 
	public void addEdge(int i, int j) {
	        adj[i].add(j);
	    }
    
	public void removeEdge(int i, int j) {
        Iterator<Integer> it = adj[i].iterator();
        while (it.hasNext()) {
            if (it.next() == j) {
                it.remove();
                return;
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
        for (int j = 0; j < n; j++)
        	if (adj[j].contains(i))    edges.add(j);
	        return edges;
	    }// end inEdge
	 
	 public void contractEdge(int i, int j){
		 if(adj[i] != null && adj[j]!=null){
			 ListIterator<Integer> it= adj[j].listIterator();
			 while(it.hasNext()){
				 int k=it.next();
				 if(!(adj[i].contains(k))){
					 addEdge(i, k);
				 }//end if
				 if(adj[i].contains(j+1) || adj[i].contains(i+1)){
					 removeEdge(i, j+1);
					 removeEdge(i, i+1);
				 }
			 }//end while 
			 adj[j]=null;
			 vertexCount--;	
		 }// end outer if		 
	 } // end contract edge
	 
	 public void removeSelfLoops(int i)
	 {
		 if(adj[i] != null){
			 ListIterator<Integer> itr= adj[i].listIterator();
			 while(itr.hasNext()){
				 int k=itr.next();
				 if(k==(i+1)){
					 removeEdge(i, k);
					 return;
				 }//end if 
			 }//end while
		 }// end outer if
	 }// end self loops
	 
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