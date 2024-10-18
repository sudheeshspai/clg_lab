import java.util.*;

class SharedNumber {
    private int number;
    private boolean isEven;

    public synchronized void setNumber(int number) {
        this.number = number;
        this.isEven = (number % 2 == 0);
        notifyAll();
    }

    public synchronized int getNumber() {
        return number;
    }

    public synchronized boolean isEven() {
        return isEven;
    }

    public synchronized void waitForNumber() {
        try {
            wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class RandomNumberGenerator extends Thread {
    private SharedNumber sharedNumber;
    private Random random = new Random();

    public RandomNumberGenerator(SharedNumber sharedNumber) {
        this.sharedNumber = sharedNumber;
    }

    @Override
    public void run() {
        while (true) {
            int number = random.nextInt(100) + 2; // Generate a random number between 2 and 101
            System.out.println("Generated number: " + number);
            sharedNumber.setNumber(number);
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class EvenNumberPrinter extends Thread {
    private SharedNumber sharedNumber;

    public EvenNumberPrinter(SharedNumber sharedNumber) {
        this.sharedNumber = sharedNumber;
    }

    @Override
    public void run() {
        while (true) {
            sharedNumber.waitForNumber();
            if (sharedNumber.isEven()) {
                int number = sharedNumber.getNumber();
                System.out.print("Even numbers between 1 and " + number + ": ");
                for (int i = 2; i <= number; i += 2) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
        }
    }
}

class OddNumberPrinter extends Thread {
    private SharedNumber sharedNumber;

    public OddNumberPrinter(SharedNumber sharedNumber) {
        this.sharedNumber = sharedNumber;
    }

    @Override
    public void run() {
        while (true) {
            sharedNumber.waitForNumber();
            if (!sharedNumber.isEven()) {
                int number = sharedNumber.getNumber();
                System.out.print("Odd numbers between 1 and " + number + ": ");
                for (int i = 1; i <= number; i += 2) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
        }
    }
}

public class postiveMulti {
    public static void main(String[] args) {
        SharedNumber sharedNumber = new SharedNumber();
        new RandomNumberGenerator(sharedNumber).start();
        new EvenNumberPrinter(sharedNumber).start();
        new OddNumberPrinter(sharedNumber).start();
    }
}