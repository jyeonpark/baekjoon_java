import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;
	static int n, w[][], dp[][];
	static final int INF = 20000000;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(br.readLine());
		w = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				w[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[n][(1<<n)-1];
		for(int i=0; i<n; i++)
			Arrays.fill(dp[i], INF);
		
		System.out.println(tsp(0,1));
	}
	
	static int tsp(int node, int visited) {
		
		if (visited == ((1<<n)-1)) { // 모든 도시를 방문했다면
			if (w[node][0] == 0)
				return INF;
			return w[node][0];
		}
		
		if (dp[node][visited] != INF)
			return dp[node][visited];
		
		for(int i=0; i<n; i++) {
			if ((visited & (1<<i)) == 0 && w[node][i] !=0) { 
				dp[node][visited] = Math.min(dp[node][visited], 
						tsp(i, visited | (1<<i)) + w[node][i]);
				//System.out.printf("dp[%d][%d] : %d\n", node, visited, dp[node][visited]);
			}
		}
		
		return dp[node][visited];
		
	}
}