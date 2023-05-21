package bankingapplication1;

public class Account {

    private int number;
    private String name;
    private double balance;

    public Account(int number, String name, double balance) {
        this.number = number;
        this.name = name;
        this.balance = balance;
    }

    public void deposit(double amount) {
        this.balance = this.balance + amount;
//        this.balance += amount;
    }

    public void withdraw(double amount) {
        this.balance = this.balance - amount;
//        this.balance -= amount;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

}
