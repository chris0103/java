package homework.chap02;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.junit.Test;

public class StringReverseServiceTest {

    private static String errorMessage = "unknown message";

    private StringReverseService service = new StringReverseService();

    @Test
    public void testReverseString() throws Exception {
        String ret = service.reverseString("hello").get();
        assertEquals("olleh", ret);
    }

    @Test
    public void testStringSequence() throws Exception {
        List<String> strs = Arrays.asList("Apple", "Banana", "Citrus");
        List<String> results = Arrays.asList("elppA", "ananaB", "surtiC");
        List<CompletableFuture<String>> listOfFuture = strs.stream().map(service::reverseString).collect(Collectors.toList());
        CompletableFuture<List<String>> futureOfList = sequence(listOfFuture);
        assertEquals(results, futureOfList.get());
    }

    @Test
    public void testUnknownType() throws Exception {
        String ret = service.reverseString(new Object()).get();
        assertEquals(errorMessage, ret);
    }

    private <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
        CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        return allDoneFuture.thenApply(v -> futures.stream().map(future -> future.join()).collect(Collectors.<T> toList()));
    }

}
