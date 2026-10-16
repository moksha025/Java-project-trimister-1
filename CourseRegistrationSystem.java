 import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

class InvalidCreditException extends Exception {
    public InvalidCreditException(String message) {
        super(message);
    }
}

class CourseItem {
    private String code;
    private String name;
    private int credits;

    public CourseItem(String code, String name, int credits) throws InvalidCreditException {
        if (credits <= 0) {
            throw new InvalidCreditException("Credits must be greater than zero.");
        }
        this.code = code;
        this.name = name;
        this.credits = credits;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return code + " - " + name + " (" + credits + " Credits)";
    }
}

public class CourseRegistrationSystem {

    private static final int MAX_CREDITS = 24;
    private static final Path FILE_PATH = Paths.get("academic_session.txt");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<CourseItem> masterCatalog = new ArrayList<>();
        try {
            masterCatalog.add(new CourseItem("CS101", "Java Programming", 4));
            masterCatalog.add(new CourseItem("MA102", "Linear Algebra", 3));
            masterCatalog.add(new CourseItem("PH103", "Physics", 4));
            masterCatalog.add(new CourseItem("EN104", "Technical English", 2));
            masterCatalog.add(new CourseItem("CS105", "Data Structures", 4));
        } catch (InvalidCreditException e) {
            System.out.println("Initialization error: " + e.getMessage());
        }

        Map<String, CourseItem> courseMap = new HashMap<>();
        for (CourseItem c : masterCatalog) {
            courseMap.put(c.getCode(), c);
        }

        Set<String> registeredCodes = new HashSet<>();
        int totalCredits = 0;

        String[][] timetable = new String[5][4];
        initializeTimetable(timetable);

        boolean running = true;

        System.out.println("==================================================");
        System.out.println("     COURSE REGISTRATION & TIMETABLE BUILDER      ");
        System.out.println("==================================================");

        while (running) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Master Course Catalog ---");
                    masterCatalog.forEach(System.out::println);
                    break;

                case 2:
                    System.out.print("Enter target credits to filter: ");
                    int targetCredits = scanner.nextInt();
                    scanner.nextLine();

                    List<CourseItem> filtered = masterCatalog.stream()
                            .filter(c -> c.getCredits() == targetCredits)
                            .collect(Collectors.toList());

                    System.out.println("\n--- Filtered Results ---");
                    if (filtered.isEmpty()) {
                        System.out.println("No courses found.");
                    } else {
                        filtered.forEach(System.out::println);
                    }
                    break;

                case 3:
                    System.out.print("Enter Course Code to Register: ");
                    String codeInput = scanner.nextLine().trim().toUpperCase();

                    if (courseMap.containsKey(codeInput)) {
                        CourseItem course = courseMap.get(codeInput);
                        try {
                            if (registeredCodes.contains(codeInput)) {
                                System.out.println("Notice: Already registered for this course.");
                            } else if ((totalCredits + course.getCredits()) > MAX_CREDITS) {
                                throw new InvalidCreditException("Credit limit exceeded!");
                            } else {
                                registeredCodes.add(codeInput);
                                totalCredits += course.getCredits();
                                System.out.println("Success: Registered for " + course.getName());
                            }
                        } catch (InvalidCreditException e) {
                            System.out.println("Registration Error: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Error: Course code not found.");
                    }
                    break;

                case 4:
                    displayTimetable(timetable);
                    System.out.print("Enter Day Index (0-4): ");
                    int day = scanner.nextInt();
                    System.out.print("Enter Slot Index (0-3): ");
                    int slot = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Course Code to assign: ");
                    String slotCourse = scanner.nextLine().trim().toUpperCase();

                    if (validateSlot(timetable, day, slot)) {
                        timetable[day][slot] = slotCourse;
                        System.out.println("Timetable slot successfully booked.");
                    } else {
                        System.out.println("Error: Slot already occupied or out of bounds.");
                    }
                    break;

                case 5:
                    try {
                        List<String> sessionData = new ArrayList<>();
                        sessionData.add("Total Credits: " + totalCredits);
                        sessionData.add("Registered Courses: " + String.join(", ", registeredCodes));
                        Files.write(FILE_PATH, sessionData);
                        System.out.println("Session saved to file.");
                    } catch (IOException e) {
                        System.out.println("Error writing to file: " + e.getMessage());
                    }
                    break;

                case 6:
                    try {
                        if (Files.exists(FILE_PATH)) {
                            List<String> loadedLines = Files.readAllLines(FILE_PATH);
                            System.out.println("\n--- Loaded Session Data ---");
                            loadedLines.forEach(line -> System.out.println("- " + line));
                        } else {
                            System.out.println("No saved session file found.");
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading from file: " + e.getMessage());
                    }
                    break;

                case 7:
                    System.out.println("Exiting application.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. View Master Course Catalog");
        System.out.println("2. Filter Courses by Credits");
        System.out.println("3. Register for a Course");
        System.out.println("4. Manage Weekly Timetable Grid");
        System.out.println("5. Save Session to File");
        System.out.println("6. Load Session from File");
        System.out.println("7. Exit");
        System.out.print("Choose an option: ");
    }

    private static void initializeTimetable(String[][] grid) {
        for (String[] row : grid) {
            Arrays.fill(row, "Free Slot");
        }
    }

    private static boolean validateSlot(String[][] grid, int day, int slot) {
        if (day >= 0 && day < grid.length && slot >= 0 && slot < grid[day].length) {
            return grid[day][slot].equals("Free Slot");
        }
        return false;
    }

    private static void displayTimetable(String[][] grid) {
        System.out.println("\n--- WEEKLY TIMETABLE GRID ---");
        for (int i = 0; i < grid.length; i++) {
            System.out.print("Day " + i + ": ");
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print("[" + grid[i][j] + "] ");
            }
            System.out.println();
        }
    }
}