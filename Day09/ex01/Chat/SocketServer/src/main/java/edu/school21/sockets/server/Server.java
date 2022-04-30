package edu.school21.sockets.server;

import edu.school21.sockets.services.MessageService;
import edu.school21.sockets.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

@Component
public class Server {
    @Autowired
    private UsersService usersService;
    @Autowired
    private MessageService messageService;
    public static LinkedList<ClientListener> clientListeners = new LinkedList<>();

    public Server(){}

    public void start(int port){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                try{
                    clientListeners.add(new ClientListener(socket, usersService, messageService));
                }catch(IllegalArgumentException e){
                    socket.close();
                }
            }
        } catch (IOException e) {
            System.err.println("Server is not created");
        }
    }
}
