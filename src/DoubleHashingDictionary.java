package main;

import java.text.DecimalFormat;

public class DoubleHashingDictionary {
	
	int tableSize;
	DictionaryEntry[] dictionary;
	int entriesInDictionary;
	
	public DoubleHashingDictionary(int tableSize){
		this.tableSize = tableSize;
		dictionary = new DictionaryEntry[tableSize];
	}
	
	public void addEntry(DictionaryEntry entry){
		//if dictionary is full return immediately
		if(entriesInDictionary == tableSize){
			return;
		}
		int hash = HashUtil.simpleHash(entry.getKey());
		int tableNum = hash % (tableSize-1);
		// if found empty in first attempt then add and return
		if(dictionary[tableNum] == null){
			dictionary[tableNum] = entry;
			return;
		}	
		
		// lets do the double hashing now
		int hash2 = HashUtil.simpleHash2(entry.getKey());
		
		int i = tableNum;
		int x = 0;
		try {
			while(dictionary[i] != null) {
				x++;
				i = (hash + x*hash2) % tableSize;
				//i = Math.abs(i);
				//System.out.println(i + " >>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(dictionary[i] == null) {
			//System.out.println("Adding "+ entry.getKey());
			dictionary[i] = entry;
			entriesInDictionary++;
			//System.out.println("Total " + entriesInDictionary +" Adding entry at " + i + " " + entry.getKey());
		}
	}

	
	public void searchAndAnalyze(AnalysisTable aTable){
		//print();
		int hash = HashUtil.simpleHash(aTable.getKey());
		int tableNum = hash % tableSize;
		int itemsInspected = 1;
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		String lambda = df.format((float)Dictionary.numberOfElements/tableSize);
		aTable.doubleHashingProbing.lambda = lambda;
		aTable.doubleHashingProbing.tableSize = "" + tableSize;
		if(dictionary[tableNum] == null){
			aTable.doubleHashingProbing.success = "no";
			aTable.doubleHashingProbing.itemsInvestigated = "" + itemsInspected;
			return;
		}else{
			// lets probe by double hashing now
			int hash2 = HashUtil.simpleHash2(aTable.getKey());
			itemsInspected++;
			DictionaryEntry entry = dictionary[tableNum];
			int i = tableNum;
			int x = 0;
			while(dictionary[i] != null) {
				x++;
				if(entry.getKey().equals(aTable.getKey())) {
					aTable.doubleHashingProbing.success = "yes";
					aTable.doubleHashingProbing.itemsInvestigated = "" + itemsInspected;
					return;
				}
				i = (hash + x*hash2) % tableSize;
				//i = Math.abs((hash + x*hash2)) % tableSize;
				entry = dictionary[i];
			}
		}
		aTable.doubleHashingProbing.success = "no";
		aTable.doubleHashingProbing.itemsInvestigated = "" + itemsInspected;
		return;
	}
	
	public void print() {
		for(int i = 0; i < dictionary.length; i++)
		{
			System.out.println(i + ": " + (dictionary[i] != null ? dictionary[i].getWord() : ""));
		}
	}
}

