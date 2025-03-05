# Cinema-System
Cinema Management System (CLI-Based)

## Overview
The **Cinema System** is a command-line application developed in Java that enables users to manage movie screenings, seat reservations, and ticket pricing. The system allows customers to book multiple seats for multiple movies while handling pricing, discounts, and report generation.

## Features
- **Movie Management**: Add and remove movies from the schedule.
- **Seat Reservation**: Book and cancel seats for specific movies and time slots.
- **Pricing System**: Automatically calculates total ticket prices based on seat type, number of seats, discounts, and taxes.
- **Booking Management**: Stores customer details such as name, phone number, and payment information.
- **Reporting**: Generates reports about movie bookings and cinema performance.

## Installation
1. Ensure you have **Java 11 or later** installed.
2. Clone the repository:
   ```sh
   git clone https://github.com/alimagdye/cinema-system.git
   ```
3. Navigate to the project directory:
   ```sh
   cd cinema-system
   ```
4. Compile the Java files:
   ```sh
   javac -d bin src/*.java
   ```
5. Run the application:
   ```sh
   java -cp bin Main
   ```

## Usage
1. Start the application.
2. Follow the menu prompts to perform operations like:
   - Adding or removing movies.
   - Booking seats.
   - Viewing movie schedules.
   - Generating reports.
3. Exit the system when finished.

## Future Enhancements
- Implement a graphical user interface (GUI) for better user experience.
- Integrate with a database for persistent storage.
- Add user authentication for admin and customer roles.
- Include online payment integration.

## Contributing
Contributions are welcome! If you'd like to improve this project, please fork the repository and submit a pull request.
