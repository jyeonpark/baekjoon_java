import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;
	static int n,k, dp[];

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		if (n>=k) {
			dp = new int[n+1];
		}else {
			dp = new int[k*2+1];
		}
		Arrays.fill(dp, Integer.MAX_VALUE);
		bfs();
		System.out.println(dp[k]);
	}
	
	static void bfs() {
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(n);
		dp[n] = 0;
		
		while(!q.isEmpty()) {
			int x = q.poll();
			 //System.out.printf("좌표 %d : %d초\n", x, dp[x]);
			if (x == k) break;
			if (x>k && dp[x-1]>dp[x]+1) {
				dp[x-1] = dp[x] + 1;
				q.add(x-1);
			}
			else if (x<k) {
				if (dp[x*2] > dp[x]) {
					dp[x*2] = dp[x];
					q.add(x*2);
				}
				if (dp[x+1] > dp[x]+1) {
					dp[x+1] = dp[x]+1;
					q.add(x+1);
				}
				if (x-1>=0 && dp[x-1]>dp[x]+1) {
					dp[x-1] = dp[x]+1;
					q.add(x-1);
				}
			}
		}
	}
}