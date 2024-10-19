class Account {
    private String accountNo;
    private double balance;

    public Account(String accountNo, double initialBalance) {
        this.accountNo = accountNo;
        this.balance = initialBalance;
    }

    public synchronized void displayBalance() {
        System.out.println("Account No: " + accountNo + ", Balance: " + balance);
    }

    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
            displayBalance();
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    public synchronized void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: " + amount);
            displayBalance();
        } else {
            System.out.println("Invalid or insufficient funds for withdrawal");
        }
    }
}

class DepositThread extends Thread {
    private Account account;
    private double amount;

    public DepositThread(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        account.deposit(amount);
    }
}

class WithdrawThread extends Thread {
    private Account account;
    private double amount;

    public WithdrawThread(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        account.withdraw(amount);
    }
}

public class multiAmount {
    public static void main(String[] args) {
        Account account = new Account("123456789", 1000.0);

        DepositThread depositThread = new DepositThread(account, 500.0);
        WithdrawThread withdrawThread = new WithdrawThread(account, 200.0);

        depositThread.start();
        withdrawThread.start();

        try {
            depositThread.join();
            withdrawThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}