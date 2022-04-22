package ex02;

public class Calculator {
    static int allSum = 0;
    public void makeSum(int sum){
        allSum += sum;
    }

    public void getResult(){
        System.out.println("Sum by threads: " + allSum);
    }
}
