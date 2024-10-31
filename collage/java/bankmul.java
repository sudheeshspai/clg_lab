class Account {
    private int accountNo;
    private int balance;

    public Account(int accountNo, int balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public  synchronized void deposit(int amount) {
        System.out.println("Depositing: " + amount);
        balance += amount;
        System.out.println("Balance after deposit: " + balance);
    }

    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            System.out.println("Withdrawing: " + amount);
            balance -= amount;
            System.out.println("Balance  after withdrawal: " + balance);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public synchronized void displayBalance() {
        System.out.println("Current balance: " + balance);
    }
}

class DepositThread extends Thread {
    private Account account;
    private int amount;

    public DepositThread(Account account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    public void run() {
        account.deposit(amount);

    }
}

class WithdrawThread extends Thread {
    private Account account;
    private int amount;

    public WithdrawThread(Account account, int amount) {
        this.account = account;
        this.amount = amount;

    }

    public void run() {
        account.withdraw(amount);

    }
}

public class bankmul {
    public static void main(String[] args) {
        Account account = new Account(1234, 1000);

        DepositThread depositThread = new DepositThread(account, 500);
        WithdrawThread withdrawThread = new WithdrawThread(account, 200);

        depositThread.start();
       
        withdrawThread.start();

    }
}