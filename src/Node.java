package main;

public class Node {
	
	private DictionaryEntry value;
	private Node nextNode;

	public DictionaryEntry getValue() {
		return value;
	}

	public void setValue(DictionaryEntry value) {
		this.value = value;
	}

	public Node getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}

}
