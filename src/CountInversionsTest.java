import java.io.IOException;


public class CountInversionsTest {
	public static void main(String[] args) throws NumberFormatException, IOException{
		CountInversions sortArray= new CountInversions();
		
		System.out.println("Unsorted:"+ sortArray.toString()+"\n");
		
		sortArray.sortAndCountInversions();
		
		System.out.println("Sorted:"+ sortArray.toString()+"\n");
		
		long inversionCount= sortArray.getInversionCount();
		
		System.out.println("Inversion Count="+ inversionCount);
	}//end main

	
}
