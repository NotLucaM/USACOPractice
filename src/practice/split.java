import java.io.*;
import java.util.*;

public class split {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Rect {
        Point lx, ly, hx, hy;
        Set<Point> points;

        public Rect(Point p1, Point p2) {
            if (p1 == p2) {
                points = new HashSet<>();
                points.add(p1);
                this.lx = p1; this.ly = p1; this.hx = p1; this.hy = p1;
                return;
            }

            if (p1.x < p2.x) {
                this.lx = p1;
                this.hx = p2;
            } else {
                this.lx = p2;
                this.hx = p1;
            }

            if (p1.y < p2.y) {
                this.ly = p1;
                this.hy = p2;
            } else {
                this.ly = p2;
                this.hy = p1;
            }
        }

        public Rect(Point p1, Point p2, Point p3, Point p4, Point[] all) {
            if (p1 == p2 && p2 == p3 && p3 == p4) {
                points = new HashSet<>();
                points.addAll(Arrays.asList(all));
                this.lx = p1; this.ly = p1; this.hx = p1; this.hy = p1;
                return;
            }

            if (p1.x < p2.x) {
                this.lx = p1;
            } else {
                this.lx = p2;
            }

            if (p1.y < p2.y) {
                this.ly = p1;
            } else {
                this.ly = p2;
            }

            if (p3.x < p4.x) {
                this.hx = p1;
            } else {
                this.hx = p2;
            }

            if (p3.y < p4.y) {
                this.hy = p1;
            } else {
                this.hy = p2;
            }
        }

        public void addPoint(Point p) {
            if (p.x > hx.x) {
                hx = p;
            }
            if (p.y > hy.y) {
                hy = p;
            }
        }

        public int test(Point p) {
            Point tx = hx, ty = hy;
            if (p.x > hx.x) {
                tx = p;
            }
            if (p.y > hy.y) {
                ty = p;
            }

            int length = tx.x - lx.x;
            int width = ty.y - ly.y;
            return width * length;
        }

        public int getArea() {
            int length = hx.x - lx.x;
            int width = hy.y - ly.y;
            return width * length;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("split.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("split.out")));
        StringTokenizer st;

        int numberOfCows = Integer.parseInt(in.readLine());

        Point[] cows = new Point[numberOfCows];
        for (int i = 0; i < numberOfCows; i++) {
            st = new StringTokenizer(in.readLine());

            cows[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Point[] cowsX = sortX(cows);
        Point[] cowsY = sortY(cows);

        Rect set = new Rect(cowsX[0], cowsY[0]);
    }

    private static boolean greedy(Point[] arr, int x, Set<Point> first, Rect set) {
//        if (set.test(arr[x]) )
        return false;
    }

    private static Point[] sortX(Point[] arr) {
        Point[] ret = arr.clone();
        Arrays.sort(arr, (p1, p2) -> {
            if (p1.x < p2.x) {
                return -1;
            } else if (p1.x == p2.x) {
                return p1.y - p2.y;
            } else {
                return 1;
            }
        });
        return ret;
    }

    private static Point[] sortY(Point[] arr) {
        Point[] ret = arr.clone();
        Arrays.sort(arr, (p1, p2) -> {
            if (p1.y < p2.y) {
                return -1;
            } else if (p1.y == p2.y) {
                return p1.x - p2.x;
            } else {
                return 1;
            }
        });
        return ret;
    }
}
