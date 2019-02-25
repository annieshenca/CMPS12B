/*
 * Annie Shen (ashen7@ucsc.edu)
 * CMPS 12B/M Lab6
 * Testing List.java
*/

class ListTest{
    public static void main(String[] args){
        List<String> A = new List<String>();
        List<String> B = new List<String>();
        List<List<String>> C = new List<List<String>>();
//        List<String> D = new List<String>();
        
        A.add(1, "bed");
        A.add(2, "pillow");
        A.add(3, "sheets");
        B.add(1, "pen");
        B.add(2, "eraser");
        B.add(3, "paper");
        C.add(1, A);
        C.add(2, B);
//        D.add(1, "bed");
//        D.add(2, "pillow");
//        D.add(3, "sheets");
    
        System.out.println("List A: " + A);
        System.out.println("List B: " + B);
        System.out.println("List C: " + C);
        System.out.println("A.equals(A): " + A.equals(A));
        System.out.println("B.equals(B): " + B.equals(B));
        System.out.println("C.equals(C): " + C.equals(C));
        System.out.println("A.equals(B): " + A.equals(B));
//        System.out.println("A.equals(D): " + A.equals(D)); //print result: false
        
        System.out.println("A.empty(): " + A.isEmpty());
        System.out.println("A.size(): " + A.size());
        System.out.println("C.size(): " + C.size());
        
        System.out.println("A.get(1): " + A.get(1));
        System.out.println("B.get(3): " + B.get(3));
        System.out.println("C.get(2): " + C.get(2));
        
        A.remove(2);
        
        try {
        	A.get(4);
        } catch(ListIndexOutOfBoundsException e) {
        	System.out.println("Caught Exception: ");
            System.out.println(e);
            System.out.println("Continuing without interuption");
        }
        try {
        	A.add(0, "error");
        } catch(ListIndexOutOfBoundsException e) {
        	System.out.println("Caught Exception: ");
            System.out.println(e);
            System.out.println("Continuing without interuption");
        }
        try {
        	A.remove(5);
        } catch(ListIndexOutOfBoundsException e) {
        	System.out.println("Caught Exception: ");
            System.out.println(e);
            System.out.println("Continuing without interuption");
         }
        
        B.removeAll();
        System.out.println("B.empty(): " + B.isEmpty());
        System.out.println("B.size(): " + B.size());
    }
}
