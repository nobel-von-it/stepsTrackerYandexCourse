import java.util.Scanner;

import static StepTracker.StepTracker.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        initMonthDataHashMap();
        while (true) {
            printChoices();
            switch (in.nextInt()) {
                case 1:
                    enterSteps();
                    break;
                case 2:
                    printStatistics();
                    break;
                case 3:
                    changeTarget();
                    break;
                case 4:
                    kill();
                default:
                    System.out.println("incorrect input. try again");
            }
        }
    }
    public static void printChoices() {
        System.out.println("1. enter steps.");
        System.out.println("2. statistics for the month");
        System.out.println("3. change steps target");
        System.out.println("4. exit");
    }
}