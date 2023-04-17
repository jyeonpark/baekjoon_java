import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, m, map[][], iceCnt, time;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0)	iceCnt++;
			}
		}

		outerLoop: while (true) { // 빙산이 두 덩어리 이상으로 분리될 때까지
			if (iceCnt == 0) {
				time = 0;
				break;
			}

			// 두 덩어리 이상인지 체크하기
			boolean[][] visited = new boolean[n][m];
			boolean check = false;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] > 0 && !visited[i][j]) {
						if (!check) { // 아직 한 개의 빙산 덩어리를 체크하지 않았다면
							bfs(i, j, visited); // 하나의 빙산 덩어리 방문 체크하기
							check = true;
						}
						else { // 두 덩어리 이상인 경우
							break outerLoop;
						}
					}
				}
			}

			// 빙산 녹이기
			melt();

			time++;
		}
		
		System.out.println(time);
	}

	static void bfs(int x, int y, boolean[][] visited) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x,y));
		visited[x][y] = true;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int d=0; d<4; d++) {
				int nx = p.x+dx[d];
				int ny = p.y+dy[d];
				if (isRange(nx, ny) && map[nx][ny] >0 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new Point(nx,ny));
				}
			}
		}	
	}
	
	static void melt() {
		int[][] new_map = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				new_map[i][j] = map[i][j];
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if (map[i][j] > 0) {
					int cnt = 0;
					for(int d = 0; d<4; d++) { // 빙산의 동서남북에 붙어있는 0개수 구하기
						int nx = i+dx[d];
						int ny = j+dy[d];
						if (isRange(nx, ny) && map[nx][ny] == 0) {
							cnt++;
						}
					}
					new_map[i][j] = Math.max(0, map[i][j]-cnt);
					if (new_map[i][j] == 0)	iceCnt--;
				}
			}
		}
		map = new_map;
	}
	
	static boolean isRange(int x, int y) {
		return x>=0 && x<n && y>=0 && y<m;
	}
}