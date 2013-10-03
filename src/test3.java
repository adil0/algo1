import java.util.HashMap;
import java.util.PriorityQueue;


public class test3 {
		
	public static void main(String[] args) {
		int n=7;
		double min,psum;
		double[][] A= new double[n][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				A[i][j]=0;
			}
		}
		
		double[] p={0.05,0.4,0.08,0.04,0.1,0.1,0.23};
		
		for(int s=0;s<(n-1);s++){
			for(int i=0;i<n;i++){
				min=10;
				psum=0;
				for(int r=i;r<=(i+s);r++){
					if((r-1)>=0 & (i-s)>=0 & r<(n-2) & (i+s)<(n-1)){
						System.out.println("s="+s);
						System.out.println("i="+i);
						System.out.println("r="+r);							

						if(A[i][r-1]+A[r+1][i-s]< min){
//							System.out.println("s="+s);
//							System.out.println("i="+i);
//							System.out.println("r="+r);							
							min=A[i][r-1] + A[r+1][i-s];
						}
						psum+=p[r];
					}
				}
				if((i+s)<(n-1))A[i][i+s]=min+psum;
			}
		}
		System.out.println(A[0][n-1]);
		
	}// end main			
}

