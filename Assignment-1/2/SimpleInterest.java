public class SimpleInterest{
	int principal,rate,time;
	//int temp,s,i;
	// Formula SI=(P*R*T)/100
	
	float calc(float p,float r, float t){
	  float interest=0;
	  interest=(p*r*t)/100;
	  return interest;
	}
public static void main(String[] args){
   SimpleInterest s1=new SimpleInterest();
   float t;
   t= s1.calc(100,200,300);
    System.out.println("interest "  + t);
  }
}

//output : interest 60000.0

