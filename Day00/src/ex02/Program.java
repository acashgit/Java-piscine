package ex02;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        int result = 0;

        while (input != 42) {
            if (isPrime(mySum(input)))
                result++;
            input = scanner.nextInt();
        }
        System.out.println("Count of coffee-request - " + result);
    }

    private static boolean isPrime(int digit){
        if (digit <= 1) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        if (digit == 2){
            return true;
        }
        for (int i = 2; i * i <= digit; i++){
            if (digit % i == 0)
                return false;
        }
        return true;
    }
    private static int mySum(int digit){
        int result = 0;

        while (digit != 0){
            result += digit % 10;
            digit /= 10;
        }
        return result;
    }
}
