package ex03;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

public class Downloader {
    private int lastLoaded = -1;

    public Downloader(){
    }

    public void downloadUsingNIO(ArrayList<String> urls, String file, int loadIndex, int current) throws IOException {
        String urlStr = urls.get(current);
        System.out.println("Thread-" + loadIndex + " start download file number " + (current + 1));
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
        System.out.println("Thread-" + loadIndex + " finish download file number " + (current + 1));
    }

    public int getLastLoaded() {
        return lastLoaded;
    }

    public int getLastLoadedUp() {
        lastLoaded += 1;
        return lastLoaded;
    }

}
