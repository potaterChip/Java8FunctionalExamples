package chapter4;

public class DefaultExamples {

  public interface Fly {
    default void takeOff() { System.out.println("Fly::takeOff");}
    default void cruise() {System.out.println("Fly::cruise");}
  }
  
  public interface FastFly extends Fly{
    default void takeOff() {System.out.println("Fastfly::takeOff");}
  }
  
  public interface Sail {

    default void cruise() {
      System.out.println("Sail::cruise");
    }

    default void turn() {
      System.out.println("Sail::turn");
    }

  }


  public class Vehicle {

    public void turn() {
      System.out.println("Vehicle::turn");
    }

  }
  
  public class SeaPlane extends Vehicle implements FastFly, Sail {
    
    private int altitude;
        
    //...
        
    public void cruise() {
        
    System.out.print("SeaPlane::cruise currently cruise like: ");
        
    if(altitude > 0)
        
    FastFly.super.cruise();
        
    else
        
    Sail.super.cruise();
        
    }
        
    }
}
