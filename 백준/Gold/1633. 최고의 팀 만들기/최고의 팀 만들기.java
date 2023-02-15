import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int[] white;
	static int[] black;
	static int n = 0;
	static int[][][] dp;

	public static void main(String[] args) throws IOException {
		
		white = new int[1001];
		black = new int[1001];

		String s = "";
		while ((s = br.readLine()) != null) {
			n++;
			st = new StringTokenizer(s);
			white[n] = Integer.parseInt(st.nextToken());
			black[n] = Integer.parseInt(st.nextToken());		
		}
		

		// dp[i][w][b]
		// i번째 선수까지 선택했을 때 백팀 w명, 흑팀 b명일 때의 능력치 최댓값
		dp = new int[n+1][16][16]; 
		solution(n,15, 15);
		

		System.out.println(dp[n][15][15]);

	}
	
	static int solution(int i, int wIdx, int bIdx) {
		if (i==0)
			return 0;
		
		if (wIdx==0 && bIdx==0)
			return 0;
		
		int ans = dp[i][wIdx][bIdx];		
		
		if (ans != 0)
			return ans;
		
		// i번째 선수가 선택되지 않는다면
		ans = Math.max(ans, solution(i-1, wIdx, bIdx));
		
		// i번째 선수가 백팀에 들어간다면
		if (wIdx > 0)
			ans = Math.max(ans, solution(i-1, wIdx-1, bIdx)+white[i]);
		
		// i번째 선수가 흑팀에 들어간다면
		if (bIdx > 0)
			ans = Math.max(ans, solution(i-1, wIdx, bIdx-1)+black[i]);

		dp[i][wIdx][bIdx] = ans;
		return ans;
	}

}
