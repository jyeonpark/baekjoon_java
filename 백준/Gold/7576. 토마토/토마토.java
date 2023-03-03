import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int m, n, map[][];
	static Queue<Point> q;
	static int ripeTomatoes, totalTomatoes, time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		q = new ArrayDeque<>();
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != -1) totalTomatoes++;
				if (map[i][j] == 1) { // 익은 토마토 저장
					q.add(new Point(i, j));
				}
			}
		}
		if (q.size() == totalTomatoes)
			System.out.println(0);
		else if (q.size() == 0)
			System.out.println(-1);
		else {
			ripeTomatoes = q.size();
			bfs();
			if (ripeTomatoes != totalTomatoes)
				System.out.println(-1);
			else
				System.out.println(time);
		}
	}

	static void bfs() {
		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };
		while (true) { // q : 익은 토마토가 들어있다.
			if (ripeTomatoes == totalTomatoes)
				break;
			int size = q.size();
			if (size ==0)	break;
			for(int i=0; i<size; i++) {
				Point p = q.poll();
				for (int j = 0; j < 4; j++) {
					int nx = p.x + dx[j];
					int ny = p.y + dy[j];
					if (nx < 0 || nx >= n || ny < 0 || ny >= m)
						continue;
					if (map[nx][ny] == 0) {
						map[nx][ny] = 1;
						q.add(new Point(nx, ny));
						ripeTomatoes++;
					}
				}
			}
			time++;
			
		}
	}
}