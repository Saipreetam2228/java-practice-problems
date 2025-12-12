public class Person{
  String name;
  int age;
  
  Person(String n,int a){
    this.name=n; // -> this ; is a pointer /reference to the current object
    this.age=a;
  }
  
  public void DisplayInfo(){
    System.out.println("Name is:"+name+"Age is:"+age);
  }
  public static void main(String[] args){ 
    Person p= new Person("Sai",18);
    p.DisplayInfo();
  }
}

