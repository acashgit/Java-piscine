package edu.school21.sockets.server;

import edu.school21.sockets.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class Server {
    @Autowired
    private UsersService usersService;

    public Server(){}

    public void start(int port){

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            write(printWriter, "Hello from server!");

            String command = in.readLine();
            if (!command.equals("signUp")){
                write(printWriter, "exit");
                System.err.println("Wrong command");
                return;
            }

            write(printWriter, "Enter username:");
            String email = in.readLine();
            write(printWriter, "Enter password:");
            String password = in.readLine();

            if (usersService.signUp(email, password))
                write(printWriter, "Successful!");
            else
                write(printWriter, "this user already exist");
            write(printWriter, "exit");
        } catch (IOException e) {
            System.err.println("Server is not created");
        }

    }

    private void write(PrintWriter printWriter, String text){
        printWriter.println(text);
        printWriter.flush();
    }
}
