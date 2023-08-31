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
                    enterSteps(); //done
                    break;
                case 2:
                    printStatistics(); //done
                    break;
                case 3:
                    changeTarget(); //done
                    break;
                case 4:
                    monthInit();
                    break;
                case 5:
                    kill(); //done
                default:
                    System.out.println("incorrect input. try again");
            }
        }
    }
    public static void printChoices() {
        System.out.println("you should only enter a number");
        System.out.println("1. enter steps");
        System.out.println("2. statistics for the month");
        System.out.println("3. change steps target");
        System.out.println("4. initialize month");
        System.out.println("5. exit");
    }
}