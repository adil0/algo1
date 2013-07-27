import java.util.LinkedList;
import java.util.List;
import java.util.Collection;
import java.util.Iterator;


public class CollectionTest {
	private static final String[] colors ={ "MAGENTA", "RED", "WHITE",
		 "BLUE", "CYAN" };

	List <String> mainList= new LinkedList<String>();
	List <String> removeList= new LinkedList<String>();
		
	public CollectionTest(String[] removeColors){
		mainList= asList(colors);
		removeList=asList(removeColors);
		
		System.out.println("Main List:");
		
		for(int i=0;i < mainList.size();i++){
			System.out.printf("%s ",mainList.get(i));
		}
		
		System.out.println("\n \n" + "Remove List:");
		
		for(int i=0;i < removeList.size();i++){
			System.out.printf("%s ",removeList.get(i));
		}
		
		removeColors(mainList,removeList);
		
		System.out.println("\n \n" + "Truncated List:");
		
		for(int i=0;i < mainList.size();i++){
			System.out.printf("%s ",mainList.get(i));
		}
		

	}
	
	public List<String> asList(String[] colorArray){
		List <String> list= new LinkedList<String>();
		for(String color:colorArray){
			list.add(color);
		}		
		return list;
	}
	
	private void removeColors(Collection<String> coll1, 
			Collection<String> coll2){
		Iterator< String > iterator = coll1.iterator();
		
		while(iterator.hasNext()){
			if(coll2.contains(iterator.next()))
			{
				iterator.remove();
			}
		}
	}
	
	public static void main(String[] args) {
	 String[] removeColors ={ "RED", "WHITE", "BLUE" };

		new CollectionTest(removeColors);		
	}

}
