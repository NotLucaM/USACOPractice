import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

@SuppressWarnings("ALL")
public class snowboots {

    private static class Boot {

        private int index;
        private int strength;
        private int travel;
        private boolean works;

        public Boot(int index, int strength, int travel) {
            this.index = index;
            this.strength = strength;
            this.travel = travel;
            this.works = false;
        }

        @Override
        public String toString() {
            return works ? "1" : "0";
        }
    }

    private static class Tile {

        private Tile prev;
        private Tile next;
        private int index;
        private int snow;
        private int distance;

        public Tile(int index, int snow) {
            this(null, index, snow);
        }

        public Tile(Tile prev, int index, int snow) {
            this.prev = prev;
            this.next = null;
            this.index = index;
            this.snow = snow;
            this.distance = -1;
        }

        public void filter(int threshold) {
            if (snow > threshold) {
                prev.setNext(this.next);
                next.setPrev(this.prev);
            }

            if (next != null) {
                next.filter(threshold);
            }
        }

        public void setPrev(Tile prev) {
            this.prev = prev;
        }

        public void setNext(Tile next) {
            this.next = next;

            this.distance = Math.abs(this.snow - next.snow);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("snowboots.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
        StringTokenizer st;

        st = new StringTokenizer(in.readLine());
        int tiles = parseInt(st.nextToken());
        int bootAmount = parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        Tile head = new Tile(0, parseInt(st.nextToken()));
        Tile tail = head;
        for (int i = 1; i < tiles; i++) {
            tail = new Tile(tail, i, parseInt(st.nextToken()));
            tail.prev.setNext(tail);
        }

        Boot[] boots = new Boot[bootAmount];
        for (int i = 0; i < bootAmount; i++) {
            st = new StringTokenizer(in.readLine());
            boots[i] = new Boot(i, parseInt(st.nextToken()), parseInt(st.nextToken()));
        }

        Arrays.sort(boots, new Comparator<Boot>() {
            @Override
            public int compare(Boot boot, Boot t1) {
                return t1.strength - boot.strength;
            }
        });

        out.close();
    }
}
