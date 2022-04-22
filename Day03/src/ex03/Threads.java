package ex03;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Threads extends Thread{
    private ArrayList<String> urls;
    private int loadIndex;
    private Downloader downloader;

    public Threads(ArrayList<String> urls, int loadIndex, Downloader downloader){
        this.urls = urls;
        this.loadIndex = loadIndex;
        this.downloader = downloader;
    }

    @Override
    public void run() {
        while(downloader.getLastLoaded() < urls.size() - 1){
            try {
                int current = downloader.getLastLoadedUp();
                downloader.downloadUsingNIO(urls, "file" + current, loadIndex, current);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
