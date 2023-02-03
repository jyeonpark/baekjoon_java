
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static long mod = 1000000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[][] dp = new long[n][10];

		for (int i = 1; i < 10; i++) {
			dp[0][i] = 1;
		}
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < 10; j++) {
				switch (j) {
				case 0:
					dp[i][j] = dp[i - 1][1] % mod;
					break;
				case 9:
					dp[i][j] = dp[i - 1][8] % mod;
					break;
				default:
					dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod;
				}
			}
		}
		long sum = 0;
		for (int i = 0; i < 10; i++)
			sum += dp[n - 1][i];
		System.out.println(sum % mod);
	}

}
