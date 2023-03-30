import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int t, n, map[][], dp[][];
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		while ((n = Integer.parseInt(br.readLine())) != 0) {
			map = new int[n][n];
			dp = new int[n][n];

			for (int i = 0; i < n; i++) {
				Arrays.fill(dp[i], Integer.MAX_VALUE);
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			bfs();
			System.out.printf("Problem %d: %d\n", ++t, dp[n-1][n-1]);
		}
	}

	static void bfs() {
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(0, 0, map[0][0]));
		dp[0][0] = map[0][0];
		while (!q.isEmpty()) {
			Node now = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (isRange(nx, ny) && dp[nx][ny] > dp[now.x][now.y] + map[nx][ny]) {
					dp[nx][ny] = dp[now.x][now.y] + map[nx][ny];
					q.add(new Node(nx, ny, dp[nx][ny]));
				}
			}
		}
	}

	static boolean isRange(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}

	static class Node implements Comparable<Node> {
		int x;
		int y;
		int weight;

		public Node(int x, int y, int weight) {
			super();
			this.x = x;
			this.y = y;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
}
