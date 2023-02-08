import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int money;
		int dp[] = new int[k + 1]; // i원을 만드는 경우의 수
		dp[0] = 1;

		for (int i = 0; i < n; i++) {
			money = Integer.parseInt(br.readLine());
			for(int j=money; j<=k; j++) {
				if (dp[j-money] > 0) { // j-money원을 만들 수 있다면
					dp[j] += dp[j-money]; 
				}
			}
		}
		System.out.println(dp[k]);
	}

}
