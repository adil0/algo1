import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.io.IOException;


public class AdjacencyList {
	 int n=200;
	 List<Integer>[] adj;
	       	  	
	public AdjacencyList(String fileName)throws IOException, NumberFormatException{

		 adj = (List<Integer>[])new List[n];
		 for (int i = 0; i < n; i++)
		 adj[i] = new ArrayList<Integer>();
		 readFile(fileName);
	}
	
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
				
				for(int j=0;j<strLine.length;j++){
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
	
	 boolean hasEdge(int i, int j) {
	       return adj[i].contains(j);
	 }// end hasEdge
	 
	 List<Integer> outEdges(int i) {
	        return adj[i];
	 }// end outEdge
	 
	 List<Integer> inEdges(int i) {
	   	List<Integer> edges = new ArrayList<Integer>();
        for (int j = 0; j < n; j++)
        	if (adj[j].contains(i))    edges.add(j);
	        return edges;
	    }	    
}