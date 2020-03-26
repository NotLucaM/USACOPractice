package practice;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class art2 {

    private static class Range {
        int start, end, color;

        public Range(int start, int color) {
            this.start = start;
            this.end = -1;
            this.color = color;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("art2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("art2.out")));

        int paintingSize = parseInt(in.readLine());
        Range[] ranges = new Range[paintingSize];
        for (int i = 0; i < paintingSize; i++) {
            int color = parseInt(in.readLine());
            if (color != 0) {
                if (ranges[color - 1] == null) {
                    ranges[color - 1] = new Range(i, color);
                } else {
                    ranges[color - 1].end = i;
                }
            }
        }

        Arrays.sort(ranges, (range, t1) -> {
            if (range == null) {
                return 1;
            } else if (t1 == null) {
                return -1;
            }
            return range.start - t1.start;
        });

        Stack<Integer> paint = new Stack<>();
//        int maxSize = 0;
//        for (int i = 0; i < ; i++) {
//
//        }
    }
}
