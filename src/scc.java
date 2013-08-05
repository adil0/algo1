import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class scc {
	 int n,s;
	 int t=0;
	 int k=0;
	 int sccSz;
	 List<Integer>[] adj;
	 List<Integer>[] adjRev;
	 List<Integer>[] adjFinTimes;
	 int[] track,finTimes,leader;
	 
	 
	public scc(String fileName, int sz) throws IOException,NumberFormatException{
		n=sz;
		adj = (List<Integer>[])new List[n];
		adjRev = (List<Integer>[])new List[n];
		adjFinTimes= (List<Integer>[])new List[n];
		track=new int[n];
		finTimes=new int[n];
		leader=new int[n];
		for (int i = 0; i < n; i++){
		adj[i] = new ArrayList<Integer>();
		adjRev[i] = new ArrayList<Integer>();
		adjFinTimes[i]=new ArrayList<Integer>();
		}
		for(int i=1;i<n;i++){
			track[i]=0;
			finTimes[i]=0;
			leader[i]=0;
		}
		
		int[] res={1,1,1,1,1};
		for(int i=0;i<5;i++){
			System.out.println(res[i]);
		}
		readFile(fileName);
		System.out.println("File Reading Done");
//		System.out.println(outEdges(1));
//		System.out.println(adj[1]);
//		System.out.println("Reverse Array");
//		for(int k=(n-1);k>0;k--){
//		System.out.println(k+":"+ adjRev[k]);
//		}
		
		// calculate the finishing times
		for(int k=(n-1);k>0;k--){
			if(track[k]==0){
				DFS(k);
//				System.out.println("Rev. Processed for vertex: "+ k);
			}
		}
		System.out.println("Finishing Times Calculated");
		
		adjRev=null;
		System.gc();
		// create Finishing times adjacency list
		createFinTimeList();
		System.out.println("Finishing Times List created");
		
		// calculate the master nodes
		System.out.println("Print out the size of SCCs");

		for(int i=1;i<n;i++){
			track[i]=0;
		}

		for(int k=(n-1);k>0;k--){
			if(track[k]==0){
				sccSz=0;
				s=k;
				DFSF(k);
//				System.out.println(sccSz);
				for(int i=0;i<5;i++){
					if(sccSz>res[i]){
						res[i]=sccSz;
						break;
					}
				}				
			}
		}
	
		for(int i=0;i<5;i++){
			System.out.println(res[i]);
		}
	}// end constructor
	
	public void readFile(String fileName) throws IOException, NumberFormatException{
		FileInputStream in = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
						
			int i,j;
			String line = null;
			while (true)
			{
				line = br.readLine();
				if (line == null)
				{
					break;
				}
				String[] strLine = (line.split(" "));
				
				i=Integer.parseInt(strLine[0]);
				j=Integer.parseInt(strLine[1]);
//				System.out.printf("%d %d \n",i,j);
				addEdge(i, j);			
			}
	}
	 
	public void addEdge(int i, int j) {
	        adj[i].add(j);
	        adjRev[j].add(i);
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
	 

	 public void DFS(int i){
		track[i]=1;
//		System.out.println(adjRev[i]);
		ListIterator<Integer> it= adjRev[i].listIterator();
		 
		 while(it.hasNext()){
			 int k=it.next();
//			 System.out.println("k: " + k);
			 if(track[k]==0){				 
				 DFS(k);
			 }else{
					t++;
					finTimes[i]=t;
				 return;
			 }
		 }
		t++;
		finTimes[i]=t; 
	 }
	 
	 public void createFinTimeList(){
		 for(int i=(n-1);i>0;i--){
			 int f=finTimes[i];
//			 System.out.println("Finishing time of " + i + "=" + f);
//			 System.out.println(adj[i]);
			 ListIterator<Integer> itr=adj[i].listIterator();
			 while(itr.hasNext()){
				 int k=itr.next();
				 int p=finTimes[k];
//				 System.out.println("f="+f+" and p="+p);
				adjFinTimes[f].add(p); 
			 }
		 }
	 }
	 
	 public void DFSF(int i){
		 track[i]=1;
		 leader[i]=s;
		 
		 ListIterator<Integer> it= adjFinTimes[i].listIterator();
		 while(it.hasNext()){
			 int k=it.next();
			 if(track[k]==0){
				 sccSz++;
				 DFSF(k);
			 }else{
				 return;
			 }
		 }
	 }
	 
}//end class
