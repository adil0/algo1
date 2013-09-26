import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ksBig {
	int n,W;
	ArrayList<Integer> v=new ArrayList<Integer>();
	ArrayList<Integer> w = new ArrayList<Integer>();
	 int[][] A;
	
	public ksBig(String fileName) throws NumberFormatException,IOException{
		readFile(fileName);
		
		A= new int[2][(W+1)];
		for(int i=0;i<(W+1);i++){
			A[0][i]=0;
		}
		
		System.out.println("n="+n);
		System.out.println("W="+W);
		System.out.println(v.get(0));
		System.out.println(w.get(0));
		
		
		knap();
		System.out.println(A[0][W]);
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
	}// end readLine

	public void knap(){
		for(int i=1;i<(n+1);i++){
			for(int x=0;x<(W+1);x++){
				if(x-w.get(i-1) >= 0){
					A[1][x]=Math.max(A[0][x], A[0][x-w.get(i-1)] + v.get(i-1));
				}else{
					A[1][x]= A[0][x];
				}
			}
			for(int k=0;k<(W+1);k++){
			A[0][k]= A[1][k];
			A[1][k]=0;
			}
		}
	}

	public static void main(String[] args) throws IOException,NumberFormatException{
		ksBig k1 = new ksBig("/home/adil/Downloads/knapsack_big.txt");

	}

}
