package controller;

import service.IWordFrequencyService;
import service.WordFrequencyService;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class WordFrequencyController {
	

	public void runApplication()
	{
		while(true)
		{
			String ch =  getUserInput("\nChoose 1 0r 2 \n1. Sentence 2. Path");
			String input = "";
			if( ch.equals("1")) 
			{
				input = getUserInput("\nEnter input String: "); 
			}
			else if(ch.equals("2"))
			{
				input = getUserInput("\nEnter input Path: ");
			}
			findWordFrequency(input);
		}
	}
	
	public String getUserInput(String message)
	{
		System.out.println(message);
		Scanner sc = new Scanner(System.in);
		String val = sc.nextLine();
		return val;
	}
	
	public void findWordFrequency(String input)
	{
		IWordFrequencyService wordFrequencyService = new WordFrequencyService();
		if(isValidPath(input))
		{
			input = getFileContent(input);
		}
		HashMap<String, Integer> wordFreq = wordFrequencyService.findWordFrequency(input);
		for(Map.Entry<String,Integer> map : wordFreq.entrySet())
		{
			System.out.println(map.getKey() + " : "+ map.getValue());
		}
	}
	
	public static boolean isValidPath(String path)
	{
        try 
        {
            Paths.get(path);
            return true;
        } catch (Exception ex) 
        {
            return false;
        }
    }
	
	public String getFileContent(String input)
	{

		StringBuilder contentBuilder = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(input));
			String line;
            try {
				while ((line = br.readLine())!= null)
				{
				    contentBuilder.append(line).append("\n");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Cannot read the content");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File doesn't exits in the path " + input);
		}
		return contentBuilder.toString();
	}
}
