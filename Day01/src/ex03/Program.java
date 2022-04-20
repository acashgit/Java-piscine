package ex03;

import java.util.UUID;

public class Program {
    public static void main(String[] args) throws TransactionNotFoundException {
        User user1 = new User("Son", 1000);
        User user2 = new User("Jin", 1000);
        User user3 = new User("Jin", 1000);

        Transaction send1 = new Transaction(user1, user2, "debit", 500);
        Transaction send2 = new Transaction(user1, user3, "credit", -500);
        Transaction send3 = new Transaction(user3, user2, "debit", 100);

        TransactionsLinkedList list = new TransactionsLinkedList();
        Transaction arr[];

        list.addTransaction(send1);
        list.addTransaction(send2);
        list.addTransaction(send3);

        Node tmp = list.getHead();

        while (tmp != null){
            System.out.println(tmp.data.getIdentifier() + "   " + tmp.data.getAmount());
            tmp = tmp.next;
        }

        list.delTransaction(send1.getIdentifier());

        System.out.println("\n");
        Node ll = list.getHead();
        while (ll != null){
            System.out.println(ll.data.getIdentifier());
            ll = ll.next;
        }

        System.out.println("\n");

        list.delTransaction(send3.getIdentifier());
        arr = list.transToArr();
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr[i].getIdentifier() + " " + arr[i].getAmount());
        }
    }
}
