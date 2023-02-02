
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int n = sc.nextInt();
			int[][] arr = new int[2][n];
			int[][] dp = new int[2][n];
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < n; j++) {
					arr[i][j] = sc.nextInt();
					dp[i][j] = arr[i][j];
				}
			}
			if (n > 1) {
				dp[0][1] += dp[1][0];
				dp[1][1] += dp[0][0];

				for (int j = 2; j < n; j++) {
					for (int i = 0; i < 2; i++) {
						dp[i][j] += Math.max(dp[1 - i][j - 2], dp[1 - i][j - 1]);
					}
				}
			}
			System.out.println(Math.max(dp[0][n - 1], dp[1][n - 1]));
		}
	}

}
