import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int man[] = new int[n + 1];
		int woman[] = new int[m + 1];
		int dp[][] = new int[n + 1][m + 1]; // dp[i][j] => 남자 i, 여자 j 인덱스까지 탐색하여 커플을 만들었을 때의 최소 성격 차이

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++)
			man[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= m; i++)
			woman[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(man);
		Arrays.sort(woman);

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				dp[i][j] = dp[i - 1][j - 1] + Math.abs(man[i] - woman[j]); // i == j 인 경우 커플이 차례대로 성사된다.

				if (i > j) { // 남자가 더 많은 경우
					dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]); // i-1번 남자까지의 값 vs i와 j여자를 성사시켰을 때의 값 중 더 작은 값을 선택한다.
				}

				if (i < j) {
					dp[i][j] = Math.min(dp[i][j], dp[i][j - 1]);
				}

			}
		}
		
		System.out.println(dp[n][m]);

	}

}
