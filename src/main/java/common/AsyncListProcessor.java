package common;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.function.Function;

public class AsyncListProcessor {

    private AsyncListProcessor() {
    }

    public static <T> void runBatchesAsync(
            List<List<T>> batches,
            ExecutorService executor,
            Consumer<List<T>> handler,
            Consumer<List<T>> onSuccess,
            Consumer<Throwable> onError,
            Runnable onFinally
    ) {
        for (List<T> batch : batches) {
            executor.submit(() -> {
                try {
                    handler.accept(batch);
                    onSuccess.accept(batch);
                } catch (Throwable ex) {
                    onError.accept(ex);
                } finally {
                    onFinally.run();
                }
            });
        }
    }
}
