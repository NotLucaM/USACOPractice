/*
 problem http://www.usaco.org/index.php?page=viewproblem2&cpid=839
 */

import java.io.*;
import java.util.StringTokenizer;

@SuppressWarnings("ALL")
public class talent {

    private static Cow[] cows;
    private static long[] dp;
    private static int numberOfCows;
    private static int minWeight;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("talent.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("talent.out")));
        StringTokenizer st;

        st = new StringTokenizer(in.readLine());
        numberOfCows = readInt(st);
        minWeight = readInt(st);
        dp = new long[250000001];
        cows = new Cow[minWeight];
        for (int i = 0; i < numberOfCows; i++) {
            st = new StringTokenizer(in.readLine());
            cows[i] = new Cow(readInt(st), readInt(st));
        }

        int l = 0, r = 250000001;
        while (l <= r) {
            int m = l + (r - l) / 2;

            if (check(l)) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        System.out.println(l);
    }

    static boolean check(int weight) {
        for (int i = 0; i < minWeight; i++) {
            dp[i] = Long.MIN_VALUE;
        }
        dp[0] = 0;

        for (int j = 0; j < numberOfCows; j++) {
            long value = 1000 * cows[j].talent - weight * cows[j].weight;
            int inc = cows[j].weight;
            for (int k = weight; k >= 0; k--) {
                int k1 = Math.min(weight, k + inc);
                if (dp[k] != Long.MIN_VALUE) {
                    if (dp[k1] < dp[k] + value) {
                        dp[k1] = dp[k] + value;
                    }
                }
            }
        }

        return dp[weight] >= 0;
    }

    private static int readInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }

    private static class Cow {
        private int weight;
        private int talent;


        public Cow(int weight, int talent) {
            this.weight = weight;
            this.talent = talent;
        }
    }
}
