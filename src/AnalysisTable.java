package main;

public class AnalysisTable {
	private String key;
	private DictionaryEntry entry;
	public TableRow linearProbing = new TableRow();
	public TableRow quadraticProbing = new TableRow();
	public TableRow separateChaining = new TableRow();
	public TableRow doubleHashingProbing = new TableRow();
	public AnalysisTable(){
		linearProbing.dataStructure = "Linear Probing";
		quadraticProbing.dataStructure = "Quadratic Probing";
		separateChaining.dataStructure = "Separate Chaining";
		doubleHashingProbing.dataStructure = "Double Hashing";
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public DictionaryEntry getEntry() {
		return entry;
	}

	public void setEntry(DictionaryEntry entry) {
		this.entry = entry;
	}

	public String toHTMLString(){
		StringBuilder table = new StringBuilder();
		table.append("<h3>Total Words: "+ Dictionary.numberOfElements+"</h3>");
		table.append("<table>");
		table.append("<th>Data Structure</th><th>Table Size</th><th>Lambda</th><th>Success</th><th>Itms Investigated</th>");
		table.append("<tr><td>" + linearProbing.dataStructure + "</td><td>" + linearProbing.tableSize + "</td><td>" + linearProbing.lambda + "</td><td>" + linearProbing.success + "</td><td>" + linearProbing.itemsInvestigated + "</td></tr>");
		table.append("<tr><td>" + quadraticProbing.dataStructure + "</td><td>" + quadraticProbing.tableSize + "</td><td>" + quadraticProbing.lambda + "</td><td>" + quadraticProbing.success + "</td><td>" + quadraticProbing.itemsInvestigated + "</td></tr>");
		table.append("<tr><td>" + separateChaining.dataStructure + "</td><td>" + separateChaining.tableSize + "</td><td>" + separateChaining.lambda + "</td><td>" + separateChaining.success + "</td><td>" + separateChaining.itemsInvestigated + "</td></tr>");
		table.append("<tr><td>" + doubleHashingProbing.dataStructure + "</td><td>" + doubleHashingProbing.tableSize + "</td><td>" + doubleHashingProbing.lambda + "</td><td>" + doubleHashingProbing.success + "</td><td>" + doubleHashingProbing.itemsInvestigated + "</td></tr>");
		table.append("</table>");
		return table.toString();
	}
	
	class TableRow{
		String dataStructure;
		String tableSize;
		String lambda;
		String success;
		String itemsInvestigated;
	}
	
}


