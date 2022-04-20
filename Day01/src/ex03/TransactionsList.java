package ex03;

import java.util.UUID;

public interface TransactionsList {
    public void addTransaction(Transaction trans);
    public void delTransaction(UUID id) throws TransactionNotFoundException;
    public Transaction[] transToArr();
}
