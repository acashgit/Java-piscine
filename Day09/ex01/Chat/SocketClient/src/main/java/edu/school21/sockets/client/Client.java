package edu.school21.sockets.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class    Client {
    public Client(){}

    public void start(String host, int port) throws IOException {
        try{
            Socket socket = new Socket(host, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            NonblockingBufferedReader nonBlock = new NonblockingBufferedReader(in);
            String serverMessage = "";
            while(true){
                while((serverMessage = nonBlock.readLine()) != null) {
                    if (serverMessage.equals("Exit")) {
                        System.out.println("Server is close");
                        System.exit(0);
                    } else if (serverMessage.equals("Successful!") || serverMessage.equals("this user already exist")) {
                        write(printWriter, "Exit");
                        System.exit(0);
                    }
                }
                System.out.print(">");
                String myAnswer = reader.readLine();
                write(printWriter, myAnswer);
            }
        } catch (IOException e) {
            System.err.println("Server not found");
        }
    }

    private void write(PrintWriter printWriter, String text) {
        printWriter.println(text);
        printWriter.flush();
    }
}

class NonblockingBufferedReader {
    private final BlockingQueue<String> lines = new LinkedBlockingQueue<String>();
    private volatile boolean closed = false;
    private Thread backgroundReaderThread = null;

    public NonblockingBufferedReader(final BufferedReader bufferedReader) {
        backgroundReaderThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!Thread.interrupted()) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        lines.add(line);
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    closed = true;
                }
            }
        });
        backgroundReaderThread.setDaemon(true);
        backgroundReaderThread.start();
    }

    public String readLine() throws IOException {
        try {
            return closed && lines.isEmpty() ? null : lines.poll(500L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new IOException("The BackgroundReaderThread was interrupted!", e);
        }
    }

    public void close() {
        if (backgroundReaderThread != null) {
            backgroundReaderThread.interrupt();
            backgroundReaderThread = null;
        }
    }
}