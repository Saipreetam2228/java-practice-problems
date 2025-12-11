import java.util.Scanner;

public class Bank{
  long accountnumber;
  int  balance=0;
  int  amount;
  
  //Rupees >100
  //===========Deposit============
  void deposit(int amount){
    if( amount>=100){
      balance=balance+amount;
    }
    else{
    System.out.println("Invalid Deposit (<100)");
    }
    
  //return balance;
 }
 
 //==========Withdraw===========
  int withdraw(int amount){
    if (balance < amount){
      System.out.println("insufficient balance");
    }
    balance=balance-amount;
    return balance;
  }
  
  void display(){
  if(balance>=0){
     System.out.println("Balance= "+balance);
    }
  else{
     System.out.println("Balance=0");
    }
  }
  

  public static void main(String[] args){
    Scanner sc=new Scanner(System.in);
    
      Bank val=new Bank();
      System.out.print("Enter Initial Deposit: ");
      int n=sc.nextInt();
      val.deposit(n);
      val.display();
      System.out.print("Amount you want to withdraw: ");
      int w=sc.nextInt();
      val.withdraw(w);
      val.display();
  }
}
