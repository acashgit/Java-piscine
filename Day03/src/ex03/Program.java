package ex03;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) throws InterruptedException {
        int threads = 0;
        String path = "C:\\Java-pool\\mygit\\Java-pool\\Day03\\src\\ex03\\signature.txt";
        ArrayList<String> urls = new ArrayList<>();

        if (args.length < 1)
            return;
        urls = getFileUrl(path);
        threads = parserThreads(args);
        if (threads == -1){
            System.out.println("Wrong params");
            return;
        }
        Threads[] threadArray = new Threads[threads];
        Downloader downloader = new Downloader();
        for (int i = 0; i < threads; i++){
            threadArray[i] = new Threads(urls, i + 1, downloader);
        }
        for (int i = 0; i < threads; i++)
            threadArray[i].start();
        for (int i = 0; i < threads; i++)
            threadArray[i].join();
    }

    public static ArrayList<String> getFileUrl(String path){
        ArrayList<String> urls = new ArrayList<>();
        try{
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null){
                urls.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return urls;
    }

    public static int parserThreads(String[] args){
        int threads = 0;
        if (!args[0].contains("="))
            return -1;
        for (int index = args[0].indexOf('=') + 1; index < args[0].length();index++){
            threads = threads * 10 + (args[0].charAt(index) - '0');
        }
        return threads;
    }
}
