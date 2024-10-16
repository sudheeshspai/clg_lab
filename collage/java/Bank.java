import java.util.*;
interface Account {
    void deposit(double amount);
    void withdraw(double amount);
    void view_balance();
}

class SavingsAccount implements Account {
    private double balance;

    public SavingsAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + " in SavingsAccount");
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrew: " + amount + " from SavingsAccount");
        } else {
            System.out.println("Insufficient balance in SavingsAccount!");
        }
    }

    @Override
    public void view_balance() {
        System.out.println("SavingsAccount balance: " + balance);
    }
}

class CurrentAccount implements Account {
    private double balance;
    private double overdraftLimit = 500;

    public CurrentAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + " in CurrentAccount");
    }

    @Override
    public void withdraw(double amount) {
        if (balance + overdraftLimit >= amount) {
            balance -= amount;
            System.out.println("Withdrew: " + amount + " from CurrentAccount");
        } else {
            System.out.println("Insufficient balance in CurrentAccount!");
        }
    }

    @Override
    public void view_balance() {
        System.out.println("CurrentAccount balance: " + balance);
    }
}

public class Bank {
    public static void main(String[] args) {
        SavingsAccount savings = new SavingsAccount(1000);
        CurrentAccount current = new CurrentAccount(2000);

        savings.deposit(500);
        savings.withdraw(300);
        savings.view_balance();

        current.deposit(1000);
        current.withdraw(2500);
        current.view_balance();
    }
}
