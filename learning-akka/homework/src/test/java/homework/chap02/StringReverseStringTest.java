package homework.chap02;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringReverseStringTest {

    private StringReverseService service = new StringReverseService();

    @Test
    public void testReverseString() throws Exception {
        String ret = service.reverseString("hello").get();
        assertEquals("olleh", ret);
    }

    @Test(expected = Exception.class)
    public void testUnknownType() throws Exception {
        service.reverseString(new Object()).get();
    }

}
