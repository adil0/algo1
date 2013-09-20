import java.util.HashMap;
import java.util.PriorityQueue;


public class test2 {

	public static void main(String[] args) {
		int array[]= {1,1,3,4,5};
		String s;
		
		HashMap<Integer, String> map= new HashMap<Integer, String>();
		
		System.out.println(map.get(1));
		s = map.get(1);
		if(s==null){s="";}
		s = s + " ab"+"-"+"cd";
		map.put(1, s);
		System.out.println(map.get(1));
		s=map.get(1);
		if(s==null){s="";}
		s = s + " ef"+"-"+"gh";
		map.put(1, s);
		System.out.println(map.get(1));
	}

}
