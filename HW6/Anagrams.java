import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * An anagram is word or phrase formed by rearranging the
 * letters of a different word or phrase, typically using all the original letters exactly once.
 * @author Chenxu Wang(10457625)
 * @version CS570_Spring_HW6_V1.0
 *  */
public class Anagrams {
	//data fields
	/**An array consists of the first 26 prime numbers.*/
	final Integer[] primes={2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 
			47, 53, 59, 61,67, 71, 73, 79, 83, 89, 97, 101};
	/**A hash table will associate each letter in the alphabet with a prime number.*/
	Map<Character,Integer> letterTable;
	/**The main hash table whose each entry includes a key and a list of words.*/
	Map<Long,ArrayList<String>> anagramTable;
	
	//Constructor
	/**Construct a new anagram with a letterTable and a anagramTalbe.
	 * post: Initialize letterTable and anagramTable */
	public Anagrams()
	{
		 letterTable=new HashMap<Character,Integer>();
		 buildLetterTable();
		 anagramTable=new HashMap<Long,ArrayList<String>>();
	}
	
	//Methods
	/**Build the hash table letterTable which consists of the 
	 * entries which include a letter and its prime.
	 * */
	private void buildLetterTable()
	{
		Character[] alphabet={'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
		'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		for(int i=0;i<26;i++)
		{
			letterTable.put(alphabet[i], primes[i]);
		}
	}
	/**Compute the hash code and add the word to the hash table anagramTable.
	 * @param s The word will be added 
	 * @exception IllegalArgumentException when String s is null*/
	private void addWord(String s)
	{
		if(s==null)
		{
			throw new IllegalArgumentException("String s cannot be null.");
		}
		Long key=myHashCode(s);
		if(anagramTable.containsKey(key))//There is at least a anagram in the list.
		{
			anagramTable.get(key).add(s);
			return;
		}
		else//Creast a new ArrayList and add the new string to it.
		{	
			anagramTable.put(key, new ArrayList<String>());
			anagramTable.get(key).add(s);
			return;
		}
		
	}
	/**Comput the hash code using the given string.
	 * post: All the anagrams of a word should have the same hash code.
	 * @param s The word will be added
	 * @return Return a hash code of s and return 0 when string is null.  */
	private Long myHashCode(String s)
	{
		if(s!=null)
		{
			char[] c=s.toCharArray();
			Long result=1L;
			//The product of the combination of prime numbers of the corresponding letter.
		    for(char cc:c)
		    {
		    	result=result*letterTable.get(cc);
		    }
		      return result;
		}
		 else
		 {
			 return 0L;
		 }
	}
	/**Receive the name of a text file containing words,
	 * one per line, and build the hash table anagramTable.
	 * @param s the name of file
	 * @throws IOException The file could not be read
	 * */
	private void processFile ( String s) throws IOException 
	{
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine ;
		while((strLine = br.readLine ()) != null ) 
		{
			this.addWord(strLine);
		}
		br.close();
	}
	
	/**Return a list of entries that have the largest number of anagrams.
	 * @return  An Arraylist of entries that have the largest number of anagrams
	 * */
	private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries()
	{
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxLists=new ArrayList<>();
		int max=0;
		//Find the largest number of anagrams
		for (Map.Entry<Long,ArrayList<String>> e: anagramTable.entrySet()) 
		{
			if(e.getValue().size()>max)
			{
				max=e.getValue().size();	
			} 
		}
		//Put the list of them into an arrayList
		for (Map.Entry<Long,ArrayList<String>> e: anagramTable.entrySet()) 
		{
			if(e.getValue().size()==max)
			{
				maxLists.add(e);
			} 
		}
		return maxLists;
	}
	
	
	/**Main method*/
	public static void main(String[] args)
	{
		Anagrams a = new Anagrams();
		
		final long startTime = System.nanoTime();
		try {
			a.processFile("words_alpha.txt");
			} catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		ArrayList< Map.Entry<Long , ArrayList <String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime ;
		final double seconds = (( double )estimatedTime /1000000000);
		System.out.println("Elapsed Time:"+ seconds );
		for(int i=0;i<maxEntries.size();i++)
		{
			System.out.println("Key of max anagrams:"+maxEntries.get(i).getKey());
			System.out.println("List of max anagrams:"+maxEntries.get(i).getValue());
			System.out.println("Length of list of max anagrams:"+maxEntries.get(i).getValue().size());
		}
		//System.out.println(" List of max anagrams : "+ maxEntries);
	}

}
