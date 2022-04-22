package ex02;

public class Threads extends Thread{
    private int distance;
    private int sum = 0;
    private int number;
    private int index;
    private int[] array;

    public Threads(int number, int distance, int index, int[] array){
        this.distance = distance;
        this.index = index;
        this.array = array;
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = index; i < index + distance; i++){
            sum += this.array[i];
        }
        Calculator calculator = new Calculator();
        calculator.makeSum(sum);
        System.out.println("Thread " + this.number + ": from " + this.index + " to " +
                (this.index + this.distance) + " sum is " + sum);
    }
}
