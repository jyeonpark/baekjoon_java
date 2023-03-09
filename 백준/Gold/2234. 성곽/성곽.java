import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, m, map[][], visited[][], roomIdx = 1, maxRoomCnt = 0;
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { -1, 0, 1, 0 };
	static HashMap<Integer, Integer> roomCnt;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[m][n];
		visited = new int[m][n];
		roomCnt = new HashMap<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j] == 0) {
					int cnt = bfs(i, j);
					roomCnt.put(roomIdx, cnt);
					maxRoomCnt = Math.max(maxRoomCnt, cnt);
					roomIdx++;
				}
			}
		}
		
		int ans = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				for(int d=0; d<4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if (nx >= 0 && nx < m && ny >= 0 && ny < n && visited[nx][ny] != visited[i][j]) {
						ans = Math.max(ans, roomCnt.get(visited[nx][ny]) + roomCnt.get(visited[i][j]));
					}
				}
			}
		}

		sb.append(roomIdx-1).append("\n").append(maxRoomCnt).append("\n").append(ans);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

	static int bfs(int startX, int startY) {
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(startX, startY));
		visited[startX][startY] = roomIdx;
		int cnt = 1;

		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				if ((map[p.x][p.y] & (1 << i)) != 0)
					continue; // 해당 방향으로 벽이 있으면 탐색X

				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (nx >= 0 && nx < m && ny >= 0 && ny < n && visited[nx][ny] == 0) {
					visited[nx][ny] = roomIdx;
					q.add(new Point(nx, ny));
					cnt++;
				}
			}
		}
		return cnt;
	}
}