/*
 * Annie Shen (ashen7@ucsc.edu)
 * CMPS 12B/M Lab7
 * Due Dec 2
 * DictionaryTest.java
 */

public class DictionaryTest{
    public static void main(String[] args){
	    String S;
		Dictionary D = new Dictionary();
		
		System.out.println("Empty: " + D.isEmpty());
        System.out.println("Size: " + D.size());
        System.out.println();
        
        D.insert("1","a");
		D.insert("2","b");
		D.insert("3","c");
	    D.insert("4","d");
	    D.insert("5","e");
	    D.insert("6","f");
	    D.insert("7","g");
	    System.out.println(D);
	    
	    S = D.lookup("1");
	    System.out.println("key=1 "+(S==null?"not found":("value="+S)));
	    S = D.lookup("8");
	    System.out.println("key=8 "+(S==null?"not found":("value="+S)));
        System.out.println();
        
        D.delete("3");
        D.delete("4");
        
        System.out.println("Empty: " + D.isEmpty());
        System.out.println("Size: " + D.size());
        
        D.makeEmpty();
        
        System.out.println("Empty: " + D.isEmpty());
        System.out.println("Size: " + D.size());
	}
}
