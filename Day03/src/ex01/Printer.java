package ex01;

public class Printer {
    private int check = 1;

    public synchronized void printName(String name, int check) throws InterruptedException {
        if (this.check == check){
            System.out.println(name);
            notify();
            this.check = -this.check;
        }else {
            while (this.check != check)
                wait();
            System.out.println(name);
            notify();
            this.check = -this.check;
        }
    }
}
