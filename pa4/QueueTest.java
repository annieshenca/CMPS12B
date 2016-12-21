
public class QueueTest {
	public static void main(String[] args) {
		Queue Q = new Queue();
		
		System.out.println("Empty? " + Q.isEmpty());
		System.out.println("Length: " + Q.length());
		
		//Add
		Q.enqueue((String)"H");
		Q.enqueue((String)"e");
		Q.enqueue((String)"l");
		Q.enqueue((String)"l");
		Q.enqueue((String)"o");
		Q.enqueue((int)1);
		Q.enqueue((int)2);
		Q.enqueue((double)3.4);
		
		System.out.println(Q.toString());
		
		System.out.println("Empty? " + Q.isEmpty());
		System.out.println("Length: " + Q.length());
		
		try {
			Q.dequeue(); //Deleted "H"
		} catch(QueueEmptyException e) {
			System.out.println("Caught Exception " + e);
			System.out.println("Continuing without interuption:");
		}
		System.out.println(Q.toString());
		
		try {
			System.out.println("Peek: " + Q.peek()); //Should print out "e"
		} catch(QueueEmptyException e) {
			System.out.println("Caught Exception " + e);
			System.out.println("Continuing without interuption:");
		}
		
		try {
			Q.dequeueAll(); //Delete all
		} catch(QueueEmptyException e) {
			System.out.println("Caught Exception " + e);
			System.out.println("Continuing without interuption:");
		}
		
		System.out.println("Empty? " + Q.isEmpty());
		System.out.println("Length: " + Q.length());
	}
}
