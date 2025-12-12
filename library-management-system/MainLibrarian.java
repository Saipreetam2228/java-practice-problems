/* 
Host/Librarian

-> complete list in the ordered view
-> Remove 1st book (return)
-> Remove by indexing
-> count the total(no.of books)

========Task========
* Add 5 books in sequence
* Remove the books from the list in sequence
* Remove first (Head) from the list 
* Full print of list with count
*/
import java.util.*;
import task.*;  //collection of the template code
import java.util.HashMap; //by using Hashmap function to store the data like "Id,BookName"

public class MainLibrarian{
//printing the entire list in a loop
  public static void Loop(List t){
  System.out.println("Order of the list: "); 
   while(t!=null){
      System.out.println(t.value);
      t=t.next;
    }
    System.out.println(" ");
  } 

//Hashmap structure for the record <Id,Book_Name> 
 public static void main(String[] args){
    Functions<Integer,String> Head=new Functions<Integer,String>();
    Head.put(501,"OS");
    Head.put(502,"CN");
    Head.put(503,"JAVA");
    Head.put(504,"OPTML");
    Head.put(505,"DMML");
    
    System.out.println("Overall Record: ");
    Loop(t);
 
 }
}
