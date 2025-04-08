public class Main {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new BlockingQueue<>(3);

        Runnable producer = () -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    queue.enqueue(i);
                    System.out.println("Производитель добавил: " + i);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable consumer = () -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    int item = queue.dequeue();
                    System.out.println("Потребитель получил: " + item);
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
