import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Tokens Used
/*keyword -> if | then | else | begin | end
special -> ( | ) | [ | ] | + | - | = | , | ;|{ | }
digit -> 0|1|2|3|4|5|6|7|8|9
character -> a|b|c ... |z|A|B|C ... |Z
real -> digit.digit
 */


public class TokenSummary {

	static Map<String,Integer> keywordMap = new HashMap<String,Integer>();
	static Map<String,Integer> specialMap = new HashMap<String,Integer>();
	static Map<String,Integer> digitMap = new HashMap<String,Integer>();
	static Map<String,Integer> characterMap = new HashMap<String,Integer>();
	static Map<String,Integer> realMap = new HashMap<String,Integer>();

	public static void main(String[] args) throws IOException  {
		if(args.length==0) {
			System.out.println("File not found");
			return;
		}
		String textFile=args[0];

		DataInputStream input=new DataInputStream(new BufferedInputStream(new FileInputStream(textFile)));

		String[] keyword = {"if","then","else","begin","end"};
		List<String> keywordList = Arrays.asList(keyword);

		String[] special = {"{","}",")","[","]","+","-","=",",",";"};
		List<String> specialList = Arrays.asList(special);

		List<String> digitList = new ArrayList<String>();

		for (int i = 48; i<=57; i++) {
			digitList.add(Character.toString((char) i));
		}	

		List<String> characterList = new ArrayList<String>();

		for (int i = 97; i<=122; i++) {
			characterList.add(Character.toString((char) i));
		}
		for (int i = 65; i<=90; i++) {
			characterList.add(Character.toString((char) i));
		}

		readInputAndPutToMap(input, keywordList, specialList, characterList,digitList);

		printResults();

	}

	private static void readInputAndPutToMap(DataInputStream input, List<String> keywordList, List<String> specialList,
			List<String> characterList,List<String> digitList) throws IOException {
		while(input.available()>0) {
			String line=input.readLine();
			String[] words = line.split(" ");
			for(String word: words){
				if(keywordList.contains(word)) {
					if (keywordMap.containsKey(word)) {
						keywordMap.put(word,keywordMap.get(word)+1);
					}else
						keywordMap.put(word, 1);

				}
				else if(specialList.contains(word)) {
					if (specialMap.containsKey(word)) {
						specialMap.put(word,specialMap.get(word)+1);
					}else
						specialMap.put(word, 1);

				}
				else if(characterList.contains(word)) {
					String lowerCaseWord=word.toLowerCase();
					if (characterMap.containsKey(lowerCaseWord)) {
						characterMap.put(lowerCaseWord,characterMap.get(lowerCaseWord)+1);
					}else
						characterMap.put(lowerCaseWord, 1);

				}

				else if (digitList.contains(word)) {
					if (digitMap.containsKey(word)) {
						digitMap.put(word,digitMap.get(word)+1);
					}else {
						digitMap.put(word, 1);
					}
				}


				else if (isreal(word)) {
					if (realMap.containsKey(word)) {
						realMap.put(word,realMap.get(word)+1);
					}else
						realMap.put(word, 1);
				}

			}

		}
	} 

	public static void printResults() {
		int keywordCount=0;
		int specialCount=0;
		int digitCount=0;
		int characterCount=0;
		int realCount=0;

		for(Map.Entry<String,Integer> keywordEntry: keywordMap.entrySet() ) {
			System.out.println(" The token that appeared is : "+keywordEntry.getKey()+": The number of times it appeared is : "+ keywordEntry.getValue()+" It belongs to KEYWORD class ");
			keywordCount+=keywordEntry.getValue();
		}
		for(Map.Entry<String,Integer> specialEntry: specialMap.entrySet() ) {
			System.out.println(" The token that appeared is : "+specialEntry.getKey()+": The number of times it appeared is :"+ specialEntry.getValue()+": It belongs to SPECIAL class ");
			specialCount+=specialEntry.getValue();
		}
		for(Map.Entry<String,Integer> digitEntry: digitMap.entrySet() ) {
			System.out.println(" The token that appeared is :"+digitEntry.getKey()+": The number of times it appeared is :"+ digitEntry.getValue()+": It belongs to DIGIT class ");
			digitCount+=digitEntry.getValue();
		}
		for(Map.Entry<String,Integer> characterEntry: characterMap.entrySet() ) {
			System.out.println(" The token that appeared is :"+characterEntry.getKey()+" The number of times it appeared is :"+ characterEntry.getValue()+": It belongs to CHARACTER class ");
			characterCount+=characterEntry.getValue();
		}
		for(Map.Entry<String,Integer> realEntry: realMap.entrySet() ) {
			System.out.println(" The token that appeared is :"+realEntry.getKey()+": The number of times it appeared is :"+ realEntry.getValue()+": It belongs to REAL class ");
			realCount+=realEntry.getValue();
		}
		System.out.println(" Number of times the keyword token appeared is: "+keywordCount);
		System.out.println(" Number of times the special token appeared is: "+specialCount);
		System.out.println(" Number of times the digit token appeared is: "+digitCount);
		System.out.println(" Number of times the character token appeared is: "+characterCount);
		System.out.println(" Number of times the real token appeared is: "+realCount);

	}

	public static boolean isreal(String s) {
		String[] value = s.split("\\.");
		if(value.length!=2 || value.length <=0) {
			return false;
		}
		try {
			int x = Integer.parseInt(value[0]);
			int y = Integer.parseInt(value[1]);
			return true;
		}catch(NumberFormatException e){
			return false;
		}


	}

}


