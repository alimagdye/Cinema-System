package Book;

import Movie.MovieLibrary;
import Hall.Hall;
import Movie.Movie;
import Movie.ScreenTime;

import java.util.ArrayList;
import java.util.List;


public class Order {

    /*
      Books a movie screening time for a customer, confirming the booking and generating a receipt if successful.
      Checks if there are enough available seats in the selected hall, confirms the booking,
      and creates a receipt with booking details.
    */
    public static Receipt bookMovieTime(Customer customer, ScreenTime screenTime, Movie movie, List<Hall.Seat> seats) {
       
        // Confirm the booking with the total price and selected screening details
        if (confirmMsg(getPrice(seats), movie, screenTime)) {
            
            // Check if the movie exists in the store and if it has the specified screen time
            if (MovieLibrary.hasMovie(movie) && movie.hasScreenTime(screenTime)) {
            
                Hall hall = screenTime.getHall();

                // Check if there are enough available seats in the hall
                if (seats.size() > hall.getTotalSeats() - hall.getBookedSeats()) {
                    //if not then show message and return null
                    System.out.println("Sorry, no enough seats");
                    return null;
                } else {
                    // try to book the specified seats
                    List<String> seatIDs = new ArrayList<>(seats.size());
                    if (bookSeats(hall, seats, seatIDs)) {
                        //making new receipt with customer and movie and hall and screening time details
                        return new Receipt(customer.getName(),movie.getTitle(),getPrice(seats),hall.getID(), seatIDs, screenTime);
                    } else {
                        // if the seats isn't available then print message and return null
                        System.out.println("Booking failed for some seats.");
                        return null;
                    }
                }
            } else {
                // if there is no movie like that or not in that time then print message and return null
                System.out.println("Movie or screen time is incorrect or unavailable.");
                return null;
            }
        }
        return null; // if canceled then return null
    }

    /*Books the specified seats in a given hall by marking each seat as reserved.*/
    private static boolean bookSeats(Hall hall, List<Hall.Seat> seats, List<String> seatIDs) {
        // looping the selected seats
        for (var seat : seats) {
            // If booking any seat fails, return false
            if (!seat.isAvailable()) {
                return false;
            }
        }

        for (var seat : seats) {
            String seatID = seat.getID();
            hall.bookSeat(seatID);
            seatIDs.add(seatID);
        }
        return true;
    }
    /**
        Displays a confirmation message for the booking, including the total price, selected movie, and screen time.
        **Note: the implementation will be in GUI part **
     */
    private static boolean confirmMsg(float totalPrice, Movie movie, ScreenTime screenTime) {
        // using GUI ; temporary returns 
        return true;
    }

    /* Calculates the total price for booking the specified seats based on their individual prices. */
    
    public static float getPrice(List<Hall.Seat> seats) {
        //intial value for calc. the total price
        float totalPrice = 0.0F;
        //looping the selected seats
        for (Hall.Seat seat : seats) {
            //calc the total price
            totalPrice += seat.getPrice();
        
        }
        return totalPrice;   // return the total price
    }
}

