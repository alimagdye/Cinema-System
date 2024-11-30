package CLI;

import Book.Order;
import Hall.Hall;
import Movie.Movie;
import Movie.ScreenTime;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Check {
    public enum enResponse {
        YES,
        NO,
        UNKNOWN,
    }

    public static <T> boolean isValidAnswer (int choice, T[] arr) {
        return choice > 0 && choice <= arr.length;
    }

    public static <T> boolean isValidAnswer (int choice, List<T> list) {
        return choice > 0 && choice <= list.size();
    }

    public static enResponse getResponse(String answer) {
        var normalizedChoice = answer.toLowerCase(Locale.ROOT);
        return switch (normalizedChoice) {
            case "y", "yes" -> enResponse.YES;
            case "n", "no" -> enResponse.NO;
            default -> enResponse.UNKNOWN;
        };
    }

    public static boolean confirmMsg (Scanner scanObj, Movie selectedMovie, List<Hall.Seat> seats, ScreenTime screenTime) {
        if (!seats.isEmpty()) {
            float totalPrice = Order.getPrice(seats);
            var response = Check.enResponse.UNKNOWN;

            do {
                System.out.println("Price all is " + totalPrice);
                System.out.println("Movie Title: " + selectedMovie.getTitle());
                System.out.println("Hall ID: " + screenTime.getHall().getID());
                System.out.println("Start Date: " + Format.date(screenTime.getStartDate()));
                System.out.println("End Date: " + Format.date(screenTime.getEndDate()));
                System.out.println("Duration: " + Format.duration(selectedMovie.getDuration()));


                System.out.print("Seats: ");
                for (var seat : seats) {
                    System.out.print(seat.getID() + ", ");
                }
                System.out.println();

                System.out.println("Confirm Payment? (y/n)");
                String answer = scanObj.next();
                response = Check.getResponse(answer);

            } while(response == Check.enResponse.UNKNOWN);

            return response == Check.enResponse.YES;
        }
        return false;
    }

    public static boolean datesOverlap(Date start1, Date end1, Date start2, Date end2) {
        return !(end1.before(start2) || start1.after(end2));
    }

    public static boolean isTodayOrWithinOneWeek(Date date) {
        // Get the current time
        Date now = new Date();

        // Calculate one week from now
        Date oneWeekFromNow = new Date(now.getTime() + (7L * 24 * 60 * 60 * 1000)); // 7 days in milliseconds

        // Check if the date is today or within the next 7 days
        return !date.before(now) && !date.after(oneWeekFromNow);
    }
}