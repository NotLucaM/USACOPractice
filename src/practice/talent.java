
/*
 problem http://www.usaco.org/index.php?page=viewproblem2&cpid=839
 */

import java.io.*;
import java.util.StringTokenizer;

@SuppressWarnings("ALL")
public class talent {

    private static class Cow {
        private int weight;
        private int talent;


        public Cow(int weight, int talent) {
            this.weight = weight;
            this.talent = talent;
        }
    }

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

    private static boolean check() {
        return false;
    }

    private static int readInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}
