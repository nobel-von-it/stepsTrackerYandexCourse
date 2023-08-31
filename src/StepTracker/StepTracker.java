package StepTracker;

import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.time.Month;
import java.util.*;

public class StepTracker {
    public static class MonthData {
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
        if (stepTarget <= 0) {
            System.out.println("error. step target must be positive");
            changeTarget();
        }
        System.out.println("your target: " + stepTarget);
    }

    static HashMap<Integer, MonthData> monthDataHashMap = new HashMap<>();

    public static void initMonthDataHashMap() {
        for (int i = 1; i <= 12; i++) {
            monthDataHashMap.put(i, new MonthData(new DateFormatSymbols().getMonths()[i - 1], new int[30], new int[30]));
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
                tmpMonthData.days[day - 1] = day;
                tmpMonthData.steps[day - 1] = step;
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
        Scanner in = new Scanner(System.in);
        System.out.println("enter month number");
        List<String> stepsList = new ArrayList<>();
        int monthNumber = in.nextInt();
        int totalSteps = 0, maxSteps = -1, averageSteps, maxStreak = -1, countStreak = 0;
        String  calorie, distance;
        DecimalFormat f = new DecimalFormat("##.00");

        MonthData currentMonthData = monthDataHashMap.get(monthNumber);
        for (int i = 0; i < currentMonthData.days.length; i++) {
            totalSteps += currentMonthData.steps[i];
            if (currentMonthData.steps[i] >= stepTarget) {
                countStreak++;
            } else {
                if (countStreak > maxStreak)
                    maxStreak = countStreak;
                countStreak = 0;
            }
            stepsList.add((i + 1) + " day: " + currentMonthData.steps[i]);
            if (currentMonthData.steps[i] > maxSteps)
                maxSteps = currentMonthData.steps[i];
        }
        averageSteps = totalSteps / 30;
        distance = f.format(((double) totalSteps * 75) / 100000);
        calorie = f.format(((double) totalSteps * 50) / 1000);

        System.out.printf("""
                days data
                %s
                other data:
                total monthly steps: %d,
                max daily steps: %d,
                average monthly steps: %d,
                total distance in km: %s km,
                lost calorie: %s kcal,
                maximum goal streak: %d.
                """, stepsList, totalSteps, maxSteps, averageSteps, distance, calorie, maxStreak);
    }

    public static void kill() {
        System.exit(0);
    }

    public static void monthInit() {
        Scanner in = new Scanner(System.in);
        System.out.println("enter the number of the month you want to initialize");
        int monthNumber = in.nextInt();
        Random random = new Random();
        int[] days = new int[30];
        int[] steps = new int[30];
        for (int i = 0; i < 30; i++) {
            days[i] = i + 1;
            steps[i] = random.nextInt(3000) + 8000;
        }
        monthDataHashMap.put(monthNumber,
                new MonthData(new DateFormatSymbols().getMonths()[monthNumber - 1], days, steps));
    }
}
