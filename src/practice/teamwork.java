package practice;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class teamwork {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("teamwork.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("teamwork.out")));
        StringTokenizer st;

        st = new StringTokenizer(in.readLine());
        int cows = parseInt(st.nextToken());
        int teamSize = parseInt(st.nextToken());

        int[] cowSkills = new int[cows];
        for (int i = 0; i < cows; i++) {
            cowSkills[i] = parseInt(in.readLine());
        }
    }
}
