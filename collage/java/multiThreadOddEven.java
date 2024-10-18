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
            int number = random.nextInt(100);
            System.out.println("Generated number: " + number);
            sharedNumber.setNumber(number);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class SquareCalculator extends Thread {
    private SharedNumber sharedNumber;

    public SquareCalculator(SharedNumber sharedNumber) {
        this.sharedNumber = sharedNumber;
    }

    @Override
    public void run() {
        while (true) {
            sharedNumber.waitForNumber();
            if (sharedNumber.isEven()) {
                int number = sharedNumber.getNumber();
                System.out.println("Square of " + number + " is " + (number * number));
            }
        }
    }
}

class CubeCalculator extends Thread {
    private SharedNumber sharedNumber;

    public CubeCalculator(SharedNumber sharedNumber) {
        this.sharedNumber = sharedNumber;
    }

    @Override
    public void run() {
        while (true) {
            sharedNumber.waitForNumber();
            if (!sharedNumber.isEven()) {
                int number = sharedNumber.getNumber();
                System.out.println("Cube of " + number + " is " + (number * number * number));
            }
        }
    }
}

public class MultiThreadOddEven {
    public static void main(String[] args) {
        SharedNumber sharedNumber = new SharedNumber();
        new RandomNumberGenerator(sharedNumber).start();
        new SquareCalculator(sharedNumber).start();
        new CubeCalculator(sharedNumber).start();
    }
}