package StepTracker;
import java.sql.SQLOutput;
import java.text.DateFormatSymbols;
import java.time.Month;
import java.util.*;

public class StepTracker {
    static class MonthData {
        String monthName;
        int[] days;
        int[] steps;

        public MonthData(String monthName, int[] day, int[] steps) {
            this.monthName = monthName;
            this.days = day;
            this.steps = steps;
        }

        @Override
        public String toString() {
            return "MonthData{" +
                    "monthName='" + monthName + '\'' +
                    ", day=" + Arrays.toString(days) +
                    ", steps=" + Arrays.toString(steps) +
                    '}';
        }
    }

    static int stepTarget = 10000;
    public static void changeTarget() {
        Scanner in = new Scanner(System.in);
        System.out.println("enter new target");
        stepTarget = in.nextInt();
        System.out.println("your target: " + stepTarget + ". right?");
        String answer = in.next();
        if (answer.equals("no")) {
            changeTarget();
        }
    }
    static HashMap<Integer, MonthData> monthDataHashMap = new HashMap<>();

    public static void initMonthDataHashMap() {
        for (int i = 1; i <= 12; i++) {
            monthDataHashMap.put(i, new MonthData(new DateFormatSymbols().getMonths()[i-1], new int[30], new int[30]));
        }
    }
    public static void enterSteps() {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("enter month, day and your total steps");
            String month = in.next();
            int day = in.nextInt();
            int step = in.nextInt();
            int monthNumber;

            try {
                monthNumber = getMonthNumber(month);
            } catch (Exception e) {
                System.out.println("month input error, try again");
                continue;
            }
            if (day <= 0 || day > 30) {
                System.out.println("day input error, try again");
                continue;
            }
            if (step < 0 || step > 100000) {
                System.out.println("steps input error, try again");
                continue;
            }
            if (monthDataHashMap.containsKey(monthNumber)) {
                MonthData tmpMonthData = monthDataHashMap.get(getMonthNumber(month));
                tmpMonthData.days[day-1] = day;
                tmpMonthData.steps[day-1] = step;
                monthDataHashMap.put(monthNumber, tmpMonthData);
            }
            System.out.println("continue?");
            String answer = in.next();
            if (answer.equals("no")) {
                break;
            }
        }
    }
    private static int getMonthNumber(String month) {
        return Month.valueOf(month.toUpperCase()).getValue();
    }
    public static void printStatistics() {
        System.out.println(monthDataHashMap);
    }
    public static void kill() {
        System.exit(0);
    }
}
