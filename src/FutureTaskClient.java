public class FutureTaskClient {
    public static void main(String[] args) throws InterruptedException {
        FutureTask<String> task = new FutureTask<>(new DummyTask());
        Thread thread = new Thread(task);
        thread.start();
        while (!task.isDone()) {
            // simulate doing other work
            System.out.println("Doing other work");
            Thread.sleep(1_000);
        }
        System.out.println("Task returned the value: " + task.get());

        FutureTask<String> cancelTask = new FutureTask<>(new DummyTask());
        Thread cancelThread = new Thread(cancelTask);
        cancelThread.start();

        Thread.sleep(1_000);
        System.out.println("Cancelling task");
        cancelTask.cancel();
        while (!cancelTask.isCancelled()) {
            System.out.println("Waiting for cancel");
            Thread.sleep(100);
        }
    }
}
