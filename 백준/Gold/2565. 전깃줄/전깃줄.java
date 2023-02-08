import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static long mod = 1000000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] wire = new int[n][2];
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			wire[i][0] = Integer.parseInt(st.nextToken());
			wire[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(wire, (w1,w2)->w1[0]-w2[0]); // A전깃줄 위치로 오름차순 정렬하기
		
		/**
		 * LIS (최장 증가 부분 수열)
		 */
		int ans = Integer.MIN_VALUE;
		for(int i=0; i<n; i++) { // i번째 전깃줄 => dp[i] 란 0~i까지 탐색했을 때 최대로 연결할 수 있는 전깃줄 개수
			dp[i] = 1;
			for(int j=0; j<i; j++) { // A전깃줄에서 i보다 위에 있는 전깃줄들 탐색
				if (wire[i][1] > wire[j][1]) { // B전깃줄에서 j에 연결된 전깃줄이 i에 연결된 전깃줄보다 위에 있다면
					dp[i] = Math.max(dp[i], dp[j]+1); // dp[i] 갱신
				}
			}
			ans = Math.max(ans, dp[i]); 
		}
		
		System.out.println(n-ans);
	}

}
