package ex01;


public class Threads extends Thread {
    private int count;
    private String name;
    private Printer printing;

    public Threads(Printer printer, int count, String name){
        this.count = count;
        this.name = name;
        this.printing = printer;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            try {
                if (name.equals("Egg"))
                    printing.printName(this.name,1);
                else
                    printing.printName(this.name,-1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
