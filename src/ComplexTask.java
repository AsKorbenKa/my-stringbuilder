import java.util.concurrent.CyclicBarrier;

public class ComplexTask implements Runnable {
    private final int taskId;
    private final CyclicBarrier barrier;

    public ComplexTask(int taskId, CyclicBarrier barrier) {
        this.taskId = taskId;
        this.barrier = barrier;
    }

    public void execute() {
        System.out.println("Задача " + taskId + " выполняет свою часть...");
        try {
            Thread.sleep(1000 + (int) (Math.random() * 1000)); // имитация работы
            System.out.println("Задача " + taskId + " завершила выполнение своей части.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        execute();
        try {
            System.out.println("Задача " + taskId + " ждет остальных на барьере.");
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}