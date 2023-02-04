
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[][] rock = new int[n][2];
		int[] dp = new int[n + 1];
		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			rock[i][0] = Integer.parseInt(st.nextToken()); // 작은 점프
			rock[i][1] = Integer.parseInt(st.nextToken()); // 큰 점프
		}
		int k = Integer.parseInt(br.readLine());

		if (n >= 2) {
			dp[2] = rock[1][0];
		}
		if (n >= 3) {
			dp[3] = Math.min(rock[1][1], dp[2] + rock[2][0]);
		}

		int energy = Integer.MAX_VALUE;
		if (n >= 4) {
			for (int i = 4; i <= n; i++) { // 매우 큰 점프를 할 수 있는 위치
				for (int j = 4; j <= n; j++) {
					if (i == j) { // 큰 점프를 할 수 있는 돌의 위치
						dp[j] = dp[j - 3] + k;
					} else
						dp[j] = Integer.MAX_VALUE;
					dp[j] = Math.min(dp[j], Math.min(dp[j - 2] + rock[j - 2][1], dp[j - 1] + rock[j - 1][0]));
				}
				energy = Math.min(energy, dp[n]);
			}
		} else {
			energy = dp[n];
		}
		System.out.println(energy);
	}

}
