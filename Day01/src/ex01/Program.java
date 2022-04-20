package ex01;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Son", 1000);
        User user2 = new User("Jin", 777);
        User user3 = new User("Vu", -146);
        User user4 = new User("Biba", 100000);
        User user5 = new User("Boba", 999);
        System.out.println("User name " + user1.toString() + " user balance " + user1.getBalance() + " user id " + user1.getIdentifier());
        System.out.println("User name " + user2.toString() + " user balance " + user2.getBalance() + " user id " + user2.getIdentifier());
        System.out.println("User name " + user3.toString() + " user balance " + user3.getBalance() + " user id " + user3.getIdentifier());
        System.out.println("User name " + user4.toString() + " user balance " + user4.getBalance() + " user id " + user4.getIdentifier());
        System.out.println("User name " + user5.toString() + " user balance " + user5.getBalance() + " user id " + user5.getIdentifier());
    }
}
