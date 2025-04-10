import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ComplexTaskExecutor {
    public void executeTasks(int numberOfTasks) {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfTasks);

        Runnable barrierAction = () -> System.out.println("Все задачи достигли барьера. Объединение результатов...");

        CyclicBarrier barrier = new CyclicBarrier(numberOfTasks, barrierAction);

        for (int i = 1; i <= numberOfTasks; i++) {
            executor.submit(new ComplexTask(i, barrier));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Все задачи завершены.");
    }
}
