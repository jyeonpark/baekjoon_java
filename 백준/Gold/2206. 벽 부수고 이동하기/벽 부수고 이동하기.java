import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static int n, m, arr[][];
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}
		System.out.println(bfs());
	}

	static int bfs() {
		Queue<int[]> q = new ArrayDeque<int[]>();
		// visited[i][j][0] : 벽을 부수지 않고 (i,j)에 도달 visited[i][j][1] : 벽을 한 번 부수고 (i,j) 에 도달
		boolean[][][] visited = new boolean[n][m][2];
		q.add(new int[] { 0, 0, 1, 0 }); // x,y,이동횟수,{벽안부쉈으면0,벽부쉈으면1}
		visited[0][0][0] = true;

		while (!q.isEmpty()) {
			int[] info = q.poll();
			int x = info[0];
			int y = info[1];
			int cnt = info[2];
			int broken = info[3];
			
			if (info[0] == n - 1 && info[1] == m - 1)
				return info[2];

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
					if (broken == 0 && !visited[nx][ny][0]) { // 아직 벽을 안부쉈다면
						if (arr[nx][ny] == 0) {
							visited[nx][ny][0] = true;
							q.add(new int[] { nx, ny, cnt+1, 0 });
						} else { // 벽이라면
							visited[nx][ny][1] = true;
							q.add(new int[] { nx, ny, cnt+1, 1 });
						}
					}
					if (broken == 1 && !visited[nx][ny][1]) { // 이미 벽을 부쉈다면
						if (arr[nx][ny] == 0) { // 벽이 아닌 경우에만 탐색 가능
							visited[nx][ny][1] = true;
							q.add(new int[] { nx, ny, cnt+1, 1 });
						}
					}
				}
			}
		}

		return -1;
	}

}