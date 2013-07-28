import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

public class sample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		int a= 5;
//		System.out.println(Math.IEEEremainder(a, 2)!= 0);
		
		int array[]= {1,2,3,4,5};
		
		List<Integer> ar = new ArrayList<Integer>();
		for(int element:array){
			ar.add(element);
		}
		
		ListIterator<Integer> it = ar.listIterator(); 
		while(it.hasNext()){
			System.out.printf("%d: %d \n",it.nextIndex(),it.next());
		}// end while 		
	}// end main

}
