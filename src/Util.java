import org.junit.Test;
import static org.junit.Assert.*;

public class Util {

    public static <T> T lastIndex(T[] array) {
        return array[array.length - 1];
    }

    public static int binarySearch(int[] array, int number) {
        int l = 0, r = array.length;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (m >= array.length || m < 0) {
                return -1;
            }

            if (array[m] == number) {
                return m;
            } else if (array[m] < number) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return -1;
    }

    @Test
    public void binarySearch() {
        int[] arr = { 2, 3, 4, 10, 40 };
        int[] empty = { };
        int[] oneVal = { 2 };

        assertEquals(3, binarySearch(arr, 10));
        assertEquals(-1, binarySearch(arr, 24));
        assertEquals(-1, binarySearch(arr, -1));
        assertEquals(-1, binarySearch(arr, 54));
        assertEquals(0, binarySearch(arr, 2));
        assertEquals(-1, binarySearch(empty, 23));
        assertEquals(0, binarySearch(arr, 2));
    }
}
