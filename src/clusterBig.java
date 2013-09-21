import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;



public class clusterBig {
	 int n,clustCount,minSep=3;
	 List <String>[] group;
	 ArrayList<String> adj=new ArrayList<String>();
	 int maxSpacing,codeLen;

	public clusterBig(String fileName) throws IOException,NumberFormatException{
		readFile(fileName);
		System.out.println("n="+n);
		System.out.println("code Length="+codeLen);
		clustCount=n;
		
		groupClusters();
		
		for(int k=0;k<codeLen;k++){
			for(int i=0;i<group[k].size();i++){
//				System.out.println(group[k].get(i));
			}
		}
		
		findNearestAndMerge();
		System.out.println("Cluster Count="+clustCount);
	}// end constructor
	
	public void readFile(String fileName) throws IOException, NumberFormatException{
		FileInputStream in = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			int lc=0;
			String line = null;
			while (true)
			{
				line = br.readLine();
				if (line == null)
				{
					break;
				}
				
				if(lc==0){
					String[] strLine = (line.split(" "));
					n=Integer.parseInt(strLine[0]);
					codeLen=Integer.parseInt(strLine[1]);
					// initialize the group adjacnecy list
					group = (List<String>[])new List[(n+1)];
					for(int i=0;i<(n+1);i++){
						group[i]=new ArrayList<String>();
					}
				}else{
					adj.add(line.trim());
				}
				lc++;
			}// end while
	}// end readLine
			
	public void groupClusters(){
		int sum;
		String s;
		String[] sArray;
		int el;
		for(int i=0;i<adj.size();i++){
			sum=0;
			el=0;
			s=adj.get(i);
			sArray=s.split(" ");
			for(int k=(codeLen-1);k>=0;k--){
				el= (int) (el + Integer.parseInt(sArray[k])*Math.pow(10, k));
			}
			for(int j=0;j<codeLen;j++){
				sum= sum + el%10;
				el=el/10;
			}// end inner for
			group[sum].add(adj.get(i));
		}// end for
	}// end function
	
	public void findNearestAndMerge(){
		int lookFwd;
		for(int i=0;i<codeLen;i++){
			if(group[i].size()==0){
				System.out.printf("There is no element with sum=%d",i);
			}else{
				lookFwd=i+minSep-1;
				if(lookFwd >= codeLen){
					break; // get outside loop if 
							//we have already looked at all elements
				}
				clustCount--;
			}// end else
		}// end for		
	}//end function
	
	public int findDist(String s1, String s2){
		int dist=0;
		
		return dist;
	}
	
	public static void main(String[] args) throws NumberFormatException,IOException {
//		clusterBig c1 = new clusterBig("/home/adil/Downloads/clustering_big.txt");
		clusterBig c2 = new clusterBig("/home/adil/Downloads/assign2test2.txt");
	}

}
