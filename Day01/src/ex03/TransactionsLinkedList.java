package ex03;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList{
    private Integer size;
    private Node head;
    private Node tail;

    public TransactionsLinkedList(){
        size = 0;
        this.head = null;
        this.tail = null;
    }
    public Node getHead(){
        return (head);
    }
    @Override
    public void addTransaction(Transaction trans) {
        if (size == 0){
            this.head = new Node(null, null, trans);
            this.tail = head;
        }
        else {
            this.tail.next = new Node(this.tail, null, trans);
            this.tail = this.tail.next;
        }
        size++;
    }

    @Override
    public void delTransaction(UUID id) throws TransactionNotFoundException {
        Node tmp = head;
        Node prevTmp, nextTmp;

        if (head != null && this.head.data.getIdentifier() == id){
            head = head.next;
            size--;
            return;
        }
        if (head == null){
            throw new TransactionNotFoundException();
        }
        for (int i = 0; i < size; i++){
            if (tmp.data.getIdentifier() == id)
                break;
            tmp = tmp.next;
        }
        if (tmp == null)
            throw  new TransactionNotFoundException();
        prevTmp = tmp.prev;
        nextTmp = tmp .next;
        if (prevTmp != null)
            prevTmp.next = tmp.next;
        if (nextTmp != null)
            nextTmp.prev = tmp.prev;
        size--;
    }

    @Override
    public Transaction[] transToArr() {
        Transaction[] array = new Transaction[size];
        Node tmp = this.head;
        for (int i = 0; i < this.size; i++){
            array[i] = tmp.data;
            tmp = tmp.next;
        }
        return array;
    }
}

class Node{
    Transaction data;
    Node next;
    Node prev;
    public Node(Node prev, Node next, Transaction data){
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
}
