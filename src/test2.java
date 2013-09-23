import java.util.HashMap;
import java.util.PriorityQueue;


public class test2 {
	
	static int codeLen=8;
	
	public static void main(String[] args) {
		String a="0 1 0 0 1 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
		String b="0 0 1 0 1 1 0 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
		
		int dist=findDist(a,b);
		
		System.out.println(dist);
	}
	
	public static int findDist(String s1, String s2){
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
		
}

