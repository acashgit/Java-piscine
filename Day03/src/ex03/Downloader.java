package ex03;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Downloader {
    private int lastLoaded = 0;

    public Downloader(){
    }

    public void downloadUsingNIO(String urlStr, String file, int loadIndex) throws IOException {
        int tmp = getLastLoaded();
        this.lastLoaded++;
        System.out.println("Thread-" + loadIndex + " start download file number " + tmp);
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
        System.out.println("Thread-" + loadIndex + " finish download file number " + tmp);
    }

    public int getLastLoaded() {
        return lastLoaded;
    }
}
