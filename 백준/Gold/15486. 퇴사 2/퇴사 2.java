import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n+2][2];
		int[] dp = new int[n+2]; // i일부터 n일까지 얻을 수 있는 최대 이익
		StringTokenizer st;
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken()); // 상담 기간
			arr[i][1] = Integer.parseInt(st.nextToken()); // 상담 금액
		}
		for(int i=n; i>0; i--) {
			if (i+arr[i][0] > n+1) // 상담이 n+1일을 초과한다면
				dp[i] = dp[i+1]; // i일에 잡힌 상담은 할 수 X
			else if (i+arr[i][0] == n+1) { // N일에 맞춰 상담을 끝낸다면
				dp[i] = Math.max(dp[i+1], arr[i][1]);
			} else { 
				dp[i] = Math.max(arr[i][1] + dp[i+arr[i][0]], dp[i+1]);
			}
		}
		System.out.println(dp[1]);
	}

}
