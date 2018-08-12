package main;

import java.text.DecimalFormat;

public class LinearProbingDictionary {
	
	int tableSize;
	DictionaryEntry[] dictionary;
	
	public LinearProbingDictionary(int tableSize){
		this.tableSize = tableSize;
		dictionary = new DictionaryEntry[tableSize];
	}
	
	public void addEntry(DictionaryEntry entry){
		int hash = HashUtil.simpleHash(entry.getKey());
		int tableNum = hash % (tableSize-1);
		
		int i = tableNum;
		while(dictionary[i] != null) {
			if(i == tableSize-1) {
				i = 0;
			}
			else i++;
			// if we have looped through and back to same index means, it is time to get out to avoid infinite loop
			if(i == tableNum) break;
			//System.out.println(i);
		}
		if(dictionary[i] == null) {
			//System.out.println("Adding "+ entry.getKey());
			dictionary[i] = entry;
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
		aTable.linearProbing.lambda = lambda;
		aTable.linearProbing.tableSize = "" + tableSize;
		itemsInspected++;
		DictionaryEntry entry = dictionary[tableNum];
		int i = tableNum;
		while(dictionary[i] != null) {
			itemsInspected++;
			if(entry.getKey().equals(aTable.getKey())){
				// OK we found the match
				aTable.linearProbing.success = "yes";
				aTable.linearProbing.itemsInvestigated = "" + itemsInspected;
				return;				
			}
			if(i == tableSize-1) i = 0;
			else i++;
			entry = dictionary[i];
			// if we have looped through and back to same index means, it is time to get out to avoid infinite loop
			if(i == tableNum) break;
			//System.out.println(i);
		}
		// if we reach here it means we found NULL or we have looped through and back to same index - suggesting the word never got entered
		aTable.linearProbing.success = "no";
		aTable.linearProbing.itemsInvestigated = "" + itemsInspected;
		return;
	}
	
	public void print() {
		for(int i = 0; i < dictionary.length; i++)
		{
			System.out.println(i + ": " + (dictionary[i] != null ? dictionary[i].getWord() : ""));
		}
	}
}

