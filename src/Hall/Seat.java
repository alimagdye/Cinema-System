package Hall;
/*
    The Seat class represents a seat in a movie hall with attributes ID, availability,
    price, and class type. The price is determined based on the class type of the seat.
 */

//todo: Recheck the code with UML
public class Seat {
    // Unique identifier for the seat
    private String id;
    
    // Indicates whether the seat is available for booking
    private boolean isAvailable = true;
    
    // Price of the seat based on its class type
    private float price;
    
    // Class type of the seat (FirstClass, SecondClass, ThirdClass)
    //todo: Add class of seat
    private enClass classType;

    /*
      Sets the unique identifier for the seat.
     */
    public void setID(String id) {
        this.id = id;
    }

    /*
       Sets the class type for the seat. The class type affects the price of the seat.
     */
    public void setClass (enClass classType) {
        this.classType = classType;
        setPrice();
    }

    /* Sets the availability of the seat for booking. */
    public void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    /*
      Sets the price of the seat based on its class type. The price varies by class:
      - FirstClass: 200.50
      - SecondClass: 250.95
      - ThirdClass: 350.00
     */
    private void setPrice() {
        switch (classType) {
            case FIRST -> price = 200.50F;
            case SECOND -> price = 250.95F;
            case THIRD -> price = 350.00F;
            default -> throw new AssertionError("Seat Class is Unknown.");
        }
    }

    /* Gets the unique identifier for the seat */
    public String getID() {
        return id;
    }

    /* Gets the class type of the seat */
    public String getClassType() {
        return classType.getDescription();
    }

    /*
      Gets the price of the seat. This method calls setPrice() to determine the price
      based on the class type
    */
    public float getPrice() {
        return price;
    }

    /* Checks if the seat is available for booking */
    public boolean isAvailable() {
        return isAvailable;
    }
}
