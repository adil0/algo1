import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ks {
	int n,W;
	ArrayList<Integer> v=new ArrayList<Integer>();
	ArrayList<Integer> w = new ArrayList<Integer>();
	int[][] A;
	
	public ks(String fileName) throws NumberFormatException,IOException{
		readFile(fileName);
		
		A= new int[(n+1)][(W+1)];
		for(int i=0;i<(W+1);i++){
			A[0][i]=0;
		}
		System.out.println("n="+n);
		System.out.println("W="+W);
		System.out.println(v.get(0));
		System.out.println(w.get(0));
		
		
		knap();
		System.out.println(A[n][W]);
	}
	
	public void readFile(String fileName) throws IOException, NumberFormatException{
		FileInputStream in = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			int lc=0;
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
					W=Integer.parseInt(strLine[0]);
					n=Integer.parseInt(strLine[1]);
				}else{
					v.add(Integer.parseInt(strLine[0]));
					w.add(Integer.parseInt(strLine[1]));					
				}
				lc++;
			}// end while
			in.close();
	}// end readLine

	public void knap(){
		for(int i=1;i<(n+1);i++){
			for(int x=0;x<(W+1);x++){
				if(x-w.get(i-1) >= 0){
				A[i][x]=Math.max(A[i-1][x], A[i-1][x-w.get(i-1)] + v.get(i-1));
				}else{
					A[i][x]= A[i-1][x];
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException,NumberFormatException{
		ks k1 = new ks("/home/adil/Downloads/knapsack1.txt");

	}

}
