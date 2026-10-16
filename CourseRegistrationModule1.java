import java.util.Scanner;

 
public class CourseRegistrationModule1 {

    public static void main(String[] args) {
         
        final int MAX_CREDITS = 24;
        final double COST_PER_CREDIT = 150.00;

       
        Scanner scanner = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("  COURSE REGISTRATION & TIMETABLE BUILDER ");
        System.out.println("  Module 1: Student Intake & Verification ");
        System.out.println("==========================================");

        
        System.out.print("Enter Student ID (Integer): ");
        int studentId = scanner.nextInt();
        
        scanner.nextLine();  

        System.out.print("Enter Student Full Name: ");
        String studentName = scanner.nextLine();

        System.out.print("Enter Desired Semester Credits (Max 24): ");
        int requestedCredits = scanner.nextInt();

        System.out.print("Are you an International Student? (true/false): ");
        boolean isInternational = scanner.nextBoolean();

        
        double baseFee = requestedCredits * COST_PER_CREDIT;
          
        double totalFee = isInternational ? (baseFee * 1.15) : baseFee;
        int roundedFee = (int) totalFee; // Explicit narrowing cast for reporting

        // Basic validation logic (Pre-condition and constraint checking)
        System.out.println("\n------------------------------------------");
        System.out.println("          REGISTRATION SUMMARY            ");
        System.out.println("------------------------------------------");
        System.out.println("Student ID     : " + studentId);
        System.out.println("Student Name   : " + studentName.trim().toUpperCase());
        System.out.println("Requested Credits: " + requestedCredits);
        
        if (requestedCredits > 0 && requestedCredits <= MAX_CREDITS) {
            System.out.println("Status         : APPROVED (Within Credit Limit)");
            System.out.println("Exact Fee      : $" + totalFee);
            System.out.println("Rounded Fee    : $" + roundedFee + " (Casted to int)");
        } else {
            System.out.println("Status         : REJECTED (Exceeds Max Credits or Invalid)");
        }

        System.out.println("==========================================");
        
        scanner.close();
    }
}