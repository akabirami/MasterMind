import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class MasterMind {

	static String computerWord;
	static Map<String,String> mapOfWords;
	static Map<String,String> refinedMap;
	static ArrayList<String> guessedwords=new ArrayList<String>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int level;
		mapOfWords=AnagramUtil.getMapOfWords(0);
		String continue_game;
		do{
	
		System.out.println("Select 1.easy 2.medium 3.difficult");
		Scanner sc=new Scanner(System.in);
		int difficulty=Integer.parseInt(sc.nextLine());
		level=3+difficulty;
		refinedMap=getDesiredLength(level);
		computerWord=getComputerWord(refinedMap);
		System.out.println(computerWord);
		System.out.println("THINK A "+ level +" LETTER WORD");
		int count=1;
		while(true){
			String guess=getComputerWord(refinedMap);
			System.out.println("STEP: "+count++);
			System.out.println("IS IT "+guess);
			guessedwords.add(guess);
			String ans=sc.nextLine();
			Integer k = 0;
			if(ans.toLowerCase().equals("you win")){
				System.out.println("You lose :P:D");
				break;
			}
			k=Integer.parseInt(ans);
			String user_guess=sc.nextLine();
			if(user_guess.equals(computerWord)){
				System.out.println("YOU WIN !! CONGRATS :D :) ");
				break;
			}
			else
			System.out.println(checkCount(computerWord,user_guess));
			
			if(k==0)
				refinedMap=NoMatch(guess,refinedMap);
			else if(k==level)
				{fullMatch(guess,refinedMap);
				break;
				}
			else
				refinedMap=intrimMatch(k,guess,refinedMap);
			
		}
		System.out.println("Retry ?? y/n ?");
		continue_game=sc.nextLine();
		}while(continue_game.toLowerCase().equals("y"));
	}
	
	public static Map<String,String> getDesiredLength(int k){
		
		Map<String, String> result=new HashMap<String,String>();
		Map<String,String> anagrams=mapOfWords;
		
		for(String s:anagrams.keySet()){
			if(s.length()==k){
				result.put(s, anagrams.get(s));
			}
		}
		return result;
		
	}

	public static String getComputerWord(Map<String,String> wordMaps){
		ArrayList<String> valuesList = new ArrayList<String>(wordMaps.keySet());
		String randomWord;
		while(true){
			boolean present=false;
			int randomIndex = new Random().nextInt(valuesList.size());
			String randomValue = valuesList.get(randomIndex);
			
			if(checkForUnique(randomValue)){
			 String value=wordMaps.get(randomValue);
			 String words[]=value.split("\\s");
			 int randomIndex2=new Random().nextInt(words.length);
			 randomWord=words[randomIndex2];
		     for(int i=0;i<guessedwords.size();i++){
		    	 if(randomWord.equals(guessedwords.get(i))){
		    		 present=true;
		    	 }
		     }
			 if(!present)
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
	
	public static Map<String,String> NoMatch(String guess,Map<String,String> previousMap){
		Map<String,String> refinedMap=new HashMap<String,String>(); 

		for(String s:previousMap.keySet()){
		boolean check=false;
		   for(int i=0;i<guess.length();i++){
			   if(s.indexOf(guess.charAt(i))>=0){
				   check=true;
				   break;
			   }
		   }
		   if(!check){
			   refinedMap.put(s,previousMap.get(s));
		   }
		}
		
		return refinedMap;
		
	}
	public static void fullMatch(String guess,Map<String,String> refined){
		String matchedString=matchString(guess,refined);
		if(matchedString==null){
			System.out.println("Please Guess a valid word !");
		}
		else{
		String finalGuesses[]=matchedString.split("\\s+");
		for(String s:finalGuesses){
			if(!s.equals(guess)){
				System.out.println("IS IT "+ s);
				Scanner sc=new Scanner(System.in);
				String ans=sc.nextLine();
				if(ans.toLowerCase().equals("you win")){
				System.out.println("you lose :P:D");
				 break;	
				}
				else
				guess=s;
			}
		}
		}
	}
	
	public static String matchString(String guess,Map<String,String> previousMap){
        char[] chars = guess.toCharArray();
        Arrays.sort(chars);
        String sortedGuess = new String(chars);
      
		for(String s:previousMap.keySet()){
			if(s.equals(sortedGuess)){
				return previousMap.get(s);
			}
		}
		return null;
	}
	
	public static Map<String,String> intrimMatch(int count,String guess,Map<String,String> previousMap){
		Map<String,String> newMap=new HashMap<String,String>();
		for(String s:previousMap.keySet())
		{
			if(checkCount(s,guess)>=count){
				newMap.put(s,previousMap.get(s));
			}
		}
		
		return newMap;
	}

}
