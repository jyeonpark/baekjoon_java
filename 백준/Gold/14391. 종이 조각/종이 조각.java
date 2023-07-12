import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m, ans, arr[][], visited[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++)
                arr[i][j] = line.charAt(j) - '0';
        }

        cut(0, 0);
        System.out.println(ans);
    }

    static void cut(int x, int y) {

        if (x >= n) {
            getSum();
            return;
        }

        if (y >= m) {
            cut(x + 1, 0);
            return;
        }

        visited[x][y] = 1;
        cut(x, y + 1);
        visited[x][y] = 0;
        cut(x, y + 1);
    }

    static void getSum() {
        int sum = 0;

        // 가로 계산
        for (int i = 0; i < n; i++) {
            int rSum = 0;
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == 1) { // 연속되는 경우
                    rSum *= 10;
                    rSum += arr[i][j];
                } else {
                    sum += rSum;
                    rSum = 0;
                }
            }
            sum += rSum;
        }

        // 세로 계산
        for (int i = 0; i < m; i++) {
            int cSum = 0;
            for (int j = 0; j < n; j++) {
                if (visited[j][i] == 0) { // 연속되는 경우
                    cSum *= 10;
                    cSum += arr[j][i];
                } else {
                    sum += cSum;
                    cSum = 0;
                }
            }
            sum += cSum;
        }
        ans = Math.max(ans, sum);
    }
}