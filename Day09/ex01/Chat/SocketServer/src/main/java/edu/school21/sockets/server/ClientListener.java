package edu.school21.sockets.server;

import edu.school21.sockets.services.MessageService;
import edu.school21.sockets.services.UsersService;

import java.io.*;
import java.net.Socket;

public class ClientListener extends Thread{
    private Socket socket;
    private String login;
    private String password;
    private UsersService usersService;
    private MessageService messageService;
    private BufferedReader in;
    private BufferedWriter out;

    public ClientListener(Socket socket, UsersService usersService, MessageService messageService) throws IOException {
        this.socket = socket;
        this.usersService = usersService;
        this.messageService = messageService;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        write("Hello from server!");

        String command = in.readLine();
        if (command.equals("signUp")){
            signUp();
            exitFromServer();
        } else if (command.equals("signIn")){
            write("Enter username:");
            login = in.readLine();
            for (ClientListener c1 : Server.clientListeners){
                if (c1.login.equals(login)){
                    errorExit("This user already in!");
                }
            }
            write("Enter password:");
            password = in.readLine();
            if (!usersService.singIn(login, password))
                errorExit("Wrong login or password");
            write("Start messaging");
            start();
        } else {
            errorExit("wrong command");
        }
    }

    @Override
    public void run() {
        String text;
        try{
            while (true){
                text = in.readLine();
                if (text.equals("Exit")){
                    exitFromServer();
                    break;
                }
                messageService.saveMessage(login, text);
                for (ClientListener cl : Server.clientListeners) {
                    cl.write(login + ": " + text);
                    cl.write(login + ": " + text);
                    cl.write(login + ": " + text);
                    cl.write(login + ": " + text);
                }
            }
        }catch (IOException ignored){}
    }

//    private void signIn() throws IOException {
//        write("Enter username:");
//        login = in.readLine();
//        for (ClientListener c1 : Server.clientListeners){
//            if (c1.login.equals(login)){
//                errorExit("This user already in!");
//            }
//        }
//        write("Enter password:");
//        password = in.readLine();
//        if (!usersService.singIn(login, password))
//            errorExit("Wrong login or password");
//        write("Start messaging");
//    }

    private void signUp() throws IOException {
        write("Enter username:");
        login = in.readLine();
        write("Enter password:");
        password = in.readLine();
        if (usersService.signUp(login, password))
            write("Successful!");
        else
            write("this user already exist");
    }

    private void write(String text){
        try {
            out.write(text + "\n");
            out.flush();
        } catch (IOException ignored) {}
    }

    private void errorExit(String errMessage) {
        try {
            if(!socket.isClosed()) {
                System.err.println(errMessage);
                write("exit");
                socket.close();
                in.close();
                out.close();
                throw new IllegalArgumentException("Client was not added!");
            }
        } catch (IOException ignored) {}
    }

    private void exitFromServer() {
        try {
            if(!socket.isClosed()) {
                write("exit");
                socket.close();
                in.close();
                out.close();
                Server.clientListeners.remove(this);
            }
        } catch (IOException ignored) {}
    }
}
