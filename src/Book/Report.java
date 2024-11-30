package Book;

import Movie.Movie;
import Movie.MovieLibrary;
import Movie.ScreenTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Report {
       
    public static int numberOfSoldSeats(Movie movie){
        return movie.getBookedSeats(); //return the number of sold seats for this movie 
    }
    
    /*found the most watched movie(s) between all movies in the movie library */

    public static List<Movie> mostWatchedMovie(){
        int sumBookedSeats=0;       //total seats for each movie
        int max=0;      //max for select the most watched movie
        List<Movie> mostWatchedMovie=new ArrayList<>();          //list of most watched movie if there more than one has same total number of seats
        //loop every movie in the movie library
        for (Movie movie : MovieLibrary.getMovies()) {
            sumBookedSeats=0; //re-initialize the total for next movie
            // Loop through each screening time for the current movie
            for (ScreenTime screenTime : movie.getScreenTimes()) {

                sumBookedSeats += screenTime.getHall().getBookedSeats();  // Add booked seats to the total


            }
            //chack if the total seats of this movie more than max
            if(sumBookedSeats>max){
                //if tru the clear the list from movie and update the max and add the new movie to the list
                mostWatchedMovie.clear();
                max=sumBookedSeats;
                mostWatchedMovie.add(movie);
            }
            else if(sumBookedSeats == max)
                // if there movie with total seats equal max the add it to the list
                mostWatchedMovie.add(movie);

        }

        return mostWatchedMovie;// return the movie(s)
    }
    public static List<Date[]> getCrowdedTimes() {
        List<Date[]> mostCrowdedTimes = new ArrayList<>();  // List to store all equally crowded time periods
        int max = 0;                                        // Variable to store the highest number of booked seats found
        int tempMax=0;
        // Loop through all movies in the library
        for (Movie movie : MovieLibrary.getMovies()) {

            // Loop through all screening times for each movie
            for (ScreenTime screenTime : movie.getScreenTimes()) {

                // Count the number of booked seats for this specific screen time
                tempMax = getNumberOfSeats(screenTime.getStartDate(), screenTime.getEndDate());

                // Check if this screening has more booked seats than the current maximum
                if (tempMax > max) {

                    // Found a new maximum, so clear the list and add this time period
                    mostCrowdedTimes.clear();
                    max = tempMax;     // Update max with the new highest number of booked seats

                    mostCrowdedTimes.add(new Date[] { screenTime.getStartDate(), screenTime.getEndDate() });   //add the new max into the Date list

                } else if (tempMax == max) {        // If this screening has the same number of booked seats as the max

                    mostCrowdedTimes.add(new Date[] { screenTime.getStartDate(), screenTime.getEndDate() });    //add the similar time to the Date list
                }
            }
        }

        return mostCrowdedTimes;  // Return the list of crowded time periods with the maximum booked seats
    }


     /* Calculates the total number of booked seats for a specified screening period across all movies. */

    public static int getNumberOfSeats(Date startDate, Date endDate) {
        int sumBookedSeats = 0;  // Variable to accumulate the total booked seats for the period

        // Loop through all movies in the library
        for (Movie movie : MovieLibrary.getMovies()) {
            // Loop through each screening time for the current movie
            for (ScreenTime screenTime : movie.getScreenTimes()) {
                // Check if the screening time matches the specified start and end dates

                if (screenTime.getStartDate().equals(startDate) && screenTime.getEndDate().equals(endDate)) {
                    sumBookedSeats += screenTime.getHall().getBookedSeats();  // Add booked seats to the total
                }

            }
        }

        return sumBookedSeats;  // Return the total number of booked seats for the period
    }
}

