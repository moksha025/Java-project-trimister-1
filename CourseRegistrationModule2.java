import java.util.Scanner;

/**
 * Module 2: Control Flow - Selection & Iteration
 * Project: Course Registration & Timetable Builder
 */
public class CourseRegistrationModule2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true; // Flag-controlled loop variable

        // Master lists for courses and credits
        String[] availableCourses = {"CS101", "MA102", "PH103", "EN104"};
        int[] courseCredits = {4, 3, 4, 2};

        // Tracking registered state
        String[] registeredCourses = new String[5];
        int registeredCount = 0;
        int totalCredits = 0;
        final int MAX_CREDITS = 24;

        System.out.println("==========================================");
        System.out.println("  COURSE REGISTRATION & TIMETABLE BUILDER ");
        System.out.println("  Module 2: Control Flow & Navigation     ");
        System.out.println("==========================================");

        // Iteration: Flag-controlled while loop for application menu
        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. View Summary & Total Credits");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            int choice = scanner.nextInt();

            // Selection: Switch statement for menu routing
            switch (choice) {
                case 1:
                    System.out.println("\n--- Available Courses ---");
                    // Iteration: Standard for-loop for traversing arrays
                    for (int i = 0; i < availableCourses.length; i++) {
                        System.out.println((i + 1) + ". " + availableCourses[i] + " (" + courseCredits[i] + " Credits)");
                    }
                    break;

                case 2:
                    System.out.print("Enter course number to register (1-4): ");
                    int courseIdx = scanner.nextInt() - 1;

                    // Selection: if-else-if ladder with boundary and validation logic
                    if (courseIdx < 0 || courseIdx >= availableCourses.length) {
                        System.out.println("Error: Invalid course index selection.");
                    } else {
                        String selectedCourse = availableCourses[courseIdx];
                        int creditsToAdd = courseCredits[courseIdx];

                        // Search-for-condition & Loop control (break)
                        boolean alreadyRegistered = false;
                        for (int i = 0; i < registeredCount; i++) {
                            if (registeredCourses[i].equals(selectedCourse)) {
                                alreadyRegistered = true;
                                break; // Exit loop early once found
                            }
                        }

                        // Short-circuit & complex condition checking
                        if (alreadyRegistered) {
                            System.out.println("Warning: You are already registered for " + selectedCourse + ".");
                        } else if ((totalCredits + creditsToAdd) > MAX_CREDITS) {
                            System.out.println("Error: Cannot register. Exceeds maximum credit ceiling (" + MAX_CREDITS + ").");
                        } else {
                            // Accumulation pattern
                            registeredCourses[registeredCount] = selectedCourse;
                            registeredCount++;
                            totalCredits += creditsToAdd;
                            System.out.println("Success: Registered for " + selectedCourse + ".");
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--- REGISTRATION SUMMARY ---");
                    if (registeredCount == 0) {
                        System.out.println("No courses registered yet.");
                    } else {
                        System.out.print("Enrolled Courses: ");
                        // Iteration pattern with string formatting
                        for (int i = 0; i < registeredCount; i++) {
                            System.out.print(registeredCourses[i] + (i < registeredCount - 1 ? ", " : ""));
                        }
                        System.out.println("\nTotal Accumulated Credits: " + totalCredits + " / " + MAX_CREDITS);
                    }
                    break;

                case 4:
                    System.out.println("Exiting application. Session closed.");
                    running = false; // Flag toggle to terminate loop
                    break;

                default:
                    System.out.println("Invalid option. Please choose between 1 and 4.");
            }
        }

        scanner.close();
    }
}