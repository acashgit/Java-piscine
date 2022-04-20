package ex00;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Son", 1000);
        User user2 = new User("Jin", 1000);
        User user3 = new User("Jin", 1000);
        System.out.println(user1.getBalance());
        System.out.println(user2.getBalance());
        System.out.println(user3.getBalance());
        Transaction send1 = new Transaction(user1, user2, "debit", 500);
        Transaction send2 = new Transaction(user1, user3, "credit", -500);
        System.out.println(send1.toString());
        System.out.println(send2.toString());
        System.out.println(user1.getBalance());
        System.out.println(user2.getBalance());
        System.out.println(user3.getBalance());
    }
}
