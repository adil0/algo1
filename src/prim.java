import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class prim {
	 int m,n,mc=0;
	 List<Integer>[] adj;
	 List<Integer>[] wt;
	 List<Integer> vst;
	 List<Integer> nonVst;

	public prim(String fileName) throws NumberFormatException,IOException{
		readFile(fileName);
//		System.out.println("n="+n);
//		System.out.println("m="+m);
//		System.out.println("adj[397]="+adj[397]);
//		System.out.println("wt[397]="+wt[397]);
		
		vst.add(Integer.valueOf(1));
		// compute the shortest distances 
		for(int i=1;i<n;i++){
			expand();
		}
		
		System.out.println("NUmber of vertex visited="+vst.size());
		System.out.println("Min cost of the MST="+mc);
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
					m=Integer.parseInt(strLine[1]);
	
					// array list declarations
					adj = (List<Integer>[])new List[(n+1)];
					wt = (List<Integer>[])new List[(n+1)];
					vst = new ArrayList<Integer>();
					nonVst = new ArrayList<Integer>();
					for (int i = 1;i < (n+1); i++){
					adj[i] = new ArrayList<Integer>();
					wt[i] = new ArrayList<Integer>();
					nonVst.add(i);
					}
				}else{
					src=Integer.parseInt(strLine[0]);
					dest=Integer.parseInt(strLine[1]);					
					weigh=Integer.parseInt(strLine[2]);
					addEdge(src, dest);
					addWeight(src,weigh);
				}
				lc++;
			}
	}// end readLine
	 
	public void addEdge(int i, int j) {
	        adj[i].add(j);
	    }
	
	public void addWeight(int i, int j) {
        wt[i].add(j);
    }

	public void expand(){
		int k,min,minSrcIndex,minDestIndex;
		 min=1000000;
		 minSrcIndex=1;
		 minDestIndex=2;

		 Iterator<Integer> it = vst.iterator();

		 while(it.hasNext()){
			 k=it.next();			 
			 for(int i=0;i<adj[k].size();i++){
				 if(vst.contains(adj[k].get(i))){
//					System.out.println("Shortest distance already calculated"); 
				 }else{
//					 System.out.println("Min Till now="+ min);
					 if( wt[k].get(i) < min){
						 min=wt[k].get(i);
						 minSrcIndex=k;
						 minDestIndex=i;
					 }//end if
				 }//end else				 
			 }//end for			 
		 }// end while
		 
//		 System.out.println("minSrc="+minSrcIndex);
//		 System.out.println("minDest="+adj[minSrcIndex].get(minDestIndex));
		 System.out.println("minDist="+wt[minSrcIndex].get(minDestIndex));
		 
		 vst.add(adj[minSrcIndex].get(minDestIndex));
		 mc= mc + wt[minSrcIndex].get(minDestIndex);
	}//end method
	
	public static void main(String[] args) throws NumberFormatException,IOException{
		prim p1 = new prim("/home/adil/Downloads/edges.txt");
	}

}
