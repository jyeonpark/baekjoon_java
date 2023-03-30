import java.io.*;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int n, m, map[][], max;
	static boolean visited[][];
	static int dx[] = { 1, 0, -1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		visited = new boolean[n][m];
		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				visited[i][j] = true;
				dfs(1, i, j, map[i][j]);
				visited[i][j] = false;
				checkException(i, j, map[i][j]);
			}
		}
		System.out.println(max);
	}

	static void dfs(int cnt, int x, int y, int sum) {
		if (cnt == 4) {
			max = Math.max(max, sum);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (isRange(nx, ny) && !visited[nx][ny]) {
				visited[nx][ny] = true;
				dfs(cnt + 1, nx, ny, sum + map[nx][ny]);
				visited[nx][ny] = false;
			}
		}
	}

	static void checkException(int x, int y, int sum) {
		// ㅏ ㅓ 모양 검색
		int nx1 = x - 1;
		int nx2 = x + 1;
		int ny1 = y;
		int ny2 = y;
		if (isRange(nx1, ny1) && isRange(nx2, ny2)) {
			int nx3 = x;
			int ny3 = y + 1;
			if (isRange(nx3, ny3)) { // ㅏ 모양
				//System.out.printf("ㅏ모양 : (%d,%d) (%d,%d) (%d,%d) (%d,%d)\n", x,y,nx1,ny1,nx2,ny2,nx3,ny3);
				max = Math.max(max, sum + map[nx1][ny1] + map[nx2][ny2] + map[nx3][ny3]);
			}
			ny3 = y - 1;
			if (isRange(nx3, ny3)) { // ㅓ 모양
				//System.out.printf("ㅓ모양 : (%d,%d) (%d,%d) (%d,%d) (%d,%d)\n", x,y,nx1,ny1,nx2,ny2,nx3,ny3);
				max = Math.max(max, sum + map[nx1][ny1] + map[nx2][ny2] + map[nx3][ny3]);
			}
		}

		// ㅗ ㅜ 모양 검색
		nx1 = x;
		nx2 = x;
		ny1 = y - 1;
		ny2 = y + 1;
		if (isRange(nx1, ny1) && isRange(nx2, ny2)) {
			int nx3 = x + 1;
			int ny3 = y;
			if (isRange(nx3, ny3)) { // ㅜ 모양
				//System.out.printf("ㅜ모양 : (%d,%d) (%d,%d) (%d,%d) (%d,%d)\n", x,y,nx1,ny1,nx2,ny2,nx3,ny3);
				max = Math.max(max, sum + map[nx1][ny1] + map[nx2][ny2] + map[nx3][ny3]);
			}
			nx3 = x - 1;
			if (isRange(nx3, ny3)) { // ㅗ 모양
				//System.out.printf("ㅗ모양 : (%d,%d) (%d,%d) (%d,%d) (%d,%d)\n", x,y,nx1,ny1,nx2,ny2,nx3,ny3);
				max = Math.max(max, sum + map[nx1][ny1] + map[nx2][ny2] + map[nx3][ny3]);
			}
		}
	}

	static boolean isRange(int x, int y) {
		return (x >= 0 && x < n && y >= 0 && y < m);
	}
}
