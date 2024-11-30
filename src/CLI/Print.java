package CLI;
import Movie.Movie;
import Book.Receipt;
import Hall.Hall;
import Movie.ScreenTime;

import java.util.List;

public class Print {
    public static void movies (List<Movie> movies) {
        int i = 0;
        for (var movie : movies) {
            System.out.println((i + 1) + ": " + movie.getTitle());
            i++;
        }
    }

    public static void halls (List<Hall> halls) {
        int i = 0;
        for (var hall : halls) {
            System.out.println((i + 1) + ": " + hall.getID());
            i++;
        }
    }

    public static void movieDetails(Movie movie) {
        System.out.println("========================================");
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Duration: " + Format.duration(movie.getDuration()));
        System.out.println("Genre: " + movie.getGenre());
        System.out.println("Booked Seats: " + movie.getBookedSeats());
        System.out.println("========================================");
    }

    public static void seats(Hall hall) {
        System.out.println("B => Seat is Already Booked");
        System.out.println("F => Seat is Free");

        var seats = hall.getSeats();

        System.out.println("\t\t\t\t\tScreen\t\t\t\t\t");
        System.out.println("\txxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\t");
        System.out.print("    ");

        for (int i = 1; i <= hall.getNumberOfCols(); i++) {
            System.out.print(i + "\t");
        }
        System.out.println();

        for (int row = 0; row < hall.getNumberOfRows(); row++) {
            System.out.print((char) ('A' + row) + "\t");
            for (int col = 0; col < hall.getNumberOfCols(); col++) {
                if (seats[row][col].isAvailable()) {
                    System.out.print("\u001B[32mF\u001B[0m\t");
                } else {
                    System.out.print("\u001B[31mB\u001B[0m\t");
                }
            }
            System.out.println();
        }
    }

    public static void receipt (Receipt receipt) {
        //todo: Implement way to display receipt
        if (receipt != null) {
            System.out.println("Name : "        + receipt.getCustomerName());
            System.out.println("Movie Name : "  + receipt.getMovieName());
            System.out.println("Total Price : " + receipt.getTotalPrice() + " LE");
            System.out.println("Hall ID : "     + receipt.getHallID());
            System.out.println("Duration : "    + Format.duration(receipt.getStartDate(), receipt.getEndDate()));
            System.out.println("Start Date : "  + Format.date(receipt.getEndDate()));
            System.out.println("End Date : "    + Format.date(receipt.getStartDate()));
        } else {
            System.out.println("\u001B[31mSomething Went Wrong!\u001B[0m\t");
        }
    }



}
