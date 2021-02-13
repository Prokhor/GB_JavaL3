package gb.l4hw;

public class Main {

    private static final char A = 'A';
    private static final char B = 'B';
    private static final char C = 'C';

    private static volatile int currentThread = 1;
    private static final Object monitor = new Object();

    public static void main(String[] args) {
        doAction(5);
    }

    private static void doAction(int cnt) {
        new Thread(() -> {
            try {
                for (int i = 0; i < cnt; i++) {
                    synchronized (monitor){
                        while (currentThread != 1){
                            monitor.wait();
                        }
                        printChar(A);
                        currentThread = 2;
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < cnt; i++) {
                    synchronized (monitor){
                        while (currentThread != 2){
                            monitor.wait();
                        }
                        printChar(B);
                        currentThread = 3;
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < cnt; i++) {
                    synchronized (monitor){
                        while (currentThread != 3){
                            monitor.wait();
                        }
                        printChar(C);
                        Thread.sleep(400);
                        currentThread = 1;
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void printChar(char ch){
        System.out.print(ch);
    }
}
