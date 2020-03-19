import java.util.Arrays;

public class SuffixArray {

    private Suffix[] suffixes;

    public SuffixArray(String text) {
        int n = text.length();
        suffixes = new Suffix[n];
        for (int i = 0; i < n; i++) {
            suffixes[i] = new Suffix(text, i);
        }
        Arrays.sort(suffixes);
    }

    public boolean contains(String substr) {
        int ip = Arrays.binarySearch(suffixes, new Suffix(substr));
        if (ip >= 0) {
            return true;
        }

        ip = -ip - 1;

        return ip < suffixes.length && suffixes[ip].toString().startsWith(substr);
    }

    // TODO: implement longest common substring

    private static class Suffix implements Comparable<Suffix> {
        private String text;
        private int index;

        private Suffix(String text) {
            this(text, 0);
        }

        private Suffix(String text, int index) {
            this.text = text;
            this.index = index;
        }

        private int length() {
            return text.length() - index;
        }

        private char charAt(int i) {
            return text.charAt(index + i);
        }

        public String toString() {
            return text.substring(index);
        }

        @Override
        public int compareTo(Suffix that) {
            if (this == that) {
                return 0;
            }
            int n = Math.min(this.length(), that.length());
            for (int i = 0; i < n; i++) {
                if (this.charAt(i) < that.charAt(i)) {
                    return -1;
                }
                if (this.charAt(i) > that.charAt(i)) {
                    return 1;
                }
            }
            return this.length() - that.length();
        }
    }
}
