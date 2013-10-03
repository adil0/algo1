import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.stream.FileImageInputStream;


public class fw {
	 int n,m,negDet=0;
	 long min=10000;	
	 long[][][] A;
	 
	 public fw (String fileName) throws IOException, NumberFormatException{
		readFile(fileName);
		System.out.println("n="+n);
		System.out.println("m="+m);
		
//		System.out.println("A[1][1][0]="+A[1][1][0]);
//		System.out.println("A[2][2][0]="+A[2][2][0]);
//		System.out.println("A[3][1][0]="+A[3][1][0]);
		
		floyd();
//		System.out.println("A[1][1][0]="+A[1][1][0]);
//		System.out.println("A[2][2][0]="+A[2][2][0]);
		
		for(int i=1;i<(n+1);i++){
			for(int j=1;j<(n+1);j++){
				if(A[i][j][0]<min){
					min=A[i][j][0];
				}
				if(i==j & A[i][j][0]<0){
					negDet=1;
				}
			}
		}
		System.out.println("Neg Det="+negDet);
		System.out.println("Minimum value="+min);
	 }
	 
	 public void readFile(String fileName) throws IOException, NumberFormatException{
		FileInputStream in = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		int src,dest,weigh,lc=0;
		String line;
		String[] strLine;
		while(true){
			line=br.readLine();
			if(line==null){
				break;
			}
			
			strLine= line.split(" ");
			
			if(lc==0){
				n=Integer.parseInt(strLine[0]);
				m=Integer.parseInt(strLine[1]);
				
				// array  declarations
				A= new long[(n+1)][(n+1)][2];
				for(int i=1;i<(n+1);i++){
					for( int j=1;j<(n+1);j++){
						for(int k=0;k<2;k++){
							if(i==j){
								A[i][j][k]=0;
							}else{
							A[i][j][k]=10000;
							}// end else
						}// end k
					}// end j				
				}// end i			
			}else{
				src=Integer.parseInt(strLine[0]);
				dest=Integer.parseInt(strLine[1]);					
				weigh=Integer.parseInt(strLine[2]);
				A[src][dest][0]=weigh;
			}
			lc++;
		}// end while		
		in.close();
	 }// end readFile
	 
	 public void floyd(){
		 for(int k=1;k<(n+1);k++){
			 for(int i=1;i<(n+1);i++){
				 for(int j=1;j<(n+1);j++){
					 if(A[i][j][0] <= (A[i][k][0]+A[k][j][0])){
						 A[i][j][1]=A[i][j][0];
					 }else{
						 A[i][j][1]=A[i][k][0]+A[k][j][0];
					 }
				 }// end j
			 }// end i
			 // copy the array
			 for(int p=1;p<(n+1);p++){
				 for(int q=1;q<(n+1);q++){
					 A[p][q][0]=A[p][q][1];
				 }// end q
			 }// end p
//			 System.out.println("k="+k);
//			 System.out.println("A[1][1][0]="+A[1][1][0]);
//			 System.out.println("A[2][2][0]="+A[2][2][0]);
			 // copying of array done
		 }// end k
	 }// end floyd
	
	 public static void main(String[] args) throws IOException,NumberFormatException{
//		fw w1= new fw("/home/adil/Downloads/g1.txt");
//		fw w2= new fw("/home/adil/Downloads/g2.txt");
		fw w3= new fw("/home/adil/Downloads/g3.txt");
//		fw w2= new fw("/home/adil/Downloads/assgn4test1.txt");
	}

}
