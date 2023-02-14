
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
public class Main {

    static int R, N, M;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        int cnt = Math.min(N, M) / 2;
        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Deque<Integer> q;
        for (int i = 0; i < cnt; i++) {
            q = new ArrayDeque<Integer>();
            // 세로1
            for (int j = i; j < N - i; j++) {
                q.add(arr[j][i]);
            }
            // 가로1
            for (int j = i + 1; j < M - 1 - i; j++) {
                q.add(arr[N - 1 - i][j]);
            }

            // 세로2
            for (int j = N - 1 - i; j >= i; j--) {
                q.add(arr[j][M - 1 - i]);
            }

            // 가로2
            for (int j = M - 1 - i - 1; j >= i + 1; j--) {
                q.add(arr[i][j]);
            }

            // R번 회전하기
            for (int j = 0; j < R; j++) {
                q.addFirst(q.pollLast());
            }

            // 회전 후 배열에 다시 넣기
            // 세로1
            for (int j = i; j < N - i; j++) {
                arr[j][i] = q.poll();
            }
            // 가로1
            for (int j = i + 1; j < M - 1 - i; j++) {
                arr[N - 1 - i][j] = q.poll();
            }

            // 세로2
            for (int j = N - 1 - i; j >= i; j--) {
                arr[j][M - 1 - i] = q.poll();
            }

            // 가로2
            for (int j = M - 1 - i - 1; j >= i + 1; j--) {
                arr[i][j] = q.poll();
            }
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

}
