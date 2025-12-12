package list;
public class Record implements Functions<Integer>{
  public int Data;  
  public Record Next;
  
  int[] array = {1,2,3,4,5};
  
  public void AddRecord(Integer Data){
    Record T=this;
    do{
      if(T.next==null){
        Record Next=new Record(); //record allocation
        Next.Data=Data;
        T.Next=Next;
        break;
      }
      T=T.Next;
    }while(temp!=null);
  }
  
}
