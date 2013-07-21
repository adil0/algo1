import java.io.IOException;


public class QuickSortTest {

	public static void main(String[] args) throws NumberFormatException, IOException{
		QuickSort sortArray = new QuickSort();
		
		System.out.println("Unsorted:" + sortArray.toString() + "\n");
		
		sortArray.sort();
		
		long countComp= sortArray.getCountComp();
		
		System.out.println("Sorted:" + sortArray.toString() + "\n");
		
		System.out.println("No of Comparisions:" + countComp + "\n");
	}
}
