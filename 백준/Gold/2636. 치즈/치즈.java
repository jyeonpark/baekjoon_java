import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, m, map[][], cheese, time, remain;
	static Queue<Point> q; // 빈칸이 들어있다.
	static int dx[] = { 1, 0, -1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		q = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0)
					cheese++; // 치즈 개수
			}
		}
		bfs(); // 치즈 녹이기
		System.out.println(time);
		System.out.println(remain);

	}

	static void bfs() {

		while (cheese > 0) {
			
			boolean visited[][] = new boolean[n][m];
			findFirstBorder(visited);
			int size = q.size();
			remain = cheese;
			for (int i = 0; i < size; i++) {
				Point now = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = now.x + dx[d];
					int ny = now.y + dy[d];
					
					// 치즈 테두리라면
					if (isRange(nx, ny) && map[nx][ny] == 1 && !visited[nx][ny]) {
						map[nx][ny] = 0;
						visited[nx][ny] = true;
						q.add(new Point(nx, ny));
						cheese--;
					}
				}
			}
			time++;
		}

	}

	static void findFirstBorder(boolean[][] visited) { // 빈칸 찾기
		
		Queue<Point> tmp = new ArrayDeque<>();
		tmp.add(new Point(0, 0));
		visited[0][0] = true;
		while (!tmp.isEmpty()) {
			Point now = tmp.poll();
			q.add(now);
			for (int d = 0; d < 4; d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				// 빈칸이라면
				if (isRange(nx, ny) && map[nx][ny] == 0 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					tmp.add(new Point(nx, ny));
				}
			}
		}
	}

	static boolean isRange(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}

}
