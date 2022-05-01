package edu.school21.tanks.server;

import edu.school21.tanks.objects.Bullet;
import edu.school21.tanks.objects.Tanks;
import edu.school21.tanks.services.StatService;

import java.io.*;
import java.net.Socket;
import java.rmi.ServerError;
import java.util.LinkedList;

public class ClientListener extends Thread{
    private Socket socket;
    private int id;
    private BufferedReader in;
    private BufferedWriter out;
    private Tanks me;
    private Tanks enemy;
    private LinkedList<Bullet> bulletList;
    private StatService statService;

    public ClientListener(Socket socket, Tanks me, Tanks enemy, int id, LinkedList<Bullet> bulletList, StatService statService) throws IOException {
        this.socket = socket;
        this.statService = statService;
        this.id = id;
        this.bulletList = bulletList;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.me = me;
        this.enemy = enemy;
    }

    @Override
    public void run() {
        String text;
        try{
            while (me.getHealth() > 0 && enemy.getHealth() > 0){
                text = in.readLine();
                if (text.equals("RIGHT"))
                    me.moveRight();
                else if (text.equals("LEFT"))
                    me.moveLeft();
                else if (text.equals("FIRE"))
                {
                    me.shot();
                    Bullet bullet = new Bullet(me,enemy);
                    bulletList.add(bullet);
                    BulletThread bulletThread = new BulletThread(bullet , bulletList);
                    bulletThread.start();
                }
            }
            exitFromServer();
        }catch (IOException ignored){
            exitFromServer();
        }
    }

    public void write(String text){
        try {
            out.write(text + "\n");
            out.flush();
        } catch (IOException ignored) {}
    }

    private void exitFromServer() {
        try {
            if(!socket.isClosed()) {
                write("EXIT");
                write(statService.send1(me));
                write(statService.send2(enemy));
                socket.close();
                in.close();
                out.close();
                Server.clientListeners.remove(this);
                Server.changeEnd();
            }
        } catch (IOException ignored) {}
    }

    public int getCLientId(){
        return this.id;
    }
}