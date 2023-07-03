public class DummyTask implements Callable<String> {
    @Override
    public String call() {
        System.out.println("Starting task");
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            System.out.println("Terminating as thread was interrupted");
        }
        return "Done";
    }
}
