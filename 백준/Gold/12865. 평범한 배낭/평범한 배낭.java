import java.io.*;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[] weights = new int[n + 1];
		int[] values = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			weights[i] = Integer.parseInt(st.nextToken());
			values[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[n+1][k+1];
		for(int i=1; i<=n; i++) {
			for(int w=1; w<=k; w++) {
				if (weights[i] > w) {
					dp[i][w] = dp[i-1][w];
				} else {
					dp[i][w] = Math.max(dp[i-1][w], values[i] + dp[i-1][w-weights[i]]);
				}
			}
		}
		System.out.println(dp[n][k]);
	}
}
