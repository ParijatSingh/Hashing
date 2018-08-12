package main;

import java.text.DecimalFormat;

public class QuadraticProbingDictionary {
	
	int tableSize;
	DictionaryEntry[] dictionary;
	int entriesInDictionary;
	
	public QuadraticProbingDictionary(int tableSize){
		this.tableSize = tableSize;
		dictionary = new DictionaryEntry[tableSize];
	}
	
	public void addEntry(DictionaryEntry entry){
		//System.out.println("Trying to add :"+entry.getKey());
		//if dictionary is full return immediately
		if(entriesInDictionary == tableSize){
			return;
		}
		
		int hash = HashUtil.simpleHash(entry.getKey());
		int tableNum = hash % (tableSize-1);
		
		int i = tableNum;
		int x = 0;
		while(dictionary[i] != null) {
			x++;
			i = tableNum + (int)Math.pow(x, 2);
			// i can become very large and may exceed the Integer Boundary. Break if it happens
			if(i < 0) return;
			if(i >= tableSize){
				i = i%tableSize;
			}
		}
		if(dictionary[i] == null) {
			//System.out.println("Adding "+ entry.getKey());
			dictionary[i] = entry;
			//System.out.println(entriesInDictionary +" - Adding at "+i+": "+entry.getKey());
			//successfully added the key - lets increment the entries count in dictionary
			entriesInDictionary++;
		}
	}

	
	public void searchAndAnalyze(AnalysisTable aTable){
		//print();
		int hash = HashUtil.simpleHash(aTable.getKey());
		int tableNum = hash % tableSize;
		int itemsInspected = 0;
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		String lambda = df.format((float)Dictionary.numberOfElements/tableSize);
		aTable.quadraticProbing.lambda = lambda;
		aTable.quadraticProbing.tableSize = "" + tableSize;
		itemsInspected++;
		DictionaryEntry entry = dictionary[tableNum];
		int i = tableNum;
		int x = 0;
		while(dictionary[i] != null) {
			//System.out.println(i);
			itemsInspected++;
			x++;
			if(entry.getKey().equals(aTable.getKey())){
				// OK we found the match
				aTable.quadraticProbing.success = "yes";
				aTable.quadraticProbing.itemsInvestigated = "" + itemsInspected;
				return;				
			}
			i = tableNum + (int)Math.pow(x,2);
			if(i >= tableSize){
				i = i%tableSize;				
			}
			if(i<0) break;
			//System.out.println(i);
			try {
				entry = dictionary[i];
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// if we have looped through all the entries and found no match
			if(itemsInspected >= entriesInDictionary) break;
			//System.out.println(i);
		}
		// if we reach here it means we found NULL or we have looped through and back to same index - suggesting the word never got entered
		aTable.quadraticProbing.success = "no";
		aTable.quadraticProbing.itemsInvestigated = "" + itemsInspected;
		return;
	}

	
	public void print() {
		for(int i = 0; i < dictionary.length; i++)
		{
			System.out.println(i + ": " + (dictionary[i] != null ? dictionary[i].getWord() : ""));
		}
	}
}

