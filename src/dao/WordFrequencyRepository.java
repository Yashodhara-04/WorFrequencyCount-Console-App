package dao;

import java.util.Arrays;
import java.util.HashMap;

public class WordFrequencyRepository {

	public HashMap<String,Integer> findWordFrequency(String input)
	{
		String[] strArray = input.split("[\\s+,!+/:;]+");
		HashMap<String,Integer> wordFreq = new HashMap<>();
		Arrays.stream(strArray).forEach(n->wordFreq.put(n,wordFreq.getOrDefault(n,0)+1));		
		return wordFreq;		
	}
	
	
}
