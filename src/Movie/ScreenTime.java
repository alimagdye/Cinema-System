package Movie;

import Hall.Hall;

import java.util.Arrays;
import java.util.Date;

/**
 * The ScreenTime class represents a specific screening time for a movie in a particular hall.
 * It contains details about the hall, start date, end date, and booked seats.
 * Defensive programming principles are applied.
 */
public class ScreenTime {
    private Hall hall;
    private Date startDate;
    private Date endDate;

    /**
     * Constructs a ScreenTime instance with the specified hall, start date, and end date.
     *
     * @param hall      The hall where the screening is held. Must not be null.
     * @param startDate The start date of the screening. Must not be null.
     * @param endDate   The end date of the screening. Must not be null and should be after startDate.
     * @throws IllegalArgumentException if any of the parameters are invalid.
     */
    public ScreenTime(Hall hall, Date startDate, Date endDate) {
        setHall(hall);
        setStartDate(startDate);
        setEndDate(endDate);
        validateDates();
    }

    /**
     * Sets the hall for this ScreenTime.
     *
     * @param hall The hall to set. Must not be null.
     * @throws IllegalArgumentException if hall is null.
     */
    public void setHall(Hall hall) {
        if (hall == null) {
            throw new IllegalArgumentException("Hall cannot be null.");
        }
        this.hall = new Hall(hall);
    }

    /**
     * Sets the start date for this ScreenTime.
     *
     * @param startDate The start date to set. Must not be null.
     * @throws IllegalArgumentException if startDate is null.
     */
    public void setStartDate(Date startDate) {
        if (startDate == null) {
            throw new IllegalArgumentException("Start date cannot be null.");
        }
        this.startDate = startDate;
    }

    /**
     * Sets the end date directly if known.
     *
     * @param endDate The end date to set. Must not be null.
     * @throws IllegalArgumentException if endDate is null.
     */
    public void setEndDate(Date endDate) {
        if (endDate == null) {
            throw new IllegalArgumentException("End date cannot be null.");
        }
        this.endDate = endDate;
    }

    /**
     * Validates that the start date is before the end date.
     *
     * @throws IllegalArgumentException if startDate is after endDate.
     */
    private void validateDates() {
        if (startDate != null && endDate != null && startDate.after(endDate)) {
            throw new IllegalArgumentException("Start date must be before end date.");
        }
    }

    /**
     * Gets the hall for this ScreenTime.
     *
     * @return The hall where the screening is held.
     */
    public Hall getHall() {
        return hall;
    }

    /**
     * Gets the start date for this ScreenTime.
     *
     * @return The start date of the screening.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Gets the end date for this ScreenTime.
     *
     * @return The end date of the screening.
     */
    public Date getEndDate() {
        return endDate;
    }

}
