import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 짜장면의 수
		int[] woks = new int[10001];
		int[] dp = new int[n+1]; // dp[i] => i개의 짜장면을 만들기 위해 드는 최소 요리 횟수
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		woks[0] = 1;
		
		int m = Integer.parseInt(st.nextToken()); // 웍의 수
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++) {
			woks[Integer.parseInt(st.nextToken())]++; // 웍 개수 증가
		}
		//System.out.println(woks[1] + " " + woks[2] + " " + woks[3] + " " + woks[4] + " " + woks[5]);
		
		
		for(int i=1; i<=n; i++) {
			for(int j=0; j<i; j++) {
				if ((j==i-j && woks[j] >= 2)) { // 크기가 같은 웍 두개를 사용할 수 있는 경우
					dp[i] = 1;
					//System.out.println("i : " + i + "| 	" + "j : " + j + "| 	" + woks[j] + " " + woks[i-j]);
				}
				else if (j!=i-j && woks[j] > 0 && woks[i-j]>0) { // 크기가 다른 웍 두개를 사용할 수 있는 경우
					dp[i] = 1;
					//System.out.println("i : " + i + "| 	" + "j : " + j + "| 	" + woks[j] + " " + woks[i-j]);
				}
				else if (dp[j] != Integer.MAX_VALUE && dp[i-j]  != Integer.MAX_VALUE ) {
					dp[i] = Math.min(dp[i], dp[j]+dp[i-j]);
				}
			}
			//System.out.printf("dp[%d] : %d\n",i,dp[i]);
		}
		dp[n] = (dp[n] == Integer.MAX_VALUE ? -1 : dp[n]);
		System.out.println(dp[n]);
	}
}
