import java.util.Scanner;

public class StudyHoursTracker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
                    System.out.println("Study session recording is coming soon.");
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
}