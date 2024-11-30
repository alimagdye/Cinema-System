package CLI;

import Book.Customer;
import Hall.Hall;
import Movie.Movie;
import Movie.ScreenTime;
import Movie.MovieLibrary;
import Movie.enGenre;

import java.lang.String;
import java.util.Scanner;
import java.util.Date;
import java.util.ArrayList;

public class Manager {
    //!TODO
    //todo: Add Packages
    //todo: Move class Receipt to class Order (Composition)
    //todo: Move class seats to class hall (Composition)
    //todo: Make sure not including all the library for one class
    //todo: Remove confirmMsg() from Order (Check it here)
    //todo: Make getPrice() In Order public (In Case Confirm Message here not in Order)

    static public ArrayList<Hall> halls = new ArrayList<Hall>();


    public Manager (Scanner scanObj) {
        initializeLibrary();
        start(scanObj);
    }

    private void initializeLibrary () {
        var hall1 = new Hall((byte) 1, 2, 2);  // 10 * 10 = 100 Seat
        var hall2 = new Hall((byte) 1, 5, 5);  // 10 * 10 = 100 Seat
        var screenTimes1 = new ArrayList<ScreenTime>();
        var screenTimes2 = new ArrayList<ScreenTime>();
        halls.add(hall1);
        halls.add(hall2);
        screenTimes1.add(new ScreenTime(hall1, new Date(2024 - 1900, 10, 21, 12, 20, 30), new Date(2024 - 1900, 10, 21, 14, 20, 30)));

        screenTimes2.add(new ScreenTime(hall1, new Date(2024 - 1900, 11, 21, 1, 0, 0), new Date(2024 - 1900, 11, 21, 2, 0, 0)));
        screenTimes2.add(new ScreenTime(hall2, new Date(2024 - 1900, 11, 22, 1, 0, 0), new Date(2024 - 1900, 11, 22, 2, 0, 0)));

        Movie movie = new Movie("Movie1", enGenre.ROMANTIC, screenTimes1);
        Movie movie1 = new Movie("Movie2" , enGenre.COMEDY, screenTimes2);
        var movies = new ArrayList<Movie>();
        movies.add(movie);
        movies.add(movie1);
        MovieLibrary lib = new MovieLibrary(movies);
    }


    public void start (Scanner scanObj) {
        System.out.print("Enter your name: ");
        String name = scanObj.next();

        java.lang.System.out.print("Enter your age: ");
        short age = scanObj.nextShort();

        var customer = new Customer(name, age);

        java.lang.System.out.println("Welcome " + customer.getName());

        Menu.mainMenu(scanObj, customer);
    }
}
