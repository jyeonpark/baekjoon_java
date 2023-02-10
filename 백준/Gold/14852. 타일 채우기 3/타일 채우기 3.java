import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int n;
	static final int MOD = 1000000007;
	static long[][] dp = new long[1000001][2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		// dp[n]=2* dp[n-1] + 3*dp[n-2]+ (2* dp[n-3]+ 2*dp[n-4] + .... + 2*dp[0] )
		// dp[i][1] => i-3까지의 합 누적 => dp[n-3]+dp[n-4] + ... + dp[0] = dp[i-3][0] +
		// dp[i-1][1]
		// dp[i][0] = 2 * dp[n-1][0] + 3 * dp[n-2][0] + 2 * dp[i][1]
		dp[0][0] = 0;
		dp[1][0] = 2;
		dp[2][0] = 7;
		dp[2][1] = 1; // dp[3][1] = 2이기 때문

		for (int i = 3; i <= n; i++) {
			dp[i][1] = (dp[i - 3][0] + dp[i - 1][1]) % MOD;
			dp[i][0] = (3 * (dp[i - 2][0]) + 2 * (dp[i - 1][0]) + 2 * dp[i][1]) % MOD;
		}
		System.out.println(dp[n][0]);

	}

}
