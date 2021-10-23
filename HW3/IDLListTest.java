
public class IDLListTest {

	public static void main(String[] args) 
	{
		//build a new IDLList and imput some names.
		IDLList<String> a =new IDLList<String>();

		a.add("Wang");
		a.add("Lee");
		a.add("Chen");
		a.add("Zhang");
		a.add("Yan");
		a.add("Cai");
		a.add("Zhao");
		a.add("Sun");
		System.out.println("Preliminary IDLList:");
		a.toString();
		
		System.out.println("Add Zhou at the position of index 0:"+a.add(0,"Zhou"));
		a.toString();
		
		System.out.println("Add Bai at the position of index 3:"+a.add(3,"Bai"));
		a.toString();
		
		System.out.println("Add Qian at the position of last index:"+a.add(a.size()-1,"Qian"));
		a.toString();
		
		System.out.println("Append Liu at the IDLList:"+a.append("Liu"));
		a.toString();
		
		System.out.println("The positioin of index 3 is "+a.get(3));
		System.out.println("The head of IDLList:"+a.getHead());
		System.out.println("The tail of IDLList:"+a.getLast());
		System.out.println("The size of IDLList:"+a.size());
		
		System.out.println("Test remove():"+a.remove());
		a.toString();
		
		System.out.println("Test removeLast():"+a.removeLast());
		a.toString();
		
		System.out.println("removeAt(5):"+a.removeAt(5));
		//System.out.println("removeAt(-1):"+a.removeAt(-1));
		//System.out.println("removeAt(-1):"+a.removeAt(100));
		a.toString();
		
		System.out.println("remove('Cai'):"+a.remove("Cai"));
		a.toString();
		System.out.println("remove('NotExisting'):"+a.remove("NotExisting"));
		a.toString();


	}
}
