
public class QuickSortTest {

	public static void main(String[] args){
		QuickSort sortArray = new QuickSort(20);
		
		System.out.println("Unsorted:" + sortArray.toString() + "\n");
		
		sortArray.sort();
		
		System.out.println("Sorted:" + sortArray.toString() + "\n");
	}
}
