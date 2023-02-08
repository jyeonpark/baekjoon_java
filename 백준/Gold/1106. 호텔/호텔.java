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
		int c = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		// 각 도시에서 비용으로 얻을 수 있는 고객수는 100명 이하
		// 적어도 C명을 늘여야하므로 그보다 더 큰 고객을 들였을 때의 비용이 더 작을 수 있음
		int dp[] = new int[c+100]; 
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int customer = Integer.parseInt(st.nextToken());
			
			for(int j=customer; j<c+100; j++) {
				// 돈에 정수배 만큼 투자할 수 있으므로
				// cost(현재 비용) + dp[j-customer] 로 j명의 고객을 늘린다.
				if (dp[j-customer] != Integer.MAX_VALUE) // 무한이라면 아직 갱신되지 않은 값이므로 고객을 확보할 수 없다
					dp[j] = Math.min(dp[j], cost+dp[j-customer]);
			}
		}
		
		int answer = Integer.MAX_VALUE;
		for(int i=c; i<c+100; i++) {// 최소 c명을 확보해야 하므로 dp[c]부터 탐색
			answer = Math.min(answer, dp[i]);
		}
		System.out.println(answer);
		
	}

}
