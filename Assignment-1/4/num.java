import java.util.Scanner;
//number pattern print
public class num{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
  //System.out.println("*");
    System.out.print("Enter the range: ");
    int n=sc.nextInt();

  for (int i=0; i<=n; i++){
    for (int j=0; j<=i-1; j++){
      System.out.print("* ");
    }
  System.out.println();
  }
  }
}

/*
output:
* 
* * 
* * * 
* * * * 
* * * * * 
*/
