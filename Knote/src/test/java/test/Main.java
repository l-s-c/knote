package test;
 
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
 
 
class People{
    private String name;
    private int age;
     
    public People(String name,int age) {
        this.name = name;
        this.age = age;
    }  
     
    public void setAge(int age){
        this.age = age;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		People other = (People) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
     
/*    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return name.hashCode()*37+age;
    }
     
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return this.name.equals(((People)obj).name) && this.age== ((People)obj).age;
    }*/
    
    
}
 
public class Main {
 
    public static void main(String[] args) {
    	
    	String str = "-5";
    	System.out.println(Integer.parseInt(str));
    	
    	
    	
  /*      
        People p1 = new People("Jack", 12);
        System.out.println(p1.hashCode());
            
        People p2 = new People("Jack", 12);
        System.out.println(p2.hashCode());
        
        System.out.println(p1==p2);
        System.out.println(p1.hashCode()==p2.hashCode());
        System.out.println(p1.equals(p2));*/
/*        HashMap<People, Integer> hashMap = new HashMap<People, Integer>();
        hashMap.put(p1, 1);
         
        System.out.println(hashMap.get(new People("Jack", 12)));*/
    }
}