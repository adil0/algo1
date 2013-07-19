import java.util.Random;

public class MergeSort {
// constructor
	int array[];
	private static Random generator = new Random();
	
	public MergeSort(int size){
		array= new int[size];
		
		for(int i=0;i<size;i++){
			array[i]= 10 + generator.nextInt(90);
		}		
	}// end constructor
	
	public void sort(){
		sortArray(0, array.length-1 );
	}
	
	private void sortArray(int low, int high){
		
		if((high - low) >= 1){
			int middle1 = (low + high)/2;
			int middle2 = middle1 + 1;
			
			// print the split array
			System.out.println("Split:"+ subarray(low, high));
			System.out.println("	"+ subarray(low, middle1));
			System.out.println("	"+ subarray(middle2, high));
			System.out.println();
			
			sortArray(low, middle1);
			sortArray(middle2, high);
			
			merge(low,middle1,middle2,high);
		}		
	}
	
	private void merge(int left, int middle1, int middle2, int right)
	{
		int leftIndex= left;
		int rightIndex = middle2;
		int combIndex= left;
		int[] combArray= new int[array.length];
		
		// output two subarrays before merging
		System.out.println( "merge:   " + subarray( left, middle1 ) );
		System.out.println( "         " + subarray( middle2, right ) );
		
		while(leftIndex <= middle1 && rightIndex <= right){
			if(array[leftIndex] <= array[rightIndex]){
				combArray[combIndex++]= array[leftIndex++];
			} else{
				combArray[combIndex++]= array[rightIndex++];
			}
		}// end while
		
		// if the left array is empty
		if(leftIndex == middle2){
			while(rightIndex <= right){
				combArray[combIndex++]= array[rightIndex++];
			}
		}else{// if the right array is empty
			while(leftIndex <= middle1){
				combArray[combIndex++]= array[leftIndex++];
			}
		}// end else
		
		// copy the array back to the original array
		for(int i=left; i<= right;i++){
			array[i] = combArray[i];
		}
		
		// print the merged array
		System.out.println("	"+ subarray(left, right));
		System.out.println();
	}// end method merge
	
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
}// end Class
