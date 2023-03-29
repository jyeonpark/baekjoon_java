import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;
	static int n, dp[][][], map[][];

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(br.readLine());
		dp = new int[n + 1][n + 1][3];
		map = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp[1][2][0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i > 1 || (i == 1 && j > 2)) {
					if (map[i][j] == 0) {
						dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2]; // 가로로 놓기
						dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2]; // 세로로 놓기

						if (map[i - 1][j] == 0 && map[i][j - 1] == 0) { // 대각선 놓기
							dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j-1][1] + dp[i - 1][j - 1][2];
						}
					}
					//System.out.println(i + "," + j + ":" + dp[i][j][0] + " " + dp[i][j][1] + " " + dp[i][j][2]);
				}
			}
		}

		System.out.println(dp[n][n][0] + dp[n][n][1] + dp[n][n][2]);
	}
}