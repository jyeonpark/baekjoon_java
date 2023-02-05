import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] rock = new int[n+1];
		boolean[] dp = new boolean[n + 1]; // i번째 돌에 도달할 수 있는지 여부
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++)
			rock[i] = Integer.parseInt(st.nextToken());
		
		dp[1] = true; // 첫번째 돌은 시작위치이므로 true
		for (int i = 2; i <= n; i++) {
			for(int j = 1; j<i; j++) {
				if (dp[j]) { // j번째 돌에 도달할 수 있다면
					if ((i-j) * (1+Math.abs(rock[j]-rock[i])) <= k) { // j번째 돌에서 i번째 돌로 이동가능하다면
						dp[i] = true;
						break;
					}
				}
			}
		}
		if (dp[n])
			System.out.println("YES");
		else 
			System.out.println("NO");
	}

}
