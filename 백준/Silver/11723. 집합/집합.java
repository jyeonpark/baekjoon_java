import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        String op;
        int num, tmp;
        HashSet<Integer> hs = new HashSet<>();
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            op = st.nextToken();
            if (op.equals("add")) {
                num = Integer.parseInt(st.nextToken());
                hs.add(num);
            } else if (op.equals("remove")) {
                num = Integer.parseInt(st.nextToken());
                hs.remove(num);
            } else if (op.equals("check")) {
                num = Integer.parseInt(st.nextToken());
                tmp = hs.contains(num) ? 1 : 0;
                sb.append(tmp + "\n");
            } else if (op.equals("toggle")) {
                num = Integer.parseInt(st.nextToken());
                if (hs.contains(num)) hs.remove(num);
                else hs.add(num);
            } else if (op.equals("all")) {
                hs.clear();
                for (int j = 1; j <= 20; j++) hs.add(j);
            } else if (op.equals("empty")) {
                hs.clear();
            }
        }

        bw.write(sb.toString());
        bw.flush();
    }
}