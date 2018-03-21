import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CompletableFutureTest {

  @Test
  public void complete() throws InterruptedException, ExecutionException {
    Future<String> completableFuture = Main.calculateAsync();
    assert !completableFuture.isDone();
    //Call get method is blocking until completable is completed
    assert "Hello".equals(completableFuture.get());
    assert completableFuture.isDone();
  }

  private Future<String> calculateAsync() throws InterruptedException {
    CompletableFuture<String> completableFuture
        = new CompletableFuture<>();

    Executors.newCachedThreadPool().submit(() -> {
      Thread.sleep(500);
      completableFuture.complete("Hello");
      return null;
    });

    return completableFuture;
  }

  @Test
  public void getAFuture() throws ExecutionException, InterruptedException {
    Future<String> completableFuture = calculateAsync();
    String result = completableFuture.get();
    assertEquals("Hello", result);
  }

  @Test
  public void completeDirect() throws ExecutionException, InterruptedException {
    Future<String> completableFuture = CompletableFuture.completedFuture("Hello");
    String result = completableFuture.get();
    assertEquals("Hello", result);
  }

  @Test
  public void withASupplier() throws ExecutionException, InterruptedException {
    CompletableFuture<String> future
        = CompletableFuture.supplyAsync(() -> "Hello");

    assertEquals("Hello", future.get());
  }

  @Test
  public void withThenApply() throws ExecutionException, InterruptedException {
    CompletableFuture<String> completableFuture
        = CompletableFuture.supplyAsync(() -> "Hello");

    CompletableFuture<String> future = completableFuture
        .thenApply(s -> s + " World");

    assertEquals("Hello World", future.get());
  }

  @Test
  public void combining() throws ExecutionException, InterruptedException {
    CompletableFuture<String> completableFuture
        = CompletableFuture.supplyAsync(() -> "Hello")
                           .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));

    assertEquals("Hello World", completableFuture.get());
  }

  @Test
  public void inParallel() throws ExecutionException, InterruptedException {
    CompletableFuture<String> future1
        = CompletableFuture.supplyAsync(() -> "Hello");
    CompletableFuture<String> future2
        = CompletableFuture.supplyAsync(() -> "Beautiful");
    CompletableFuture<String> future3
        = CompletableFuture.supplyAsync(() -> "World");

    CompletableFuture<Void> combinedFuture
        = CompletableFuture.allOf(future1, future2, future3);

    combinedFuture.get();

    assertTrue(future1.isDone());
    assertTrue(future2.isDone());
    assertTrue(future3.isDone());

    String combined = Stream.of(future1, future2, future3)
                            .map(CompletableFuture::join)
                            .collect(Collectors.joining(" "));

    assertEquals("Hello Beautiful World", combined);

  }

  @Test(expected = ExecutionException.class)
  public void handleError() throws ExecutionException, InterruptedException {
    CompletableFuture<String> completableFuture = new CompletableFuture<>();

    completableFuture.completeExceptionally(
        new RuntimeException("Calculation failed!"));

    completableFuture.get();
  }

  @Test
  public void async() throws ExecutionException, InterruptedException {
    CompletableFuture<String> completableFuture
        = CompletableFuture.supplyAsync(() -> "Hello");

    CompletableFuture<String> future = completableFuture
        .thenApplyAsync(s -> s + " World");

    assertEquals("Hello World", future.get());
  }

}
