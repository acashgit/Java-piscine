package ex02;

public class Program {
    public static void main(String[] args) throws Exception {
        User user1 = new User("Biba", 1000);
        User user2 = new User("Boba", 1000);
        User user3 = new User("Buba", 1000);
        User user4 = new User("Pipa", 1000);
        User user5 = new User("Pupa", 1000);
        User user6 = new User("Tom", 1000);
        User user7 = new User("Jerry", 1000);
        User user8 = new User("Hank", 1000);
        User user9 = new User("Son", 1000);
        User user10 = new User("Norter", 1000);
        User user11 = new User("Kramer", 1000);

        UsersArrayList array = new UsersArrayList();

        System.out.println(array.toString());
        System.out.println(array.getCount());

        array.addUser(user1);
        array.addUser(user2);
        array.addUser(user3);
        array.addUser(user4);
        array.addUser(user5);
        array.addUser(user6);
        array.addUser(user7);
        array.addUser(user8);
        array.addUser(user9);
        array.addUser(user10);
        array.addUser(user11);

        System.out.println(array.toString());
        System.out.println(array.getCount());
        System.out.println(array.retrieveById(11).getName());
        System.out.println(array.retrieveByIndex(10).getName());
    }
}
