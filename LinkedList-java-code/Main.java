import collections.LinkedList;
import collections.*;

public class Main {
	public static void main(String... args) {
	  
		LinkedList<Integer> head = new SinglyLinkedList ();
		
		((SinglyLinkedList) head).data = 10;
		
		SinglyLinkedList temp = (SinglyLinkedList) head;
		SinglyLinkedList t = (SinglyLinkedList) head;
		
		head.addNode(200);
		head.addNode(30);
		head.addNode(40);
		head.addNode(50);
		
		while(t != null) {
			System.out.println(t.data);
			t = t.next;
		}
		System.out.println(" ");
		System.out.println("Deleting the node 30 ");
		
		head.deleteNode(30);

		
		while(temp != null) {
			System.out.println(temp.data);
			temp = temp.next;
		}
		
		System.out.println(" ");
		System.out.println("Deleting the node 30 ");
		
    head.replace(200, 999);
    SinglyLinkedList temp3 = (SinglyLinkedList) head;
		
    while(temp != null) {
			System.out.println(temp.data);
			temp = temp.next;
		}
    /*
		//SinglyLinkedList temp = (SinglyLinkedList) head;	
		
		while(temp != null) {
			System.out.println(temp.data);
			temp = temp.next;
		}
		
		*/
		
	}
}

