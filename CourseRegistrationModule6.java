 import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

class CourseItem {
    private String code;
    private String name;
    private int credits;

    public CourseItem(String code, String name, int credits) {
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

public class CourseRegistrationModule6 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<CourseItem> masterCatalog = new ArrayList<>();
        masterCatalog.add(new CourseItem("CS101", "Java Programming", 4));
        masterCatalog.add(new CourseItem("MA102", "Linear Algebra", 3));
        masterCatalog.add(new CourseItem("PH103", "Physics", 4));
        masterCatalog.add(new CourseItem("EN104", "Technical English", 2));
        masterCatalog.add(new CourseItem("CS105", "Data Structures", 4));

        Set<String> registeredCodes = new HashSet<>();
        Map<String, CourseItem> courseMap = new HashMap<>();
        for (CourseItem c : masterCatalog) {
            courseMap.put(c.getCode(), c);
        }

        boolean running = true;

        System.out.println("==========================================");
        System.out.println("  COURSE REGISTRATION & TIMETABLE BUILDER ");
        System.out.println("  Module 6: Collections & Streams         ");
        System.out.println("==========================================");

        while (running) {
            System.out.println("\n1. View Master Catalog");
            System.out.println("2. Filter Courses by Credits (Stream API)");
            System.out.println("3. Group Courses by Credits (Collectors.groupingBy)");
            System.out.println("4. Register Course (HashSet & HashMap Lookup)");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Master Course Catalog ---");
                    masterCatalog.forEach(System.out::println);
                    break;

                case 2:
                    System.out.print("Enter target credits to filter (e.g., 4): ");
                    int targetCredits = scanner.nextInt();
                    scanner.nextLine();

                    List<CourseItem> filtered = masterCatalog.stream()
                            .filter(c -> c.getCredits() == targetCredits)
                            .collect(Collectors.toList());

                    System.out.println("\n--- Filtered Results ---");
                    if (filtered.isEmpty()) {
                        System.out.println("No courses found with " + targetCredits + " credits.");
                    } else {
                        filtered.forEach(System.out::println);
                    }
                    break;

                case 3:
                    Map<Integer, List<CourseItem>> grouped = masterCatalog.stream()
                            .collect(Collectors.groupingBy(CourseItem::getCredits));

                    System.out.println("\n--- Grouped by Credits ---");
                    grouped.forEach((cred, list) -> {
                        String names = list.stream()
                                .map(CourseItem::getName)
                                .collect(Collectors.joining(", "));
                        System.out.println(cred + " Credits: " + names);
                    });
                    break;

                case 4:
                    System.out.print("Enter Course Code to Register (e.g., CS101): ");
                    String codeInput = scanner.nextLine().toUpperCase();

                    if (courseMap.containsKey(codeInput)) {
                        if (registeredCodes.add(codeInput)) {
                            System.out.println("Success: Registered for " + courseMap.get(codeInput).getName());
                        } else {
                            System.out.println("Notice: Already registered for this course.");
                        }
                    } else {
                        System.out.println("Error: Course code not found in catalog.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting Module 6.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }
}