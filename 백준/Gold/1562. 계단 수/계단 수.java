import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static final int MOD = 1000000000;
	static int N, dp[][][], ans;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1][10][1 << 10]; // dp[길이][마지막숫자][방문숫자]

		for (int i = 1; i <= 9; i++) { // 길이가 1일 때 초기화
			dp[1][i][1 << i] = 1;
		}

		for (int i = 2; i <= N; i++) { // 길이
			for (int j = 0; j <= 9; j++) { // 마지막 숫자
				for (int k = 0; k < (1 << 10); k++) { // visited
					if (j == 0) {
						dp[i][j][k | (1 << j)] = (dp[i][j][k | (1 << j)] + dp[i - 1][j + 1][k]) % MOD;
					} else if (j == 9) {
						dp[i][j][k | (1 << j)] = (dp[i][j][k | (1 << j)] + dp[i - 1][j - 1][k]) % MOD;
					} else {
						dp[i][j][k | (1 << j)] = (dp[i][j][k | (1 << j)] + dp[i - 1][j - 1][k] + dp[i - 1][j + 1][k])
								% MOD;
					}
				}
			}
		}

		for (int j = 0; j <= 9; j++) {
			ans = (ans + dp[N][j][(1 << 10) - 1]) % MOD;
		}

		System.out.println(ans);
	}

}
