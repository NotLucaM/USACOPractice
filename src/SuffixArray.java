import java.util.Arrays;

public class SuffixArray {

    private Suffix[] mSuffixes;

    public SuffixArray(String text) {
        int n = text.length();
        mSuffixes = new Suffix[n];
        for (int i = 0; i < n; i++) {
            mSuffixes[i] = new Suffix(text, i);
        }
        Arrays.sort(mSuffixes);
    }

    private static class Suffix implements Comparable<Suffix> {

        private String mText;
        private int mIndex;

        private Suffix(String text, int index) {
            mText = text;
            mIndex = index;
        }

        private int length() {
            return mText.length() - mIndex;
        }

        private char charAt(int i) {
            return mText.charAt(mIndex + i);
        }

        public int compareTo(Suffix o) {
            if (this == o) return 0;
            int n = Math.min(this.length(), o.length());
            for (int i = 0; i < n; i++) {
                if (this.charAt(i) < o.charAt(i)) return -1;
                if (this.charAt(i) > o.charAt(i)) return +1;
            }
            return this.length() - o.length();
        }

        public String toString() {
            return mText.substring(mIndex);
        }
    }

    public int length() {
        return mSuffixes.length;
    }


    public int index(int i) {
        if (i < 0 || i >= mSuffixes.length) throw new IllegalArgumentException();
        return mSuffixes[i].mIndex;
    }
}
