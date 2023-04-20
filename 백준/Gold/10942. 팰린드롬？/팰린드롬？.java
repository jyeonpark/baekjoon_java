import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, S, E, arr[];
	static boolean dp[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		dp = new boolean[N + 1][N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i][i] = true;
		}

		for (int i = 1; i < N; i++) { 
			for (int j = 1; j <= N - i; j++) {
				if (i<=2) {
					if (arr[j] == arr[j+i])	dp[j][j+i] = true;
				}
				else {
					if (arr[j] == arr[j+i]) {
						dp[j][j+i] = dp[j+1][j+i-1];
					} else {
						dp[j][j+i] = false;
					}
				}
			}
		}
		
		M = Integer.parseInt(br.readLine());
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			sb.append(dp[S][E] ? "1" : "0").append("\n");
		}
		
		System.out.println(sb.toString());

	}

}
