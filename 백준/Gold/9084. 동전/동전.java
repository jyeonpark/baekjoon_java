import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int T, N, M;
	static int[] coin;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());

			coin = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}

			M = Integer.parseInt(br.readLine());
			dp = new int[M+1];
			dp[0] = 1;
			for(int i=0; i<N; i++) {
				for(int j=coin[i]; j<=M; j++) {
					dp[j] += dp[j-coin[i]];
				}
			}
			
			System.out.println(dp[M]);

		}

	}
}
