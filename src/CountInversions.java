import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CountInversions {
	
	long inversionCount=0;
	int array[];
	
	// constructor
		public CountInversions() throws NumberFormatException, IOException
		{
			
			FileInputStream in = new FileInputStream("/home/adil/Downloads/IntegerArray.txt");
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

		}// end constructor
		
		public void sortAndCountInversions(){
			sortAndCountInversionsArray(0, array.length-1 );
		}
		
		private void sortAndCountInversionsArray(int low, int high){
			
			if((high - low) >= 1){
				int middle1 = (low + high)/2;
				int middle2 = middle1 + 1;
				
				// print the split array
//				System.out.println("Split:"+ subarray(low, high));
//				System.out.println("	"+ subarray(low, middle1));
//				System.out.println("	"+ subarray(middle2, high));
//				System.out.println();
				System.out.println("Split:"+ subarray(low, high));
				System.out.println("	"+ subarray(low, middle1));
				System.out.println("	"+ subarray(middle2, high));
				System.out.println();
				
				sortAndCountInversionsArray(low, middle1);
				sortAndCountInversionsArray(middle2, high);
				
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
//			System.out.println( "merge:   " + subarray( left, middle1 ) );
//			System.out.println( "         " + subarray( middle2, right ) );
			System.out.println( "merge:   " + subarray( left, middle1 ) );
			System.out.println( "         " + subarray( middle2, right ) );

			
			while(leftIndex <= middle1 && rightIndex <= right){
				if(array[leftIndex] <= array[rightIndex]){
					combArray[combIndex++]= array[leftIndex++];
				} else{
					combArray[combIndex++]= array[rightIndex++];
					inversionCount = inversionCount + (middle1- leftIndex) + 1;
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

//			System.out.println("	"+ subarray(left, right));
//			System.out.println();

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
	     


	     public long getInversionCount(){
	    	 return inversionCount;
	     }
	}// end Class

