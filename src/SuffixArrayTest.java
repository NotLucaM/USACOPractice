import org.junit.Test;

import static org.junit.Assert.*;

public class SuffixArrayTest {
    @Test
    public void testContains() {
        SuffixArray arr = new SuffixArray("bananas");

        assertTrue(arr.contains("bananas"));
        assertFalse(arr.contains("hello"));
        assertTrue(arr.contains("ban"));
        assertTrue(arr.contains("ana"));
        assertTrue(arr.contains("nana"));
    }
}
