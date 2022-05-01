package edu.school21.tanks.server;

import edu.school21.tanks.objects.Bullet;

import java.util.LinkedList;

public class BulletThread extends Thread{
    private Bullet bullet;
    private LinkedList<Bullet> bulletList;

    public BulletThread(Bullet bullet, LinkedList<Bullet> bulletList){
        this.bullet = bullet;
        this.bulletList = bulletList;
    }

    @Override
    public void run() {
        while(bullet.getX() < 1000f && bullet.getX() > 10f && bullet.getY() > 10f && bullet.getY() < 1000f){
            if (bullet.fly()) {
                bullet.getSender().hitEnemy();
                bullet.getEnemy().hitMe();
                break;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }
}
