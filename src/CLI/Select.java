package CLI;
import Hall.Hall;
import Movie.Movie;
import Movie.MovieLibrary;
import Movie.enGenre;
import Movie.ScreenTime;

import java.util.*;

public class Select {
    static Movie movie (Scanner scanObj) {
        int choice;
        var movies = MovieLibrary.getMovies();

        do {
            Print.movies(movies);

            System.out.print("Choose a movie: ");
            choice = scanObj.nextInt();
        } while (!Check.isValidAnswer(choice, movies));

        return movies.get(choice - 1);
    }

    static Hall selectHall(Scanner scanObj) {
        int choice;
        var halls = Manager.halls;

        do {
            Print.halls(halls);

            System.out.print("Choose a hall: ");
            choice = scanObj.nextInt();
        } while (!Check.isValidAnswer(choice, halls));

        return halls.get(choice - 1);
    }

    static ScreenTime screenTime(Scanner scanObj, Movie movie) {
        int choice;
        var screenTimes = movie.getScreenTimes();
        do {
            Print.movieDetails(movie);

            System.out.println("Available Screen Times:");

            for (int i = 0; i < screenTimes.size(); i++) {
                var screenTime = screenTimes.get(i);
                if (!screenTime.getHall().isFull()) { //Check if all seats hasn't been booked yet
                    Date startDate = screenTime.getStartDate();
                    System.out.printf("%d: %s (Duration: %s)%n",
                            i + 1,
                            Format.date(startDate),
                            Format.duration(movie.getDuration())
                    );
                }
            }
            System.out.print("Choose your preferred screen time: ");
            choice = scanObj.nextInt();
        } while (!Check.isValidAnswer(choice, screenTimes));

        return screenTimes.get(choice - 1);
    }


    static ArrayList<Hall.Seat> seats (Scanner scanObj, Hall hall) {
        var seats = hall.getSeats();
        var selectedSeats = new ArrayList<Hall.Seat>();
        var response = Check.enResponse.UNKNOWN;

        do {
            Print.seats(hall);

            System.out.print("Do you want to book a seat? (y/n) ");
            String answer = scanObj.next().trim();
            response = Check.getResponse(answer);

            if (response == Check.enResponse.NO) {
                break;
            }

            if (response == Check.enResponse.UNKNOWN) {
                continue;
            }

            System.out.print("Enter Seat ID to book (e.g., A1): ");
            String seatID = scanObj.next();

            //! Need to temporarily book seats until payment done
            //! Can define mechanism with timeout to set seats free

            int row = seatID.charAt(0) - 'A';
            int col = Integer.parseInt(seatID.substring(1)) - 1;

            if (seats[row][col].isAvailable() && !selectedSeats.contains(seats[row][col])) {
                selectedSeats.add(seats[row][col]);
                System.out.println("\u001B[32mSuccessfully booked seat \"" + seatID + "\"!\u001B[0m");
            } else {
                System.out.println("\u001B[31mSeat \"" + seatID + "\" is already booked or invalid.\u001B[0m");
            }

        } while (!hall.isFull());

        return selectedSeats;
    }

    static void unSelectSeats(Scanner scanObj, List<Hall.Seat> selectedSeats, Hall hall) {
        var seats = hall.getSeats();
        var response = Check.enResponse.UNKNOWN;

        while (!selectedSeats.isEmpty()) {
            System.out.println("Selected seats until now are");
            System.out.print("{");
            for (var seat : selectedSeats) {
                System.out.print(seat.getID() + ", ");
            }
            System.out.println("}");

            System.out.print("Do you want to cancel booked seat? (y/n) ");
            String answer = scanObj.next();
            response = Check.getResponse(answer);

            if (response == Check.enResponse.NO) {
                break;
            }

            if (response == Check.enResponse.UNKNOWN) {
                continue;
            }

            System.out.print("Enter Seat ID to cancel (e.g., A1): ");
            String seatID = scanObj.next();

            int row = seatID.charAt(0) - 'A';
            int col = Integer.parseInt(seatID.substring(1)) - 1;

            boolean seatFound = false;

            Iterator<Hall.Seat> iterator = selectedSeats.iterator();
            while (iterator.hasNext()) {
                Hall.Seat seat = iterator.next();
                if (seatID.equals(seat.getID())) {
                    iterator.remove();
                    seats[row][col].setAvailability(true);
                    System.out.println("\u001B[32mSuccessfully removed seat \"" + seatID + "\"!\u001B[0m");
                    seatFound = true;
                    break;
                }
            }
            if (!seatFound) {
                System.out.println("\u001B[31mCannot find seat \"" + seatID + "\" among selected seats.\u001B[0m");
            }
        }
    }

    static int report (Scanner scanObj, String[] reports) {
        int choice;
        do {
            for (int i = 0; i < reports.length; i++) {
                System.out.println((i + 1) + ": " + reports[i]);
            }

            System.out.println("Enter Which Report to display");
            choice = scanObj.nextInt();
        } while (!Check.isValidAnswer(choice, reports));

        return choice;
    }

    static enGenre genre (Scanner scanObj) {
        int choice = -1;
        enGenre[] genres = enGenre.values();

        do {
            System.out.println("Choose genre:");
            for (int i = 0; i < genres.length; i++) {
                System.out.println((i + 1) + ". " + genres[i].getGenre());
            }

            System.out.print("Enter your choice (1-" + genres.length + "): ");
            choice = scanObj.nextInt();
        } while (!Check.isValidAnswer(choice, genres));

        return genres[choice - 1];
    }
}
