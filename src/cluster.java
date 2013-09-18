import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class cluster {
	 int n,clustCount;
	 long mc=0;
	 List<Integer>[] adj;
	 List<Integer>[] wt;
	 List<Integer> vst;
	 List<Integer> nonVst;
	 int reqClust=4;
	 
	public cluster(String fileName) throws NumberFormatException,IOException{
		readFile(fileName);
//		System.out.println("n="+n);
		clustCount=n;
		
		System.out.println("adj[1]="+adj[1]);
		System.out.println("wt[1]="+wt[1]);
				
		while(clustCount > reqClust){
			mergeClusters();
			clustCount--;
		}
		
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
					addEdge(dest, src);
					addWeight(dest,weigh);
				}
				lc++;
			}// end while
	}// end readLine
	 
	public void addEdge(int i, int j) {
	        adj[i].add(j);
	    }
	
	public void addWeight(int i, int j) {
        wt[i].add(j);
    }

	public void mergeClusters(){
		
	}
	
	
	public static void main(String[] args) throws NumberFormatException,IOException {
		cluster c1 = new cluster("/home/adil/Downloads/clustering1.txt");
	}

}
