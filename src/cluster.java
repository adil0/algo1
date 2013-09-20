import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class cluster {
	 int n,clustCount;
	 long mc=0;
	 List<Integer>[] adj;
	 List<Integer>[] wt;
	 HashMap<Integer, String> map = new HashMap<Integer, String>();
	 Set<Integer> keys;
	 TreeSet<Integer> sortedKeys;
	 int reqClust=4;
	 int[] leader,leaderCount;
	 int maxSpacing;
	 
	public cluster(String fileName) throws NumberFormatException,IOException{
		readFile(fileName);
//		System.out.println("n="+n);
		clustCount=n;
		leader=new int[n+1];
		leaderCount=new int[n+1];
		for(int i=1;i<(n+1);i++){
			leader[i]=i;
			leaderCount[i]=1;
		}
		
//		System.out.println("adj[1]="+adj[1]);
//		System.out.println("wt[1]="+wt[1]);
//		System.out.println(map.get(6).trim());
		
		keys = map.keySet();
		sortedKeys= new TreeSet<Integer>(keys);
		
		maxSpacing=mergeClusters();
		System.out.println("Max Spacing="+maxSpacing);
		for(int i=1;i<(n+1);i++){
//			System.out.printf("Leader[%d]=%d \n",i,leader[i]);
		}

	}

	public void readFile(String fileName) throws IOException, NumberFormatException{
		FileInputStream in = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			int lc=0;
			int weigh,src,dest;
			String s;
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
					for (int i = 1;i < (n+1); i++){
					adj[i] = new ArrayList<Integer>();
					wt[i] = new ArrayList<Integer>();
					}
				}else{
					src=Integer.parseInt(strLine[0]);
					dest=Integer.parseInt(strLine[1]);					
					weigh=Integer.parseInt(strLine[2]);
					addEdge(src, dest);
					addWeight(src,weigh);
					addEdge(dest, src);
					addWeight(dest,weigh);
					s=map.get(weigh);
					if(s==null){s="";}
					s = s +" " +String.valueOf(src)+"-"+String.valueOf(dest);
					map.put(weigh, s);
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

	public int mergeClusters(){
		String s;
		String[] els;
		String[] el;
		int source,dest,removedLeader,spacing=0,t=0;
		for(Integer keys:sortedKeys){
//			System.out.println("Outer Cluster Count="+clustCount);
			s=map.get(keys).trim();
			els= s.split(" ");
			for(int i=0;i<els.length;i++){
				el= els[i].split("-");
//				System.out.println(els[i]);
//				System.out.println("Cluster Count="+clustCount);
				source= Integer.parseInt(el[0]);
				dest=Integer.parseInt(el[1]);
				if(clustCount == reqClust && leader[source]!=leader[dest]){if(t==0){spacing=keys;t++;}break;}
				if(leader[source]!=leader[dest]){
					if(leaderCount[source] >= leaderCount[dest]){
						removedLeader=leader[dest];
						for(int k=1;k<(n+1);k++){
							if(leader[k]==removedLeader){
								leader[k]=leader[source];
								leaderCount[source]++;
							}
						}
					}else{
						removedLeader=leader[source];
						for(int k=1;k<(n+1);k++){
							if(leader[k]==removedLeader){
								leader[k]=leader[dest];
								leaderCount[dest]++;
							}
						}
					}
					clustCount--;
				}// end outer if
			}// end for			
		}// end outer for
		return spacing;
	} // end function
	
	
	public static void main(String[] args) throws NumberFormatException,IOException {
		cluster c1 = new cluster("/home/adil/Downloads/clustering1.txt");
//		cluster c2 = new cluster("/home/adil/Downloads/assgn2test1.txt");
	}

}
