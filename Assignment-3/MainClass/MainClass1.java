//version 1 (with insertion )
import java.util.Scanner;
//===========MAIN============
public class MainClass1{
  public static void main(String[] args){
    Scanner sc=new Scanner(System.in);  //scanner for user input
    DLL n=new DLL();
 //   n.insert(10);
 //   n.insert(20);
 //   n.insert(30);
    System.out.print("No.of nodes you want to Insert: ");
    int num=sc.nextInt();  
    for(int i=0; i<num; i++){  //insert the value through the looping
      System.out.print("Enter Node Value "+(i+1)+": ");
      int val =sc.nextInt(); 
      n.insert(val);  //defining the values to the insertion function 
    }
    System.out.println("\nFinal Doubly Linked List:");
    n.displaylist();  //at last display all 

    sc.close();
  }
}
//==========================Creation of node============================
//create the structure of a node with the {Prev,data,next} structure
class CreateNode{
  CreateNode prev; //object creation for the function create node
  int data;
  CreateNode next;
//defining the node like data 'd' stores in data and rest all NULL for the initial creation
  public CreateNode(int d){
    this.prev=null;    
    this.data=d;
    this.next=null;
  }
}
//end of creating a node;

// so, now let's create a node called doublylinkedlist which consists start and end 
//For the starting and the ending point we need first and last to this link
//this class can encapsulate all kind of things like
//insert(),deletion(),update(),display()

//========================Structure of node=========================
class DLL{
  CreateNode first;
  CreateNode last;
  
  public DLL(){
    this.first=null;
    this.last=null;
  }
//========================Insertion of node========================
  //insertion of a node {default at last}
  public void insert(int d){
    CreateNode newnode= new CreateNode(d);
    
    /*conditions applied
    (1) node can be directly inserted when the list is empty
    (2) node should insert at last through the iteration, when there is a list */
    if(first==null){ //node inserted at as first and that is the only node
      first=newnode;
      last=newnode;
    }
    else{ //node inserted at last and linked to the last before node
      last.next=newnode;
      newnode.prev=last;
      last=newnode;
    }
  }
//end of insertion of nodes

//==========================Display node===============================
//displaying the list()
public void displaylist(){
    if(first==null){
      System.out.println("Empty LIST ---- NULL");
    }
    
    CreateNode t=first;  //temporary node to store first a head node 
    System.out.println("==== Doubly Linked List VIEW ====");
    while(t!=null){  //iteration till last node by checking it as null;
      System.out.print(t.data+" <-> ");
      t=t.next; //moving iteration
    }
   System.out.println("NULL");
   System.out.println("First Node="+first.data+"\nLast Node="+last.data);
  }
  
}


