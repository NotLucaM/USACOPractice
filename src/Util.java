public class Util {

    public static <T> T lastIndex(T[] array) {
        return array[array.length - 1];
    }

    public static int binarySearch(int[] array, int number) {
        int l = array[0], r = array[array.length - 1];

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (array[m] == number) {
                return m;
            }
            if (array[m] < number) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return -1;
    }
}
