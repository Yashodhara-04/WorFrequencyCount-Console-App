package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dao.WordFrequencyRepository;

public class WordFrequencyService implements IWordFrequencyService {
	
	WordFrequencyRepository wordFrequencyRepository = null;
	
	public WordFrequencyService()
	{
		wordFrequencyRepository = new WordFrequencyRepository();
	}

	public HashMap<String,Integer> findWordFrequency(String input)
	{
		HashMap<String, Integer> wordFreq = wordFrequencyRepository.findWordFrequency(input);
		HashMap<String,Integer> res = sort(wordFreq);
		return res;		
	}
	
	//Sort the HashMap
	public HashMap<String,Integer> sort(HashMap<String,Integer> wordFreq)
	{
		LinkedHashMap<String,Integer> res = new LinkedHashMap<>();
		List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(wordFreq.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String,Integer>>()
		{
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				if((o1.getValue() - o2.getValue()) == 0)
				{
					return o1.getKey().toLowerCase().compareTo(o2.getKey().toLowerCase());
				}
				else
				{
					return o1.getValue() - o2.getValue();
				}
			}
		});
		
		for(Map.Entry<String, Integer> map : list)
		{
			res.put(map.getKey(), map.getValue());
		}
		return res;
		
	}
}
