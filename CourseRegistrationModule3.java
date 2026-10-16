import java.util.Scanner;
import java.util.Arrays;

public class  CourseRegistrationModule3
{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String[][] timetable = new String[5][4];
        initializeTimetable(timetable);

        boolean running = true;

        while (running) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayTimetable(timetable);
                    break;
                case 2:
                    System.out.print("Enter Day Index (0-4): ");
                    int day = scanner.nextInt();
                    System.out.print("Enter Slot Index (0-3): ");
                    int slot = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Course Name: ");
                    String course = scanner.nextLine();

                    if (validateSlot(timetable, day, slot)) {
                        assignCourse(timetable, day, slot, course);
                        System.out.println("Slot successfully booked.");
                    } else {
                        System.out.println("Error: Slot already occupied or out of bounds.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting Module 3.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }

    public static void initializeTimetable(String[][] grid) {
        for (String[] row : grid) {
            Arrays.fill(row, "Free Slot");
        }
    }

    public static void printMenu() {
        System.out.println("\n--- TIMETABLE MANAGER ---");
        System.out.println("1. View Weekly Timetable Grid");
        System.out.println("2. Book Course Slot");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }

    public static boolean validateSlot(String[][] grid, int day, int slot) {
        if (day >= 0 && day < grid.length && slot >= 0 && slot < grid[day].length) {
            return grid[day][slot].equals("Free Slot");
        }
        return false;
    }

    public static void assignCourse(String[][] grid, int day, int slot, String courseName) {
        grid[day][slot] = courseName;
    }

    public static void displayTimetable(String[][] grid) {
        System.out.println("\n--- WEEKLY TIMETABLE GRID (5 Days x 4 Slots) ---");
        for (int i = 0; i < grid.length; i++) {
            System.out.print("Day " + i + ": ");
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print("[" + grid[i][j] + "] ");
            }
            System.out.println();
        }
    }
}