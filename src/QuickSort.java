import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuickSort {

	int array[];
	private static Random generator = new Random();
	private long countComp=0;
	
	public QuickSort() throws NumberFormatException, IOException{
	readFile("/home/adil/Downloads/QuickSort.txt");	
	}// end constructor

	public void readFile(String fileName) throws NumberFormatException, IOException{
		FileInputStream in = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		List<Integer> intArray = new ArrayList<Integer>(); 
		
		String line = null;
		while (true)
		{
			line = br.readLine();
			if (line == null)
			{
				break;
			}
			intArray.add(Integer.parseInt(line.trim()));
		}			
		
		array= new int[intArray.size()];
		for (int j = 0; j < array.length; j++)
		{
			array[j] = intArray.get(j);
		}			
		System.out.println("ArrayLength=" + intArray.size());
		in.close();	
	}
	
	public void sort(){
		sortArray(0,array.length-1);
	}
	
	public void sortArray(int low,int high){
		if((high-low+1) > 1){
//			System.out.println("Start of func call:" + subarray(low, high)+"\n");
			int pivotIndex=low + choosePivot(high - low + 1);
			int pivot=array[pivotIndex];
//			System.out.println("Pivot:" + pivot +"\n");
			int i=low + 1;
//			System.out.println("i:" + i+"\n");
			int j=low + 1;
			int temp=array[low];
			array[low]=pivot;
			array[pivotIndex]=temp;
//			System.out.println("After moving pivot:" + subarray(low, high)+"\n");
			
			for(j=low+1;j<=high;j++){
				if(array[j] < pivot){
					temp=array[i];
					array[i]=array[j];
					array[j]=temp;
					i++;					
				}
			}
			
//			System.out.println("After partitioning:" + subarray(low, high)+"\n");
//			System.out.println("i:" + i+"\n");
			// enter the pivot at the correct position
			temp=array[i-1];
			array[i-1]= pivot;
			array[low]= temp;
//			System.out.println("After moving pivot in correct pos:" + subarray(low, high)+"\n");
			
			countComp = countComp + (high - low);
			if(i-2>=0){
				sortArray(low, (i-2));
			}
			sortArray(i, high);
		}
	}//end method sortArray
	
	
	public int choosePivot(int n){
		int pivot=(n-1);
		return pivot;
	}
	
	// method to output certain values in array
    public String subarray( int low, int high )
    {
      StringBuffer temporary = new StringBuffer();
 
      // output spaces for alignment
      for ( int i = 0; i < low; i++ )
       temporary.append( "   " );
 
      // output elements left in array
      for ( int i = low; i <= high; i++ )
       temporary.append( " " + array[ i ] );
 
      return temporary.toString();
     } // end method subarray
 
     // method to output values in array
     public String toString()
     {
       return subarray( 0, array.length - 1 );
     } // end method toString
     
     public long getCountComp(){
    	 return countComp;
     }
}
