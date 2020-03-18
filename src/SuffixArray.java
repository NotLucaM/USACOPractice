import java.util.Arrays;

public class SuffixArray {

    int[] array;

    public SuffixArray(String string) {
        int length = string.length();
        Suffix[] suffixes = new Suffix[length];

        for (int i = 0; i < length; i++) {
            suffixes[i] = new Suffix(i, string.charAt(i) - '$', 0);
        }
        for (int i = 0; i < length; i++)
            suffixes[i].next = (i + 1 < length ? suffixes[i + 1].rank : -1);

        Arrays.sort(suffixes);

        int[] ind = new int[length];


        for (int suffixLength = 4; suffixLength < 2 * length; suffixLength <<= 1) {

            int rank = 0, prev = suffixes[0].rank;
            suffixes[0].rank = rank;
            ind[suffixes[0].index] = 0;
            for (int i = 1; i < length; i++) {
                if (suffixes[i].rank == prev &&
                        suffixes[i].next == suffixes[i - 1].next) {
                    prev = suffixes[i].rank;
                    suffixes[i].rank = rank;
                } else {
                    prev = suffixes[i].rank;
                    suffixes[i].rank = ++rank;
                }
                ind[suffixes[i].index] = i;
            }

            for (int i = 0; i < length; i++) {
                int nextP = suffixes[i].index + suffixLength / 2;
                suffixes[i].next = nextP < length ?
                        suffixes[ind[nextP]].rank : -1;
            }

            Arrays.sort(suffixes);
        }

        int[] suf = new int[length];

        for (int i = 0; i < length; i++)
            suf[i] = suffixes[i].index;

        array = suf;
    }

    public static class Suffix implements Comparable<Suffix> {
        int index;
        int rank;
        int next;

        public Suffix(int ind, int r, int nr) {
            index = ind;
            rank = r;
            next = nr;
        }

        public int compareTo(Suffix s) {
            if (rank != s.rank) return Integer.compare(rank, s.rank);
            return Integer.compare(next, s.next);
        }
    }
}
