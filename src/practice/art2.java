import java.io.*;
import java.util.Arrays;
import java.util.Stack;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;

@SuppressWarnings("ALL")
public class art2 {

    private static class Node {
        int location, color;
        boolean single = false;

        public Node(int location, int color) {
            this.location = -1;
            this.color = color;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("art2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("art2.out")));

        int paintingSize = parseInt(in.readLine());
        Node[] nodes = new Node[paintingSize];
        for (int i = 0; i < paintingSize; i++) {
            int color = parseInt(in.readLine());
            if (color != 0) {
                if (nodes[color - 1] == null) {
                    nodes[color - 1] = new Node(i, color);
                } else {
                    nodes[color - 1].single = true;
                }
            }
        }

        Arrays.sort(nodes, (node, t1) -> {
            if (node == null && t1 == null) {
                return 0;
            } else if (node == null) {
                return 1;
            } else if (t1 == null) {
                return -1;
            }
            return node.location - t1.location;
        });

        Stack<Integer> paint = new Stack<>();
        int maxSize = 0;
        boolean[] added = new boolean[paintingSize];
        for (int i = 0; i < paintingSize; i++) {
            if (nodes[i] == null) {
                break;
            }

            int color = nodes[i].color;
            if (added[color - 1]) {
                paint.pop();
            } else {
                if (nodes[i].single) {
                    added[color - 1] = true;
                    maxSize = max(paint.size() + 1, maxSize);
                } else {
                    added[color - 1] = true;
                    maxSize = max(paint.size() + 1, maxSize);
                    paint.add(color);
                }
            }
        }

        out.println(maxSize);
        out.close();
    }
}
