import java.util.Scanner;
// DO NOT import anything else

/**
 * This class forms Java Assignment 1, 2021
 */
public class StudentMarking {

    /**
     * The message that the main menu must display --- DO NOT CHANGE THIS
     */
    public final String MENU_TEMPLATE =
            "%nWelcome to the Student System. Please enter an option 0 to 3%n"
                    + "0. Exit%n"
                    + "1. Generate a student ID%n"
                    + "2. Capture marks for students%n"
                    + "3. List student IDs and average mark%n";
    /**
     * DO NOT CHANGE THIS
     */
    public final String NOT_FOUND_TEMPLATE =
            "No student id of %s exists";


   /* Do NOT change the two templates ABOVE this comment.
      DO CHANGE the templates BELOW.
   */

    // TODO (All questions) - Complete these templates which will be used throughout the program
    public final String ENTER_MARK_TEMPLATE = "Please enter mark %d for student %s%n";
    public final String STUDENT_ID_TEMPLATE = "Your studID is %s";
    public final String NAME_RESPONSE_TEMPLATE = "You entered a given name of %s and a surname of %s%n";
    public final String LOW_HIGH_TEMPLATE = "Student %s has a lowest mark of %d%nA highest mark of %d%n";
    public final String AVG_MARKS_TEMPLATE = "Student ***%s*** has an average of %5.2f%n";
    public final String COLUMN_1_TEMPLATE = "%4c%n";
    public final String COLUMN_2_TEMPLATE = "%4c%12c%n";
    public final String CHART_KEY_TEMPLATE = "Highest     Lowest%n%4d%12d%n";
    public final String REPORT_PER_STUD_TEMPLATE = "| Student ID %3d is %5s | Average is %5.2f |%n";

    /**
     * Creates a student ID based on user input
     *
     * @param sc Scanner reading {@link System#in} re-used from {@link StudentMarking#main(String[])}
     * @return a studentID according to the pattern specified in {@link StudentMarking#STUDENT_ID_TEMPLATE}
     */
    public String generateStudId(Scanner sc) {
        // DoneTODO (3.4) - Complete the generateStudId method which will allow a user to generate a student id
        String studId = "0"; // DoneMaybeTODO Don't have unnecessary initialisations
        sc.nextLine();
        System.out.printf(
                "Please enter your given name and surname (Enter 0 to return to main menu)%n");
        String input = sc.nextLine();
        if (input.equals("0")) {
            return studId;
        }
        //generating student id
        String[] names = input.split(" ");
        System.out.printf(NAME_RESPONSE_TEMPLATE, names[0], names[1]);
        char letterFirstName = Character.toUpperCase(names[0].charAt(0));
        char letterSurName = Character.toUpperCase(names[1].charAt(0));
        int sizeSurName = names[1].length();
        char letterFirstNameMiddle = names[0].charAt(names[0].length() / 2);
        char letterSurNameMiddle = names[1].charAt(names[1].length() / 2);

        studId = String.format("%c%c%02d%c%c", letterFirstName, letterSurName, sizeSurName, letterFirstNameMiddle, letterSurNameMiddle);
        System.out.printf(STUDENT_ID_TEMPLATE, studId);
        return studId;
    }

    /**
     * Reads three marks (restricted to a floor and ceiling) for a student and returns their mean
     *
     * @param sc     Scanner reading {@link System#in} re-used from {@link StudentMarking#main(String[])}
     * @param studId a well-formed ID created by {@link StudentMarking#generateStudId(Scanner)}
     * @return the mean of the three marks entered for the student
     */
    public double captureMarks(Scanner sc, String studId) {
        // TODO (3.5) - Complete the captureMarks method which will allow a user to input three mark for a chosen student
        // DO NOT change MAX_MARK and MIN_MARK
        final int MAX_MARK = 100;
        final int MIN_MARK = 0;

        double avg; // TODO Don't have unnecessary initialisations
        double sum = 0;
        int min = 101;
        int max = -1;
        final int NUM_MARKS = 3;
        String decide;

        for (int i = 0; i < NUM_MARKS; i++) {
            System.out.printf(ENTER_MARK_TEMPLATE, i + 1, studId);
            // checking input number 0 - 100
            int marks = sc.nextInt();
            while ((marks < MIN_MARK) || (marks > MAX_MARK)) {
                System.out.printf(ENTER_MARK_TEMPLATE, i + 1, studId);
                marks = sc.nextInt();
            }
            sum += marks;
            if (marks < min) {
                min = marks;
            }
            if (marks > max) {
                max = marks;
            }
        }

        avg = sum / NUM_MARKS;
        System.out.printf(LOW_HIGH_TEMPLATE, studId, min, max);
        System.out.printf(AVG_MARKS_TEMPLATE, studId, avg);

        //weird error
        sc.nextLine();
        System.out.printf("Would you like to print a bar chart? [y/n]%n");
        decide = sc.nextLine();
        if (decide.equals("y")) {
            printBarChart(studId, max, min);
        }
        return avg;
    }

    /**
     * outputs a simple character-based vertical bar chart with 2 columns
     *
     * @param studId a well-formed ID created by {@link StudentMarking#generateStudId(Scanner)}
     * @param high   a student's highest mark
     * @param low    a student's lowest mark
     */
    public void printBarChart(String studId, int high, int low) {
        // TODO (3.6) - Complete the printBarChart method which will print a bar chart of the highest and lowest results of a student
        char bar = '*';
        System.out.printf("Student id statistics: %s%n", studId);

        for (int i = high; i > 0; i--) {
            if ((i <= low)) {
                System.out.printf(COLUMN_2_TEMPLATE, bar, bar);
            } else {
                System.out.printf(COLUMN_1_TEMPLATE, bar);
            }
        }
        System.out.printf(CHART_KEY_TEMPLATE, high, low);


    }

    /**
     * Prints a specially formatted report, one line per student
     *
     * @param studList student IDs originally generated by {@link StudentMarking#generateStudId(Scanner)}
     * @param count    the total number of students in the system
     * @param avgArray mean (average) marks
     */
    public void reportPerStud(String[] studList,
                              int count,
                              double[] avgArray) {
        // TODO (3.7) - Complete the reportPerStud method which will print all student IDs and average marks
        for (int i = 0; i < count; i++) {
            System.out.printf(REPORT_PER_STUD_TEMPLATE, i + 1, studList[i], avgArray[i]);
        }
    }

    /**
     * The main menu
     */
    public void displayMenu() {
        //noinspection RedundantStringFormatCall
        System.out.printf(MENU_TEMPLATE);
    }

    /**
     * The controlling logic of the program. Creates and re-uses a {@link Scanner} that reads from {@link System#in}.
     *
     * @param args Command-line parameters (ignored)
     */
    public static void main(String[] args) {
        // TODO (3.3) - Complete the main method to give the program its core

        // DO NOT change sc, sm, EXIT_CODE, and MAX_STUDENTS
        Scanner sc = new Scanner(System.in);
        StudentMarking sm = new StudentMarking();
        final int EXIT_CODE = 0;
        final int MAX_STUDENTS = 5;

        // TODO Initialise these
        String[] keepStudId = new String[MAX_STUDENTS];
        double[] avgArray = new double[MAX_STUDENTS];

        // TODO Build a loop around displaying the menu and reading then processing input
        boolean menuOpen = true;
        int num_Students = 0;

        while (menuOpen) {
            sm.displayMenu();

            int menuOptionInput = sc.nextInt();

            // Handle invalid main menu input
            while (menuOptionInput > 3 || menuOptionInput < 0) {
                System.out.printf(
                        "You have entered an invalid option. Enter 0, 1, 2 or 3%n");// Skeleton: keep, unchanged
                sm.displayMenu();
                menuOptionInput = sc.nextInt();
            }
            switch (menuOptionInput) {
                case EXIT_CODE: {
                    System.out.printf("Goodbye%n");
                    menuOpen = false;
                    break;
                }
                case 1: {
                    if (num_Students >= 5) {
                        //noinspection RedundantStringFormatCall
                        System.out.printf("You cannot add any more students to the array");
                        break;
                    }
                    String StudentID = sm.generateStudId(sc);

                    keepStudId[num_Students] = StudentID;
                    num_Students++;
                    break;
                }
                case 2: {
                    int Student_position_array = 6;
                    boolean checkForNoneInList = true;


                    System.out.printf(
                            "Please enter the studId to capture their marks (Enter 0 to return to main menu)%n");
                    sc.nextLine();
                    String checkStudentID = sc.nextLine();

                    if (checkStudentID.equals("0")) {
                        break;
                    } else {
                        for (int i = 0; i < MAX_STUDENTS; i++) {
                            if (checkStudentID.equals(keepStudId[i])) {
                                checkForNoneInList = false;
                                Student_position_array = i;
                                break;
                            }
                        }
                        if (checkForNoneInList) {
                            System.out.printf(sm.NOT_FOUND_TEMPLATE, checkStudentID);
                            break;
                        } else {
                            String StudentID = keepStudId[Student_position_array];
                            double avg = sm.captureMarks(sc, StudentID);
                            avgArray[Student_position_array] = avg;
                        }
                    }
                    break;
                }
                case 3: {
                    sm.reportPerStud(keepStudId, num_Students, avgArray);
                    break;
                }
            }
        }
    }
}

/*
    TODO Before you submit:
         1. ensure your code compiles
         2. ensure your code does not print anything it is not supposed to
         3. ensure your code has not changed any of the class or method signatures from the skeleton code
         4. check the Problems tab for the specific types of problems listed in the assignment document
         5. reformat your code: Code > Reformat Code
         6. ensure your code still compiles (yes, again)
 */