package ex00;

public class Threads extends Thread {
    private int count;
    private String name;

    public Threads(int count, String name){
        this.count = count;
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++)
            System.out.println(name);
    }
}
