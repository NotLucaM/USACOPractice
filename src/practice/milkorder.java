import java.io.*;
import java.util.*;

/*
 problem http://usaco.org/index.php?page=viewproblem2&cpid=838
 */

public class milkorder {

    private static class Node {
        public int from, to;

        public Node(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    private static int cows = -1;
    private static ArrayList<Integer>[] successors;
    private static int[] result;


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("milkorder.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));
        StringTokenizer st;

        st = new StringTokenizer(in.readLine());
        cows = Integer.parseInt(st.nextToken());
        successors = new ArrayList[cows];
        int observations = Integer.parseInt(st.nextToken());
        ArrayList<Node>[] input = new ArrayList[observations];
        for (int i = 0; i < cows; i++) {
            successors[i] = new ArrayList<>();
        }
        result = new int[cows];
        for (int i = 0; i < observations; i++) {
            input[i] = new ArrayList<>();
            st = new StringTokenizer(in.readLine());
            int observed = Integer.parseInt(st.nextToken());
            int last = Integer.parseInt(st.nextToken()) - 1;
            for (int j = 1; j < observed; j++) {
                int current = Integer.parseInt(st.nextToken()) - 1;
                input[i].add(new Node(last, current));
                last = current;
            }
        }

        int l = 0, r = observations;
        int m;
        while (l <= r) {
             m = l + (r - l) / 2;

            if (check(input, m)) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        for (int i = 0; i < result.length; i++) {
            if (i != 0) out.print(" ");
            out.print((result[i] + 1));
        }
        out.close();
    }

    private static boolean check(ArrayList<Node>[] edges, int level) {
        boolean[] visited = new boolean[cows];
        int[] precursors = new int[cows];
        int[] res = new int[cows];

        for (ArrayList<Integer> list : successors) {
            list.clear();
        }

        for (int i = 0; i < level; i++) {
            for (Node edge : edges[i]) {
                successors[edge.from].add(edge.to);
                precursors[edge.to]++;
            }
        }

        PriorityQueue<Integer> lexicographic = new PriorityQueue<>();

        for (int i = 0; i < cows; i++) {
            if (precursors[i] == 0) {
                lexicographic.add(i);
            }
        }

        for (int i = 0; i < cows; i++) {
            if (lexicographic.isEmpty()) {
                return false;
            }

            int r = lexicographic.remove();
            for (int next : successors[r]) {
                precursors[next]--;
                if (precursors[next] == 0) {
                    lexicographic.add(next);
                }
            }

            res[i] = r;
            visited[i] = true;
        }

        for (boolean visit : visited) {
            if (!visit) {
                return false;
            }
        }

        result = res;
        return true;
    }
}
