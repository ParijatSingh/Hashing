package main;

import java.text.DecimalFormat;

public class SeparateChainingDictionary {
	int tableSize;
	Node[] dictionary;
	public SeparateChainingDictionary(int tableSize){
		this.tableSize = tableSize;
		dictionary = new Node[tableSize];		
	}
	
	public void addEntry(DictionaryEntry entry){
		//System.out.println("Adding "+ entry.getKey());
		int hash = HashUtil.simpleHash(entry.getKey());
		int tableNum = hash % (tableSize-1);
		Node n = new Node();
		n.setValue(entry);
		if(dictionary[tableNum] == null){
			dictionary[tableNum] = n;
		}else{
			n.setNextNode(dictionary[tableNum]);
			// add in the front of the queue
			dictionary[tableNum] = n;			
		}
	}
	
	public void searchAndAnalyze(AnalysisTable aTable){
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		String lambda = df.format((float)Dictionary.numberOfElements/tableSize);
		int hash = HashUtil.simpleHash(aTable.getKey());
		int tableNum = hash % tableSize;
		int itemsInspected = 0;
		aTable.separateChaining.lambda = lambda;
		aTable.separateChaining.tableSize = "" + tableSize;
		itemsInspected++;
		if(dictionary[tableNum] == null){
			aTable.separateChaining.success = "yes";
			aTable.separateChaining.itemsInvestigated = "" + itemsInspected;
			return;
		}else{
			Node n = dictionary[tableNum];
			do{
				itemsInspected++;
				if(n.getValue().getKey().equals(aTable.getKey())){
					aTable.separateChaining.success = "yes";
					aTable.separateChaining.itemsInvestigated = "" + itemsInspected;
					aTable.setEntry(n.getValue());
					return;
				}
			}while((n = n.getNextNode()) != null);
		}
		aTable.separateChaining.success = "yes";
		aTable.separateChaining.itemsInvestigated = "" + itemsInspected;
		return;
	}
}
