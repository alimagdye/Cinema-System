package Book;

import Hall.Hall;
import Movie.Movie;
import Movie.ScreenTime;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final int id;
    private static int lastID = 0;
    private String name;
    private short age;
    private List<Receipt> receipts;
    
    
    public Customer(String name, Short age) {
       setName(name);
       setAge(age);
       receipts = new ArrayList<Receipt>();
       id = lastID++;
    }

    public String getName () {
        return name;
    }

    public short getAge () {
        return age;
    }

    public List<Receipt> getReceipts () {
        return receipts;
    }

    public int getID () {
        return id;
    }

    private void setName (String name) {
        this.name = name;
    }
    

    private void setAge (short age) {
        //todo: Some Requirements to age here for example (From Age 18 - ...)
        this.age = age;
    }
    

    public Receipt bookMovie (ScreenTime screenTime, Movie movie, List<Hall.Seat> seats) {
        Receipt receipt = Order.bookMovieTime(this, screenTime, movie, seats);
        if (receipt != null) {
            receipts.add(receipt);
            return receipt;
        }
        return null;
    }
}
