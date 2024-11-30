package Hall;

import static Hall.enClass.FIRST;
import static Hall.enClass.SECOND;
import static Hall.enClass.THIRD;

public class Hall {
    private byte id;
    private int numberOfRows;
    private int numberOfCols;
    private int bookedSeatsCount;
    private Seat[][] seats;
    
    public Hall(byte id, int numberOfRows, int numberOfCols) {
        setID(id);
        setRows(numberOfRows);
        setCols(numberOfCols);
        initializeSeats(numberOfRows, numberOfCols);
    }

    public Hall (Hall other) {
        setID(other.id);
        setRows(other.numberOfRows);
        setCols(other.numberOfCols);
        initializeSeats(other.numberOfRows, other.numberOfCols);
    }
    
    
    // setter functions
    public void setID (byte id) {
        if (id > 0 && id < 127) {
            this.id = id;
        }else{
            System.out.println("Error: Invalid ID - must be between 1 and 126.");
        }
    }
    
    private void setSeatID(int row, int col) {
        if (row < 0 || row >= numberOfRows || col < 0 || col >= numberOfCols) {
            System.out.println("Error: Invalid seat position - row or column out of bounds.");
        }else{
            String seatID;
            char rowLetter = (char) ('A' + row);
            int colIdx = col + 1;
            seatID = "" + rowLetter + colIdx;
            seats[row][col].setID(seatID);
        }
    }

    private void setRows (int numberOfRows) {
        //Number of English Characters
        if (numberOfRows >= 1) {
            this.numberOfRows = Math.min(numberOfRows, 26);
        }else{
            System.out.println("Error: Number of rows must be at least 1.");
        }
        
    }
    
    private void setCols (int numberOfCols) {
        if (numberOfCols >= 1) {
            this.numberOfCols = numberOfCols;
        }else{
            System.out.println("Error: Number of columns must be at least 1.");
        }
    }
    
    private void setSeatClass(int row, int col) {
        int firstClassLimit = (int) Math.ceil(0.2 * numberOfRows); // 20% to first class
        int secondClassLimit = firstClassLimit + (int) Math.ceil(0.5 * numberOfRows); // 50% to second class
        // the rest to third class
        if (row >= 0 && row < firstClassLimit) {
            seats[row][col].setClass(enClass.FIRST);
        } else if (row >= firstClassLimit && row < secondClassLimit) {
            seats[row][col].setClass(enClass.SECOND);
        } else {
            seats[row][col].setClass(enClass.THIRD);
        }
    }
    
    // getter functions
    public int getTotalSeats () {return numberOfRows * numberOfCols;}
    public int getBookedSeats () {return bookedSeatsCount;}
    public int getID () {return id;}
    //not in UML
    public Seat[][] getSeats () {return seats;}
    public int getNumberOfRows () {return numberOfRows;}
    public int getNumberOfCols () {return numberOfCols;}
    
    private void initializeSeats(int numberOfRows, int numberOfCols) {
        seats = new Seat[numberOfRows][numberOfCols];
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfCols; col++) {
                seats[row][col] = new Seat();
                setSeatID(row, col);
                setSeatClass(row, col);
            }
        }
    }

    public boolean bookSeat (String seatID) {        int row = seatID.charAt(0) - 'A';
        int col = Integer.parseInt(seatID.substring(1)) - 1;
        if ((row >= 0 && row < numberOfRows) && (col >= 0 && col < numberOfCols)) {
            if (seats[row][col].isAvailable()) {
            seats[row][col].setAvailability(false);
            bookedSeatsCount++;
            return true;
            }else{
            System.out.println("Error: Seat " + seatID + " is already booked.");
            }
        }else{
            System.out.println("Error: Seat " + seatID + " is out of bounds.");
        }
        return false;
    }

    
    public boolean isFull () {
        return getBookedSeats() == getTotalSeats();
    }    

    
/*
    The Seat class represents a seat in a movie hall with attributes ID, availability,
    price, and class type. The price is determined based on the class type of the seat.
 */

//todo: Recheck the code with UML
public static class Seat {
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


}
