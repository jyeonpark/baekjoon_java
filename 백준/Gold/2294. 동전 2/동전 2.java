import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int money;
		int dp[] = new int[k + 1]; // i원을 만드는데 필요한 동전의 최소 개수
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		for (int i = 0; i < n; i++) {
			money = Integer.parseInt(br.readLine());
			for(int j=money; j<=k; j++) {
				if (dp[j-money] != Integer.MAX_VALUE)
					dp[j] = Math.min(dp[j], dp[j-money]+1);
			}
		}
		dp[k] = dp[k] == Integer.MAX_VALUE ? -1 : dp[k];
		System.out.println(dp[k]);
	}

}
