import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int r, c, time, hedgehog_x, hedgehog_y, cave_x, cave_y;
	static char[][] map;
	static Queue<Point> water;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		water = new ArrayDeque<>();
		for (int i = 0; i < r; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < c; j++) {
				if (map[i][j] == 'D') {
					cave_x = i;
					cave_y = j;
				} else if (map[i][j] == 'S') {
					hedgehog_x = i;
					hedgehog_y = j;
					map[i][j] = '.'; // 고슴도치는 이동하므로 좌표 저장후 .으로 표시한다
				} else if (map[i][j] == '*')
					water.add(new Point(i, j));
			}
		}

		int time = bfs();
		System.out.println(time == -1 ? "KAKTUS" : time);

	}

	static int bfs() {
		Queue<Move> q = new ArrayDeque<>();
		q.add(new Move(hedgehog_x, hedgehog_y, 0));
		boolean[][] visited = new boolean[r][c]; // 고슴도치가 방문한 좌표
		visited[hedgehog_x][hedgehog_y] = true;
		while (!q.isEmpty()) {

			// 물이 확장한다.
			int water_size = water.size();
			for (int i = 0; i < water_size; i++) { // 1분에 확장될 수 있는 물
				Point w = water.poll();
				for (int d = 0; d < 4; d++) {
					int nx = w.x + dx[d];
					int ny = w.y + dy[d];
					if (isRange(nx, ny) && map[nx][ny] == '.') { // 돌,소굴이 아니라면 확장 가능
						map[nx][ny] = '*'; // 확장된 물 표시
						water.add(new Point(nx, ny));
					}
				}
			}

			// 비버가 움직인다.
			int move_size = q.size();
			for (int i = 0; i < move_size; i++) {
				Move now = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = now.x + dx[d];
					int ny = now.y + dy[d];
					if (isRange(nx, ny)) {
						if (nx == cave_x && ny == cave_y)
							return now.time + 1;
						if (map[nx][ny] == '.' && !visited[nx][ny]) {
							q.add(new Move(nx, ny, now.time + 1));
							visited[nx][ny] = true;
						}
					}
				}
			}
		}
		return -1;
	}

	static boolean isRange(int x, int y) {
		return x >= 0 && x < r && y >= 0 && y < c;
	}

	static class Move {
		int x;
		int y;
		int time;

		public Move(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
}