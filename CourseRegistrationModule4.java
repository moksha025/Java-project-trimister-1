 import java.util.Scanner;

class InvalidCreditException extends Exception {
    public InvalidCreditException(String message) {
        super(message);
    }
}

class Course {
    private String courseCode;
    private String courseName;
    private int credits;

    public Course(String courseCode, String courseName, int credits) throws InvalidCreditException {
        if (credits <= 0) {
            throw new InvalidCreditException("Credits must be greater than zero.");
        }
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return courseCode + " - " + courseName + " (" + credits + " Credits)";
    }
}

public class CourseRegistrationModule4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("==========================================");
        System.out.println("  COURSE REGISTRATION & TIMETABLE BUILDER ");
        System.out.println("  Module 4: OOP & Exception Handling      ");
        System.out.println("==========================================");

        while (running) {
            System.out.println("\n1. Create and Validate Course");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Course Code: ");
                        String code = scanner.nextLine();

                        System.out.print("Enter Course Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter Credits: ");
                        int credits = scanner.nextInt();
                        scanner.nextLine();

                        Course newCourse = new Course(code, name, credits);
                        System.out.println("\nSuccess: Course instantiated securely.");
                        System.out.println(newCourse.toString());

                    } catch (InvalidCreditException e) {
                        System.out.println("Custom Exception Caught: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Runtime Error: Invalid input format detected.");
                    } finally {
                        System.out.println("[Execution log: Validation sequence finalized.]");
                    }
                    break;

                case 2:
                    System.out.println("Exiting Module 4.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid selection.");
            }
        }
        scanner.close();
    }
}