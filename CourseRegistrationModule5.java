import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CourseRegistrationModule5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        Path filePath = Paths.get("registered_courses.txt");
        List<String> courseList = new ArrayList<>(Arrays.asList("java programming", "data structures", "operating systems", "database management"));

        System.out.println("==========================================");
        System.out.println("  COURSE REGISTRATION & TIMETABLE BUILDER ");
        System.out.println("  Module 5: Strings & File I/O            ");
        System.out.println("==========================================");

        while (running) {
            System.out.println("\n1. Normalize Course Name & Tokenize");
            System.out.println("2. Sort Courses Alphabetically (Library Algorithm)");
            System.out.println("3. Save Courses to File (Persistence)");
            System.out.println("4. Load Courses from File");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter raw course name: ");
                    String rawInput = scanner.nextLine();
                    String normalized = rawInput.trim().toLowerCase();
                    StringBuilder sb = new StringBuilder(normalized);
                    for (int i = 0; i < sb.length(); i++) {
                        if (i == 0 || sb.charAt(i - 1) == ' ') {
                            sb.setCharAt(i, Character.toUpperCase(sb.charAt(i)));
                        }
                    }
                    System.out.println("Normalized Title: " + sb.toString());
                    break;

                case 2:
                    Collections.sort(courseList, String.CASE_INSENSITIVE_ORDER);
                    System.out.println("Sorted Courses: " + courseList);
                    break;

                case 3:
                    try {
                        Files.write(filePath, courseList);
                        System.out.println("Courses successfully saved to " + filePath.toAbsolutePath());
                    } catch (IOException e) {
                        System.out.println("Error writing to file: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        if (Files.exists(filePath)) {
                            List<String> loadedLines = Files.readAllLines(filePath);
                            System.out.println("\n--- Loaded Data from File ---");
                            for (String line : loadedLines) {
                                System.out.println("- " + line);
                            }
                        } else {
                            System.out.println("No saved file found.");
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading from file: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("Exiting Module 5.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }
}