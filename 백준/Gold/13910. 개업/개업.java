import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static final int MAX = 10001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 짜장면의 수
		int[] woks = new int[MAX];
		int[] dp = new int[n + 1]; // dp[i] => i개의 짜장면을 만들기 위해 드는 최소 요리 횟수
		Arrays.fill(dp, MAX);
		dp[0] = 0;
		woks[0] = 1;

		int m = Integer.parseInt(st.nextToken()); // 웍의 수
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			woks[Integer.parseInt(st.nextToken())]++; // 웍 개수 증가
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= i/2; j++) {
				if ((j == i - j && woks[j] >= 2)) { // 크기가 같은 웍 두개를 사용할 수 있는 경우
					dp[i] = 1;
				} else if (j != i - j && woks[j] > 0 && woks[i - j] > 0) { // 크기가 다른 웍 두개를 사용할 수 있는 경우
					dp[i] = 1;
				} else if (dp[j] != MAX && dp[i - j] != MAX) {
					dp[i] = Math.min(dp[i], dp[j] + dp[i - j]);
				}
			}
			//System.out.printf("dp[%d] : %d\n" ,i,dp[i]);
		}
		dp[n] = (dp[n] >= MAX ? -1 : dp[n]);
		System.out.println(dp[n]);
	}
}
