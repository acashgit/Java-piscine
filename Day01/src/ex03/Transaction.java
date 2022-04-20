package ex03;

import java.util.UUID;

public class Transaction {
    private UUID identifier;
    private User recipient;
    private User sender;
    private Integer amount;

    public Transaction.Category getCategory() {
        return category;
    }

    public void setCategory(Transaction.Category category) {
        this.category = category;
    }

    public Transaction.Status getStatus() {
        return status;
    }

    public void setStatus(Transaction.Status status) {
        this.status = status;
    }

    private enum Category{
        DEBIT,
        CREDIT
    }

    private Transaction.Category category;

    private enum Status{
        FAIL,
        SUCCESS
    }

    private Transaction.Status status;

    public Transaction(User sender, User recipient, String category, Integer amount){
        this.recipient = recipient;
        this.sender = sender;
        this.identifier = new UUID(45678, 123);
        if ((amount < 0 && category.equals("credit")) || (amount >= 0 && category.equals("debit"))) {
            if (amount < 0)
                setCategory(Transaction.Category.CREDIT);
            else
                setCategory(Transaction.Category.DEBIT);
            this.amount = amount;
            if (sender.getBalance() < amount)
                setStatus(Transaction.Status.FAIL);
            else {
                sender.setBalance(sender.getBalance() - amount);
                recipient.setBalance(recipient.getBalance() + amount);
                setStatus(Transaction.Status.SUCCESS);
            }
        }
        else
            setStatus(Transaction.Status.FAIL);
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction " + "identifier = " + identifier + ", recipient = " + recipient.toString() +
                ", sender = " + sender.toString() + ", amount = " + amount + ", category = " + category + ", status = " + status;
    }
}