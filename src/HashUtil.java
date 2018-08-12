package main;

public class HashUtil {
	
	public static int simpleHash(String input) {
	    int hash = 0;
	    int i = 1;
	    for (char c : input.toCharArray()) {
	        hash += c*i;
	        i++;
	    }
	    return hash;
	}
	
	public static int simpleHash2(String input) {
	    int hash = 0;
	    int i = 0;
	    for (char c : input.toCharArray()) {
	    	i++;
	        hash += c*c*i;
	    }
	    return hash;
	}
}
