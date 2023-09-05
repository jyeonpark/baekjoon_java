import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, m, map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		System.out.println(bfs());
	}

	static int bfs() {
		int nowX = 0;
		int nowY = 0;
		boolean[][] visited = new boolean[n][m];
		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { nowX, nowY, 1 });
		visited[nowX][nowY] = true;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == 1 && !visited[nx][ny]) {
					if (nx == n - 1 && ny == m - 1) {
						return (now[2] + 1);
					}
					q.add(new int[] { nx, ny, now[2] + 1 });
					visited[nx][ny] = true;
				}
			}
		}
		return Integer.MAX_VALUE;
	}

}
