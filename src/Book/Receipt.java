package Book;

import Movie.ScreenTime;

import java.util.Date;
import java.util.List;

/**
 * Represents a receipt for a movie booking.
 * Stores details such as the movie name, customer name, total price, hall ID, seat IDs,
 * and associated screen time.
 */
public class Receipt {
    private static int lastID;
    private int id;
    private String movieName;
    private String customerName;
    private float totalPrice;
    private int hallID;
    private List<String> seatIDs;
    private ScreenTime screenTime;

    /**
     * Constructs a new Receipt with the specified details.
     *
     * @param customerName The name of the customer booking the movie.
     * @param movieName The name of the movie being booked.
     * @param totalPrice The total price of the booking.
     * @param hallID The ID of the hall where the movie will be screened.
     * @param seatIDs A list of seat IDs booked by the customer.
     * @param screenTime The ScreenTime instance associated with this booking.
     * @throws IllegalArgumentException if customerName or movieName are null or empty,
     *                                  if totalPrice is negative, or if hallID is non-positive.
     */
    public Receipt(String customerName, String movieName, float totalPrice, int hallID, List<String> seatIDs, ScreenTime screenTime) {
        setCustomerName(customerName);
        setMovieName(movieName);
        setTotalPrice(totalPrice);
        setHallID(hallID);
        this.seatIDs = seatIDs;
        if (screenTime == null) {
            throw new IllegalArgumentException("Screen time cannot be null.");
        }
        this.screenTime = screenTime;
        id = lastID++;
    }

    /**
     * Sets the name of the movie with validation.
     *
     * @param movieName The name of the movie.
     * @throws IllegalArgumentException if movieName is null or empty after trimming.
     */
    private void setMovieName(String movieName) {
        movieName = movieName.trim(); // Trim whitespace
        if (movieName == null || movieName.isEmpty()) {
            throw new IllegalArgumentException("Movie name cannot be null or empty.");
        }
        this.movieName = movieName;
    }

    /**
     * Sets the customer's name with validation.
     *
     * @param customerName The name of the customer.
     * @throws IllegalArgumentException if customerName is null or empty after trimming.
     */
    private void setCustomerName(String customerName) {
        customerName = customerName.trim(); // Trim whitespace
        if (customerName == null || customerName.isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty.");
        }
        this.customerName = customerName;
    }

    /**
     * Sets the total price of the booking with validation.
     *
     * @param totalPrice The total price of the booking.
     * @throws IllegalArgumentException if totalPrice is negative.
     */
    private void setTotalPrice(float totalPrice) {
        if (totalPrice < 0) {
            throw new IllegalArgumentException("Total price cannot be negative.");
        }
        this.totalPrice = totalPrice;
    }

    /**
     * Sets the hall ID with validation.
     *
     * @param hallID The ID of the hall where the movie will be shown.
     * @throws IllegalArgumentException if hallID is non-positive.
     */
    private void setHallID(int hallID) {
        if (hallID <= 0) {
            throw new IllegalArgumentException("Hall ID must be positive.");
        }
        this.hallID = hallID;
    }

    /**
     * Returns the name of the movie.
     *
     * @return The name of the movie.
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * Returns the name of the customer.
     *
     * @return The name of the customer.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Returns the total price of the booking.
     *
     * @return The total price of the booking.
     */
    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * Returns the ID of the hall where the movie is being screened.
     *
     * @return The hall ID.
     */
    public int getHallID() {
        return hallID;
    }

    /**
     * Returns a list of seat IDs booked by the customer.
     *
     * @return A list of seat IDs.
     */
    public List<String> getSeatIDs() {
        return seatIDs;
    }

    /**
     * Returns the start date of the screen time associated with the booking.
     *
     * @return The start date of the screen time.
     */
    public Date getStartDate() {
        return screenTime.getStartDate();
    }

    /**
     * Returns the end date of the screen time associated with the booking.
     *
     * @return The end date of the screen time.
     */
    public Date getEndDate() {
        return screenTime.getEndDate();
    }

    public int getID () {
        return id;
    }
}

// in the main make array of receipts to store all the receipts in one place.
