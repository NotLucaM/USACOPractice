import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class closing {

    private static int[] disjoint;

    private static void init(int n) {
        disjoint = new int[n];
        for (int i = 0; i < n; i++) {
            disjoint[i] = i;
        }
    }

    private static int root(int i) {
        while(disjoint[i] != i) {
            disjoint[i] = disjoint[disjoint[i]];
            i = disjoint[i];
        }
        return i;
    }

    private static void union(int a, int b) {
        int root_a = root(a);
        int root_b = root(b);
        disjoint[root_a] = root_b;
    }

    private static boolean find(int a, int b) {
        return root(a) == root(b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("closing.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
        StringTokenizer st;

        st = new StringTokenizer(in.readLine());
        int barns = Integer.parseInt(st.nextToken());
        int paths = Integer.parseInt(st.nextToken());

        init(barns);
        ArrayList<Integer>[] unions = new ArrayList[barns];
        for (int i = 0; i < paths; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            if (unions[a] == null) {
                unions[a] = new ArrayList<>();
            }
            unions[a].add(b);
            if (unions[b] == null) {
                unions[b] = new ArrayList<>();
            }
            unions[b].add(a);
        }

        int[] orders = new int[barns];
        for (int i = 0; i < barns; i++) {
            orders[i] = Integer.parseInt(in.readLine()) - 1;
        }

        for (int i = barns - 1; i >= 0; i--) {
            int add = orders[i];
            for (int pair : unions[add]) {
                union(add, pair);
            }
        }
    }
}
