//for testing Dictionary.java

class DictionaryTest{
	public static void main(String[] args){
		Dictionary D = new Dictionary();
		
		System.out.println(D.isEmpty()); //true
		D.insert("1", "A"); //insert
		System.out.println(D.size()); //size of 1
		System.out.println(D.lookup("1"));
		
		//throws DuplicateKeyException by inserting a duplicate key
		try{
			D.insert("1", "Duplicate"); 
		} catch(DuplicateKeyException e){
			System.out.println("Caught Exception " + e);
        	System.out.println("System continues:\n");
		}
		
		D.insert("2", "B");
		D.insert("3", "C");
		D.insert("4", "D");
		
		System.out.println("Size: " + D.size()); //size of 4
		System.out.println("Lookup 2: " + D.lookup("2")); //B
		System.out.println("Lookup 3: " + D.lookup("3")); //C
		
		D.delete("3");
		System.out.println("Size: " + D.size()); //size of 3 now
		
		//throws KeyNotFoundException when trying to delete a non-existing key
		try{
			D.delete("3"); 
		} catch(KeyNotFoundException e){
			System.out.println("Caught Exception " + e);
        	System.out.println("System continues:\n");
		}
		System.out.println("Size: " + D.size());//now 3
		
		System.out.println(D.toString());
		
		//make D empty
		D.makeEmpty();
		System.out.println("Size: " + D.size());
	}
}
