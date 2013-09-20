import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.MathUtils;


public class clusterBig {
	 int n,clustCount,minSep;
	 List <String>[] group;
	 ArrayList<String> adj=new ArrayList<String>();
	 int maxSpacing,codeLen;

	public clusterBig(String fileName) throws IOException,NumberFormatException{
		readFile(fileName);
		System.out.println("n="+n);
		System.out.println("code Length="+codeLen);
		System.out.println(adj.get(0));
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
			
	
	
	public static void main(String[] args) throws NumberFormatException,IOException {
		clusterBig c1 = new clusterBig("/home/adil/Downloads/clustering_big.txt");
//		cluster c2 = new cluster("/home/adil/Downloads/assgn2test1.txt");
	}

}
