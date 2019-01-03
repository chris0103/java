package homework.chap02;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Map<String, String> results = new HashMap<String, String>() {
            {
                put("Apple", "elppA");
                put("Banana", "ananaB");
                put("Citrus", "surtiC");
            }
        };
        List<CompletableFuture<String>> listOfFuture = strs.stream().map(service::reverseString).collect(Collectors.toList());
        CompletableFuture<List<String>> futureOfList = sequence(listOfFuture);
        futureOfList.thenAccept(future -> {
            future.stream().forEach(str -> {
                System.out.println(str + " => " + results.get(str));
                assertEquals(results.get(str), str);
            });
        })
        /*
         * .handle((x, t) -> {
         * if (t != null) {
         * t.printStackTrace();
         * }
         * return t;
         * })
         */
        ;
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
