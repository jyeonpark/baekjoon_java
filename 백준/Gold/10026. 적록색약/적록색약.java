import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static StringBuilder sb;
	static char[][] map;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}

		boolean[][] visited = new boolean[n][n];
		int normal_cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					bfs(i, j, visited);
					normal_cnt++;
				}
			}
		}

		int rg_weakness_cnt = 0;
		for(int i=0; i<n; i++)
			Arrays.fill(visited[i], false);
		// 초록색을 빨강색으로 대체하기
		for(int i=0; i<n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 'G') {
					map[i][j] = 'R';
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					bfs(i, j, visited);
					rg_weakness_cnt++;
				}
			}
		}
		sb.append(normal_cnt).append(" ").append(rg_weakness_cnt);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bfs(int x, int y, boolean[][] visited) {
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(x, y));
		char color = map[x][y];
		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
					if (map[nx][ny] == color && !visited[nx][ny]) {
						visited[nx][ny] = true;
						q.add(new Point(nx, ny));
					}
				}
			}
		}
	}
}