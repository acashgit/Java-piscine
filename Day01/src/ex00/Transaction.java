package ex00;

import java.util.UUID;

public class Transaction {
    private UUID identifier;
    private User recipient;
    private User sender;
    private Integer amount;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private enum Category{
        DEBIT,
        CREDIT
    }

    private Category category;

    private enum Status{
        FAIL,
        SUCCESS
    }

    private Status status;

    public Transaction(User sender, User recipient, String category, Integer amount){
        this.recipient = recipient;
        this.sender = sender;
        this.identifier = new UUID(45678, 123);
        if ((amount < 0 && category.equals("credit")) || (amount >= 0 && category.equals("debit"))) {
            if (amount < 0)
                setCategory(Category.CREDIT);
            else
                setCategory(Category.DEBIT);
            this.amount = amount;
            if (sender.getBalance() < amount)
                setStatus(Status.FAIL);
            else {
                sender.setBalance(sender.getBalance() - amount);
                recipient.setBalance(recipient.getBalance() + amount);
                setStatus(Status.SUCCESS);
            }
        }
        else
            setStatus(Status.FAIL);
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
