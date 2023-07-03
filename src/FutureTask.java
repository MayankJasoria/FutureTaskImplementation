import java.util.Objects;

public class FutureTask<T> implements Runnable, Future<T> {

    Callable<T> callable;
    volatile private T result;
    private boolean isCancelled;
    private Thread runner;

    public FutureTask(Callable<T> callable) {
        this.callable = callable;
        result = null;
        isCancelled = false;
        runner = null;
    }

    @Override
    public void run() {
        runner = Thread.currentThread();
        T result = callable.call();
        synchronized (this) {
            this.result = result;
            notifyAll();
        }
    }

    @Override
    public synchronized boolean isDone() {
        return !Objects.isNull(result);
    }

    @Override
    public synchronized T get() throws InterruptedException {
        while (Objects.isNull(result)) {
            wait();
        }
        return result;
    }

    @Override
    public synchronized boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public synchronized boolean cancel() {
        try {
            if (runner.isAlive()) {
                runner.interrupt();
            }
            isCancelled = true;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
