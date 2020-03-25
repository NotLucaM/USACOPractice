import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

@SuppressWarnings("ALL")
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

        int[] dp = new int[cows];
        dp[0] = cowSkills[0];
        for (int i = 0; i < cows; i++) {
            int max = cowSkills[i];
            for (int j = i; i - j < teamSize && j >= 0; j--) {
                max = Math.max(max, cowSkills[j]);

                if (j == 0) {
                    dp[i] = Math.max(dp[i], max * (i + 1 - j));
                } else {
                    dp[i] = Math.max(dp[i], dp[j - 1] + max * (i + 1 - j));
                }
            }
        }

        out.println(dp[cows - 1]);
        out.close();
    }
}
