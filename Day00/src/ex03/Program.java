package ex03;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int currentWeek = 1;
        long all = 0;

        String input = scanner.nextLine();

        while (currentWeek <= 18 && !input.equals("42")) {
            if (!input.equals("Week " + currentWeek)){
                System.err.println("Illegal Argument");
                System.exit(-1);
            }
            all = collectGrade(getMin(scanner), all);
            currentWeek++;
            input = scanner.nextLine();
        }
        printResult(all, currentWeek - 1);
    }

    private static void printResult(long all, int number){
        if (all == 0)
            return;
        long result = all % 10;
        all /= 10;
        printResult(all, number - 1);
        System.out.print("Week " + number + " ");
        for (int i = 0; i < result; i++)
            System.out.print("=");
        System.out.println(">");
    }

    private static long collectGrade(int min, long all){
        all = (all * 10) + min;
        return all;
    }

    private static int getMin(Scanner scanner){
        int min = scanner.nextInt();
        int input;

        for (int i = 0; i < 4; i++){
            input = scanner.nextInt();
            if (input < min)
                min = input;
        }
        scanner.nextLine();
        return min;
    }
}
