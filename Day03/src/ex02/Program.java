package ex02;

import java.util.Random;

public class Program {
    public static void main(String[] args) throws InterruptedException {
        int size = 0;
        int threads = 0;
        int distance;
        size = parserSize(args);
        threads = parserThreads(args);
        if (threads > size || size == -1 || threads == -1){
            System.out.println("Wrong params");
            return;
        }
        int[] array = generate(size);
        distance = size / threads;
        if (size % threads != 0)
            distance++;
        Launch launch = new Launch(size, threads, distance, array);
        launch.starting();

    }
    public static int[] generate(int size){
        int[] array = new int[size];
        int sum = 0;
        Random random = new Random();

        array = random.ints(size, -1000, 1000).toArray();
        for (int i = 0; i < size; i++){
            sum += array[i];
            System.out.println(array[i]);
        }
        System.out.println("Sum: " + sum);
        return array;
    }

    public static int parserSize(String[] args){
        int size = 0;
        if (!args[0].contains("="))
            return -1;
        for (int index = args[0].indexOf('=') + 1; index < args[0].length();index++){
            size = size * 10 + (args[0].charAt(index) - '0');
        }
        return size;
    }

    public static int parserThreads(String[] args){
        int threads = 0;
        if (!args[1].contains("="))
            return -1;
        for (int index = args[1].indexOf('=') + 1; index < args[1].length();index++){
            threads = threads * 10 + (args[1].charAt(index) - '0');
        }
        return threads;
    }
}
