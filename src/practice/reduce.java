import java.io.*;
import java.util.*;

public class reduce {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("reduce.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));
        StringTokenizer st;

        int numberOfCows = Integer.parseInt(in.readLine());
        Point[] cows = new Point[numberOfCows];
        for (int i = 0; i < numberOfCows; i++) {
            st = new StringTokenizer(in.readLine());

            cows[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Point[] cowsX = sortX(cows);
        Point[] cowsY = sortY(cows);

        int minArea = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {
                        minArea = Math.min(minArea, checkRectangle(new Point[]{cowsX[i], cowsX[cowsX.length - j - 1],
                                                                   cowsY[k], cowsY[cowsY.length - l - 1]}, cows));
                    }
                }
            }
        }

        out.println(minArea);
        out.close();
    }

    private static Point[] sortX(Point[] arr) {
        Point[] ret = arr.clone();
        Arrays.sort(ret, Comparator.comparingInt(p -> p.x));
        return ret;
    }

    private static Point[] sortY(Point[] arr) {
        Point[] ret = arr.clone();
        Arrays.sort(ret, Comparator.comparingInt(p -> p.y));
        return ret;
    }

    // pts stored lower x, higher x, lower y, higher y, rets infinity if not possible
    private static int checkRectangle(Point[] rectPts, Point[] cows) {
        int area = (rectPts[1].x - rectPts[0].x) * ( rectPts[3].y - rectPts[2].y);

        int missingCows = 0;
        for (Point cow : cows) {
            if ((cow.x < rectPts[0].x || cow.x > rectPts[1].x) || (cow.y < rectPts[2].y || cow.y > rectPts[3].y)) {
                missingCows++;
            }
        }

        return missingCows <= 3 ? area : Integer.MAX_VALUE;
    }
}
