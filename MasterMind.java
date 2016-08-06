import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

public class MasterMind {

	static String computerWord;
	static Map<String,String> mapOfWords;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int level=4;
		mapOfWords=AnagramUtil.getDesiredLength(level);
		computerWord=getComputerWord();
		System.out.println(checkCount("bush","boss"));
	}
	
	public static String getComputerWord(){
		ArrayList<String> valuesList = new ArrayList<String>(mapOfWords.keySet());
		String randomWord;
		while(true){
			int randomIndex = new Random().nextInt(valuesList.size());
			String randomValue = valuesList.get(randomIndex);
			
			if(checkForUnique(randomValue)){
			 String value=mapOfWords.get(randomValue);
			 String words[]=value.split("\\s");
			 randomWord=words[0];
			 break;
			}
		}
		
		return randomWord;
	}
	
	public static boolean checkForUnique(String str) {
		 for (int i=0;i < (str.length()-1);i++)
	        {
	            if (str.charAt(i) == str.charAt(i+1))
	            return false;
	        }
	        return true;
    }
	
	public static Integer checkCount(String original,String guess){
		int counter=0;
		for(int i=0;i<original.length();i++)
		{
			for(int j=0;j<guess.length();j++){
				if(original.charAt(i)==guess.charAt(j)){
					counter++;
					break;
				}
			}
		}
		return counter;
	}

}
