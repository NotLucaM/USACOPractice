import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

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

    public static ArrayList<Integer> KMPSearch(String text, String substr) {
        int M = substr.length();
        int N = text.length();

        ArrayList<Integer> matches = new ArrayList<>();
        int[] lps = new int[M];
        int j = 0;

        computeLPSArray(substr, M, lps);

        int i = 0;
        while (i < N) {
            if (substr.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                matches.add(i - j);
                j = lps[j - 1];
            } else if (i < N && substr.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i = i + 1;
                }
            }
        }

        return matches;
    }

    private static void computeLPSArray(String pat, int M, int[] lps) {
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {

                if (len != 0) {
                    len = lps[len - 1];


                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    @Test
    public void binarySearch() {
        int[] arr = {2, 3, 4, 10, 40};
        int[] empty = {};
        int[] oneVal = {2};

        assertEquals(3, binarySearch(arr, 10));
        assertEquals(-1, binarySearch(arr, 24));
        assertEquals(-1, binarySearch(arr, -1));
        assertEquals(-1, binarySearch(arr, 54));
        assertEquals(0, binarySearch(arr, 2));
        assertEquals(-1, binarySearch(empty, 23));
        assertEquals(0, binarySearch(arr, 2));
    }

    @Test
    public void testKMPSearch() {
        String text = "ABABDABACDABABCABAB";
        String longPattern = "ABABCABAB";
        String shortPattern = "AB";

        assertEquals(new Integer(10), KMPSearch(text, longPattern).get(0));
        assertEquals(new Integer(2), KMPSearch(text, shortPattern).get(1));
    }
}
