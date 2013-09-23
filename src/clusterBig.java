import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;



public class clusterBig {
	 int n,clustCount,minSep=3;
	 List <String>[] group;
	 ArrayList<String> adj=new ArrayList<String>();
	 int maxSpacing,codeLen;
	 HashMap<String, String> leader=new HashMap<String, String>();
	 HashMap<String, Integer> leaderCount=new HashMap<String, Integer>();
	 
	public clusterBig(String fileName) throws IOException,NumberFormatException{
		readFile(fileName);
		System.out.println("n="+n);
		System.out.println("code Length="+codeLen);
		clustCount=n;
		ArrayList<String> list=new ArrayList<String>();
		
		for(int i=0;i<n;i++){
			leader.put(adj.get(i), adj.get(i));
			leaderCount.put(adj.get(i), 1);
		}
		
		groupClusters();
		
		for(int k=0;k<codeLen;k++){
			for(int i=0;i<group[k].size();i++){
//				System.out.println(group[k].get(i));
			}
		}
		
		removeDuplicates();
		System.out.println("Cluster Count after removing duplicates="+clustCount);
		findNearestAndMerge();
		
		for(int i=0;i<n;i++){
			list.add(leader.get(adj.get(i)));
		}
		
//		System.out.println(list);
		Set<String> set= new HashSet<String>(list);
//		System.out.println(set.size());
		System.out.println("Cluster Count after merging="+set.size());
		
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
					group = (List<String>[])new List[(codeLen+1)];
					for(int i=0;i<(codeLen+1);i++){
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
			for(int k=0;k<sArray.length;k++){
				if(Integer.parseInt(sArray[k])==1){
				sum=sum + 1;
				}
			}
			group[sum].add(adj.get(i));
		}// end for
	}// end function
	
	public void removeDuplicates(){
		for(int i=0;i<codeLen;i++){
			if(group[i].size()>1){
			 for(int k=0;k<group[i].size();k++){
				 for(int p=k+1;p<group[i].size();p++){
					if(group[i].get(p)==group[i].get(k)){
						clustCount--;
					}
				 }// end inner for
			 	}// end outer for
			}// end else
		}
	}// end function
	
	public void findNearestAndMerge(){
		int lookFwd,k,dist,leaderCountSource,leaderCountDest;
		String source,dest,leaderSource,leaderDest,removedLeader;
		ArrayList<String> searchList=new ArrayList<String>();
		for(int i=0;i<=codeLen;i++){
			searchList.clear();
			if(group[i].size()==0){
//				System.out.printf("There is no element with sum=%d \n",i);
			}else{
				k=i;
				lookFwd=i+minSep-1;
				if(lookFwd>codeLen){break;}
				while(k <= lookFwd){
					for(int p=0;p<group[k].size();p++){
						searchList.add(group[k].get(p));
					}
					k++;
					if(k>codeLen){break;}
				}// end while
//				System.out.println(searchList);
				if(searchList.size()==1){continue;}
				for(int t=0;t<searchList.size();t++){
					for(int s=(t+1);s<searchList.size();s++){
						dist=findDist(searchList.get(t), searchList.get(s));
//						System.out.printf("Distance between %s and %s is=%d\n",searchList.get(t),searchList.get(s),dist);
						if(dist<=2){
//							System.out.printf("Distance between %s and %s is=%d\n",searchList.get(t),searchList.get(s),dist);
							source=searchList.get(t);
							dest=searchList.get(s);
							leaderSource=leader.get(source);
							leaderDest=leader.get(dest);
//							System.out.println(leaderSource);
//							System.out.println(leaderDest);
							leaderCountSource=leaderCount.get(source);
							leaderCountDest=leaderCount.get(dest);
							if(leaderCountSource>=leaderCountDest && leaderSource!= leaderDest){
								removedLeader=leaderDest;
								for(int b=0;b<n;b++){
									if(leader.get(adj.get(b))==removedLeader){
										leader.put(leader.get(adj.get(b)), leaderSource);
										leaderCount.put(source, leaderCount.get(source)+1);
									}	
								}// end for b
								System.out.printf("Merged cluster %s with %s \n",leaderDest,leaderSource);
//								System.out.println(leader.get(leaderSource));
//								System.out.println(leader.get(leaderDest));
								clustCount--;
							}else if(leaderCountDest > leaderCountSource && leaderSource != leaderDest){
								removedLeader=leaderSource;
								for(int b=0;b<n;b++){
									if(leader.get(adj.get(b))==removedLeader){
										leader.put(leader.get(adj.get(b)), leaderDest);
										leaderCount.put(dest, leaderCount.get(dest)+1);
									}	
								}// end for b
								System.out.printf("Merged cluster %s with %s \n",leaderDest,leaderSource);
								clustCount--;	
							}// end else if
						}
					}
				}
			}// end else
		}// end for		
	}//end function
	
	public int findDist(String s1, String s2){
		int dist=0;		
		String[] sArray1,sArray2;		
		
		sArray1=s1.split(" ");
		sArray2=s2.split(" ");
		for(int i=0;i<codeLen;i++){
			if(Integer.parseInt(sArray1[i]) != Integer.parseInt(sArray2[i])){
			dist++;
			}
		}
		return dist;
	}
		
	public static void main(String[] args) throws NumberFormatException,IOException {
		clusterBig c1 = new clusterBig("/home/adil/Downloads/clustering_big.txt");
//		clusterBig c2 = new clusterBig("/home/adil/Downloads/assign2test2.txt");
	}

}
