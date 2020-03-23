/*
 problem http://www.usaco.org/index.php?page=viewproblem2&cpid=839
 */

import java.io.*;
import java.util.StringTokenizer;

@SuppressWarnings("ALL")
public class talent {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("milkorder.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));
        StringTokenizer st;

        st = new StringTokenizer(in.readLine());
        int numberOfCows = readInt(st);
        int minWeight = readInt(st);

        Cow[] cows = new Cow[numberOfCows];
        for (int i = 0; i < numberOfCows; i++) {
            st = new StringTokenizer(in.readLine());
            cows[i] = new Cow(readInt(st), readInt(st));
        }

        int l = 0, r = 1000000;
        while (l <= r) {
            int m = l + (r - l) / 2;

            if (check()) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
    }

    static int knapSack(int maxWeight, Cow[] cows, int n) {
        int i, w;
        int dp[][] = new int[n + 1][maxWeight + 1];

        for (i = 0; i <= n; i++) {
            for (w = 0; w <= maxWeight; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (cows[i - 1].weight <= w) {
                    dp[i][w] = Math.max(cows[i - 1].talent + dp[i - 1][w - cows[i - 1].weight], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][maxWeight];
    }

    private static boolean check() {
        return false;
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
