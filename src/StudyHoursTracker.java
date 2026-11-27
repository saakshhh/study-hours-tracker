import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;

public class StudyHoursTracker {

    private static final Path DATA_FILE = Path.of("study_sessions.csv");

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
        ArrayList<StudySession> sessions = loadSessions();
        boolean running = true;

        System.out.println("Welcome to Study Hours Tracker!");

        if (!sessions.isEmpty()) {
            System.out.println("Loaded " + sessions.size() + " saved study session(s).");
        }

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
                    showWeeklyMinutes(sessions);
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

        // commas and quotes would need extra handling when reading the CSV
        if (course.contains(",") || course.contains("\"")) {
            System.out.println("Course name cannot contain commas or quotation marks.");
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

        // don't keep a session in memory if saving it to the file failed
        if (!saveSession(session)) {
            return;
        }

        sessions.add(session);

        System.out.println("\nStudy session recorded!");
        System.out.println("Course: " + course);
        System.out.println("Date: " + date);
        System.out.println("Duration: " + minutes + " minutes");
    }

    private static boolean saveSession(StudySession session) {
        try {
            boolean needsHeader = !Files.exists(DATA_FILE)
                    || Files.size(DATA_FILE) == 0;

            String row = session.course + ","
                    + session.date + ","
                    + session.minutes + "\n";

            if (needsHeader) {
                row = "course,date,minutes\n" + row;
            }

            Files.writeString(DATA_FILE, row,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);

            return true;

        } catch (IOException e) {
            System.out.println("Could not save study session: " + e.getMessage());
            return false;
        }
    }

    private static ArrayList<StudySession> loadSessions() {
        ArrayList<StudySession> sessions = new ArrayList<>();

        if (!Files.exists(DATA_FILE)) {
            return sessions;
        }

        try {
            for (String line : Files.readAllLines(DATA_FILE)) {
                if (line.isBlank() || line.equals("course,date,minutes")) {
                    continue;
                }

                String[] parts = line.split(",", -1);

                if (parts.length != 3) {
                    System.out.println("Skipping an invalid CSV row.");
                    continue;
                }

                try {
                    String course = parts[0].trim();
                    LocalDate date = LocalDate.parse(parts[1].trim());
                    int minutes = Integer.parseInt(parts[2].trim());

                    if (!course.isEmpty() && minutes > 0) {
                        sessions.add(new StudySession(course, date, minutes));
                    } else {
                        System.out.println("Skipping a session with invalid data.");
                    }

                } catch (DateTimeParseException | NumberFormatException e) {
                    System.out.println("Skipping a session with invalid data.");
                }
            }

        } catch (IOException e) {
            System.out.println("Could not load saved sessions: " + e.getMessage());
        }

        return sessions;
    }

    private static LocalDate getStartOfWeek() {
        LocalDate today = LocalDate.now();

        // use Monday as the starting point for all weekly calculations
        return today.minusDays(today.getDayOfWeek().getValue() - 1);
    }

    private static HashMap<String, Integer> calculateWeeklyMinutes(ArrayList<StudySession> sessions) {

        HashMap<String, Integer> minutesByCourse = new HashMap<>();

        LocalDate startOfWeek = getStartOfWeek();
        LocalDate endOfWeek = startOfWeek.plusDays(6);

        for (StudySession session : sessions) {
            if (session.date.isBefore(startOfWeek)
                    || session.date.isAfter(endOfWeek)) {
                continue;
            }

            int previousMinutes = minutesByCourse.getOrDefault(session.course, 0);
            minutesByCourse.put(session.course, previousMinutes + session.minutes);
        }

        return minutesByCourse;
    }

    private static String formatStudyTime(int totalMinutes) {
        int hours = totalMinutes / 60;
        int minutes = totalMinutes % 60;

        if (hours == 0) {
            return minutes + " min";
        }

        return hours + " hr " + minutes + " min";
    }

    private static void showWeeklyMinutes(ArrayList<StudySession> sessions) {
        HashMap<String, Integer> weeklyMinutes = calculateWeeklyMinutes(sessions);

        LocalDate startOfWeek = getStartOfWeek();
        LocalDate endOfWeek = startOfWeek.plusDays(6);

        System.out.println("\n=== Weekly Study Summary ===");
        System.out.println("Week: " + startOfWeek + " to " + endOfWeek);

        if (weeklyMinutes.isEmpty()) {
            System.out.println("\nNo study sessions recorded this week.");
            return;
        }

        ArrayList<String> courses = new ArrayList<>(weeklyMinutes.keySet());
        Collections.sort(courses, String.CASE_INSENSITIVE_ORDER);

        System.out.println("\nStudy Time by Course");
        System.out.println("--------------------------------");

        int totalMinutes = 0;

        for (String course : courses) {
            int minutes = weeklyMinutes.get(course);
            totalMinutes += minutes;

            System.out.printf("%-20s %s%n", course, formatStudyTime(minutes));
        }

        System.out.println("--------------------------------");
        System.out.printf("%-20s %s%n", "Total", formatStudyTime(totalMinutes));
    }
}
