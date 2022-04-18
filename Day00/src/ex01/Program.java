package ex01;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int digit = scanner.nextInt();
        int steps = 1;

        if (digit <= 1){
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        if (digit == 2){
            System.out.println("true " + steps);
            return;
        }
        for (int i = 2; i * i <= digit; i++){
            if (digit % i == 0){
                System.out.println("false " + steps);
                return;
            }
            steps++;
        }
        System.out.println("true " + steps);
    }
}
