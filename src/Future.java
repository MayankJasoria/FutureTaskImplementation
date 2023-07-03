public interface Future<T> {
    boolean isDone();
    boolean isCancelled();
    T get() throws InterruptedException;
    boolean cancel();
}
