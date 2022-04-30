package edu.school21.sockets.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class    Client {
    public Client(){}

    public void start(String host, int port){
        try{
            Socket socket = new Socket(host, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            while(true){
                String serverMessage = in.readLine();
                if (serverMessage.equals("exit")) {
                    System.out.println("Server is close");
                    System.exit(0);
                }else if (serverMessage.equals("Successful!") || serverMessage.equals("this user already exist")) {
                    System.out.println(serverMessage);
                    System.exit(0);
                }else {
                    System.out.println(serverMessage);
                    System.out.print(">");
                    String myAnswer = reader.readLine();
                    write(printWriter, myAnswer);
                }
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
