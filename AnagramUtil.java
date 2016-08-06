
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AnagramUtil {

	private static Map<String,String> anagramList;
	//private static Map<String,String> anagramList2;
	//private static Map<String,String> anagramList3;

	
	public static void main(String[] args) {
		
		anagramList=getMapOfWords(1);
		
		for(String s:anagramList.keySet()){
			System.out.println(s+"-----"+anagramList.get(s));
		}
		
		
	}
	
	private static String printAnargam(Map<String, String> anagram) {
		// TODO Auto-generated method stub
		String clusters="";
		for(String s: anagram.keySet()){
			if(anagram.get(s).contains(" ")){
				clusters+=anagram.get(s)+"\n";
			}
		}
		return clusters;
		
	}
	
	
	public static Map<String,String> getDesiredLength(int k){
	
		Map<String, String> result=new HashMap<String,String>();
		Map<String,String> anagrams=getMapOfWords(0);
		
		for(String s:anagrams.keySet()){
			if(s.length()==k){
				result.put(s, anagrams.get(s));
			}
		}
		return result;
		
	}

	public static Map<String,String> getMapOfWords(int k){
		 Map<String,String> words_count = new HashMap<String,String>();
			
			try {
				FileInputStream fstream = new FileInputStream("sowpods.txt");
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

				String strLine;
				while ((strLine = br.readLine()) != null)   {
				  
					String sorted=sortLetters(strLine);
				     
					     if(words_count.keySet().contains(sorted))
					     { 	 
					         words_count.put(sorted, words_count.get(sorted)+" "+strLine);
					     }
					     else
					         words_count.put(sorted,strLine);

					}	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		if(k==0)
			return words_count;
		else 
			 return getSecondaryMapOfWords(words_count,k);		
		
	}
	
	public static Map<String,String> getSecondaryMapOfWords( Map<String,String> inputMap,int k){
	    Map<String,String> second_list = new HashMap<String,String>();
	    ArrayList<String> intrim=new ArrayList<String>();
	    
	    for(String s:inputMap.keySet()){
	 
	      intrim.clear();
	      combinations(s,s.length()-1,0,intrim,new char[s.length()-1]);

	       for(String str:intrim){
	    	if(second_list.keySet().contains(str)){
	    		second_list.put(str, second_list.get(str)+" "+inputMap.get(s));	
	    	}
	    	else
	    	{
	    		second_list.put(str,inputMap.get(s));	
	    	}
	       }
	    } 
	    if(k==1)
	    	return second_list;
	    else
	    	return getSecondaryMapOfWords(second_list,1);
	}

	
	static void combinations(String arr, int len, int startPosition,ArrayList<String> intrim,char[] result){
        if (len == 0){
            intrim.add(new String(result));
            return;
        } 
        for (int i = startPosition; i <= arr.length()-len; i++){
            result[result.length - len] = arr.charAt(i);
            combinations(arr, len-1, i+1, intrim,result);
        }
    }    
		
	
	
	private static String sortLetters(String strLine) {
		// TODO Auto-generated method stub
		char[] chars = strLine.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars).toLowerCase();
		return sorted;
	}
}
