import java.io.*;
import java.util.Arrays;

public class p248 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("248.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("248.out")));

        int size = Integer.parseInt(in.readLine());

        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = Integer.parseInt(in.readLine());
        }

        int ans = 0;
        int[] temp;
        for (int i = 0; i < size; i++) {
            temp = Arrays.copyOfRange(numbers, i, size);
            for (int j = 0; j < temp.length - 1; j++) {
                if (temp[j] == temp[j + 1]) {
                    temp[j + 1] = temp[j] + 1;
                    ans = Math.max(ans, temp[j] + 1);
                }
            }
        }

        out.println(ans);
        out.close();
    }
}
