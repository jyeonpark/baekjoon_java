import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb;
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int k, w, h;
	static int[][] map;
	static boolean[][][] visited;
	static int first_row = -1;
	static int first_col = -1;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[] horse_dx = { -2, -2, -1, -1, 1, 1, 2, 2 };
	static int[] horse_dy = { -1, 1, -2, 2, -2, 2, -1, 1 };

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new int[h][w];
		visited = new boolean[h][w][k+1]; // 지금까지 말을 k번 움직였을 때 (i,j) 방문 여부
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); // 0은 평지, 1은 장애물
			}
		}
		sb.append(bfs());

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int bfs() {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.add(new int[] { 0, 0, 0, 0 }); // x좌표, y좌표, 말처럼 움직인횟수, 총 움직인횟수
		visited[0][0][0] = true;

		while (!q.isEmpty()) {
			int[] info = q.poll();
			int x = info[0];
			int y = info[1];
			int horse_cnt = info[2];
			int cnt = info[3];
			
			if (x == h - 1 && y == w- 1) {
				return cnt;
			}

			if (horse_cnt < k) {
				// 말의 움직임으로 움직이기
				for (int i = 0; i < 8; i++) {
					int nx = x + horse_dx[i];
					int ny = y + horse_dy[i];
					if (nx >= 0 && nx < h && ny >= 0 && ny < w && map[nx][ny] != 1 && !visited[nx][ny][horse_cnt+1]) {
						visited[nx][ny][horse_cnt+1] = true;
                        q.add(new int[] { nx, ny, horse_cnt+1, cnt + 1 });
					}
				}
			}

			// 인접한 네 방향으로 움직이기
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx >= 0 && nx < h && ny >= 0 && ny < w && map[nx][ny] != 1 && !visited[nx][ny][horse_cnt]) {
					visited[nx][ny][horse_cnt] = true;
					q.add(new int[] { nx, ny, horse_cnt, cnt + 1 });
				}
			}

		}
		return -1;
	}
}