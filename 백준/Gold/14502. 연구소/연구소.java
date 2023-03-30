import java.io.*;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int n, m, map[][], max;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		combination(0);
		
		System.out.println(max);

	}

	static void combination(int cnt) {
		
	
		if (cnt == 3) { // 3개의 벽을 모두 세운 경우
			int[][] copyArr = copyArray(map);
			spread(copyArr);
			return;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					combination(cnt + 1);
					map[i][j] = 0;
				}
			}
		}
	}

	static void spread(int[][] arr) {
		boolean checked[][] = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 2 && !checked[i][j]) {
					checked[i][j] = true;
					dfs(i,j,checked, arr);
				}
			}
		}
		
		max = Math.max(max, getMaxSize(arr));

	}

	static void dfs(int x, int y, boolean checked[][], int[][] arr) {

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (isRange(nx, ny) && map[nx][ny] == 0 && !checked[nx][ny]) {
				arr[nx][ny] = 2; // 빈칸을 바이러스로 전염시킨다.
				checked[nx][ny] = true; // 방문체크
				dfs(nx, ny, checked, arr); // 해당 칸에서 다시 바이러스를 전염시킨다.
			}
		}

	}

	static int[][] copyArray(int[][] arr) {
		int[][] copyArr = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copyArr[i][j] = arr[i][j];
			}
		}
		return copyArr;
	}

	static boolean isRange(int x, int y) {
		return (x >= 0 && x < n && y >= 0 && y < m);
	}

	static int getMaxSize(int arr[][]) {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 0)
					cnt++;
			}
		}
		return cnt;
	}
}
