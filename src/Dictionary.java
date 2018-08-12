package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Dictionary {
	private static SeparateChainingDictionary scDictionary;
	private static LinearProbingDictionary lpDictionary;
	private static QuadraticProbingDictionary qpDictionary;
	private static DoubleHashingDictionary dhDictionary;
	public static int numberOfElements = 0;
	public static void initialize(String fromFile) throws IOException{
		if(scDictionary == null){
			scDictionary = new SeparateChainingDictionary(200003);
			lpDictionary = new LinearProbingDictionary(200003);
			qpDictionary = new QuadraticProbingDictionary(200003);
			dhDictionary = new DoubleHashingDictionary(200003);
			loadDictionary(fromFile);
		}
	}
	
	public static void searchAndAnalyze(AnalysisTable aTable){
		scDictionary.searchAndAnalyze(aTable);
		lpDictionary.searchAndAnalyze(aTable);
		qpDictionary.searchAndAnalyze(aTable);
		dhDictionary.searchAndAnalyze(aTable);
	}
	
	private static void loadDictionary(String fromFile) throws IOException {
		ZipFile zipFile = new ZipFile(fromFile);

	    Enumeration<? extends ZipEntry> entries = zipFile.entries();
	    
	    int i = 0;
	    while(entries.hasMoreElements()){
	        ZipEntry entry = entries.nextElement();
	        if(entry.getName().equals("dictionary.txt")){
	        	InputStream stream = zipFile.getInputStream(entry);
	        	BufferedReader dictionaryReader = new BufferedReader(new InputStreamReader(stream));
	        	String line = null;
	        	while((line = dictionaryReader.readLine()) != null){
	        		numberOfElements++;
	        		String[] parts = line.split("\\|");
	        		String key = parts[0];
	        		String partOfSpeech = parts[1];
	        		String definition = parts[2];
	        		DictionaryEntry dictionaryEntry = new DictionaryEntry();
	        		dictionaryEntry.setKey(key);
	        		dictionaryEntry.setPartOfSpeech(partOfSpeech);
	        		dictionaryEntry.setDefinition(definition);
	        		if(numberOfElements % 5000 == 0) {
	        			System.out.println("Loaded "+ numberOfElements+ " words");
	        		}
	        		// add to SeparateChain Dictionary
	        		scDictionary.addEntry(dictionaryEntry);
	        		lpDictionary.addEntry(dictionaryEntry);
	        		qpDictionary.addEntry(dictionaryEntry);
	        		dhDictionary.addEntry(dictionaryEntry);
	        		//System.out.println("added: " + i);
	        		//if(i == 25000) break; 
	        		i++;
	        	}
	        }
	    }
	}
}
