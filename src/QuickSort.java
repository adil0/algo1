import java.util.Random;

public class QuickSort {

	int array[];
	private static Random generator = new Random();
	
	public QuickSort(int size){
		array = new int[size];
		
		for (int i=0;i<array.length;i++){
			array[i]= 10 + generator.nextInt(90) ;
		}
	}// end constructor

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
			
			if(i-2>=0){
				sortArray(low, (i-2));
			}
			sortArray(i, high);
		}
	}//end method sortArray
	
	
	public int choosePivot(int n){
		int pivot=generator.nextInt(n);
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

}