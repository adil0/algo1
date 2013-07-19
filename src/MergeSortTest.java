
public class MergeSortTest {

	public static void main(String[] args){
		MergeSort sortArray= new MergeSort(10);
		
		System.out.println("Unsorted:"+ sortArray.toString()+"\n");
		
		sortArray.sort();
		
		System.out.println("Sorted:"+ sortArray.toString()+"\n");
	}//end main
}
