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
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n + 1][n + 1]; // 딸기:0, 초코:1, 바나나:2
		int[][][] dp = new int[n + 1][n + 1][3];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		/**
		 * 우유를 마시는 순서 : 0->1->2->0->1->2->...
		 * 시작할 때는 딸기우유만 마실 수 있다.
		 */
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				int milk = arr[i][j];
				if (milk==0) { // 딸기우유
					dp[i][j][0] = Math.max(dp[i-1][j][2]+1, dp[i][j-1][2]+1); // 이전에 먹은 우유가 바나나우유인 경우 -> 현재 딸기우유를 마실 수 있음
				} else {
					dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i][j-1][0]); // 이전에 먹은 우유가 딸기우유인 경우
				}
				if (milk==1 && dp[i][j][0] > 0) { // 초코우유 -> 이전에 딸기우유를 먹은 적이 있어야 한다.
					dp[i][j][1] = Math.max(dp[i-1][j][0]+1, dp[i][j-1][0]+1); // 이전에 먹은 우유가 딸기우유인 경우 -> 현재 초코우유를 마실 수 있음
				} else {
					dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i][j-1][1]); // 이전에 먹은 우유가 초코우유인 경우
				}
				if (milk==2 && dp[i][j][1] > 0) { // 바나나우유 -> 이전에 초코우유를 먹은 적이 있어야 한다.
					dp[i][j][2] = Math.max(dp[i-1][j][1]+1, dp[i][j-1][1]+1); // 이전에 먹은 우유가 바나나우유인 경우 -> 현재 바나나우유를 마실 수 있음
				} else {
					dp[i][j][2] = Math.max(dp[i-1][j][2], dp[i][j-1][2]); // 이전에 먹은 우유가 바나나우유인 경우
				}
			}
		}
		
		System.out.println(Math.max(dp[n][n][0], Math.max(dp[n][n][1], dp[n][n][2])));
	}

}
