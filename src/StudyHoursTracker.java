import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class StudyHoursTracker {

    private static class StudySession {
        String course;
        LocalDate date;
        int minutes;

        StudySession(String course, LocalDate date, int minutes) {
            this.course = course;
            this.date = date;
            this.minutes = minutes;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<StudySession> sessions = new ArrayList<>();
        boolean running = true;

        System.out.println("Welcome to Study Hours Tracker!");

        while (running) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Record Study Session");
            System.out.println("2. View Weekly Summary");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextLine()) {
                break;
            }

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    recordStudySession(scanner, sessions);
                    break;

                case "2":
                    System.out.println("Weekly summary is coming soon.");
                    break;

                case "3":
                    System.out.println("Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please enter 1, 2, or 3.");
            }
        }

        scanner.close();
    }

    private static void recordStudySession(Scanner scanner,
                                           ArrayList<StudySession> sessions) {

        System.out.println("\n=== Record Study Session ===");

        System.out.print("Course name: ");
        String course = scanner.nextLine().trim();

        if (course.isEmpty()) {
            System.out.println("Course name cannot be empty.");
            return;
        }

        System.out.print("Date (YYYY-MM-DD): ");
        String dateInput = scanner.nextLine().trim();

        LocalDate date;

        try {
            date = LocalDate.parse(dateInput);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date. Please use YYYY-MM-DD.");
            return;
        }

        if (date.isAfter(LocalDate.now())) {
            System.out.println("Study date cannot be in the future.");
            return;
        }

        System.out.print("Minutes studied: ");
        String minutesInput = scanner.nextLine().trim();

        int minutes;

        try {
            minutes = Integer.parseInt(minutesInput);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number of minutes.");
            return;
        }

        if (minutes <= 0) {
            System.out.println("Study time must be greater than zero.");
            return;
        }

        StudySession session = new StudySession(course, date, minutes);
        sessions.add(session);

        System.out.println("\nStudy session recorded!");
        System.out.println("Course: " + course);
        System.out.println("Date: " + date);
        System.out.println("Duration: " + minutes + " minutes");
    }
}
