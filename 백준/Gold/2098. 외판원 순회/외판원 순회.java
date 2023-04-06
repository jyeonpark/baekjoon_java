import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int INF = 20000000;
	static int n, w[][], dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		w = new int[n][n];
		dp = new int[n][(1<<n)-1];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				w[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(tsp(0,1));
	}

	static int tsp(int node, int visited) {
		
		if (visited == ((1<<n)-1)) {
			if (w[node][0] == 0)	
				return INF;
			else return w[node][0];
		}
		
		if (dp[node][visited] != 0)	return dp[node][visited];
		
		dp[node][visited] = INF;
		
		for(int i=0; i<n; i++) {
			if (w[node][i] ==0)	continue; // node~i 로 가는 경로가 없을 때
			if ((visited & (1<<i)) != 0)	continue; // 이미 i노드를 방문했을 때
			// node~i + i~출발지
			dp[node][visited] = Math.min(dp[node][visited], tsp(i, visited | (1<<i)) + w[node][i]);
		}
		
		return dp[node][visited];
	}

}