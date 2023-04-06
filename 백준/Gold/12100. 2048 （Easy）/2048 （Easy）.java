import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n, map[][], order[], max;
	static boolean combined[][];
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		order = new int[5];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		permutation(0);
		System.out.println(max);

	}

	static void permutation(int cnt) { // 이동 방향을 정하는 순열
		if (cnt == 5) {
			int[][] tmp = copyArray();
			run(tmp);
			max = Math.max(max, getMaxNum(tmp));
			return;
		}

		for (int i = 0; i < 4; i++) {
			order[cnt] = i;
			permutation(cnt + 1);
		}
	}

	static int[][] copyArray() {
		int[][] tmp = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				tmp[i][j] = map[i][j];
		}
		return tmp;
	}

	static void run(int[][] arr) {
		for (int i = 0; i < 5; i++) { // 5번 이동시킨다
			combined = new boolean[n][n]; // 한 번의 이동에서 이미 합쳐진 블록은 또 다른 블록과 다시 합쳐질 수 없다.
			move(order[i], arr);
		}
	}

	static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static void move(int dir, int[][] arr) { // dir 방향으로 전체 블록을 옮긴다.
		if (dir == 0) { // 왼쪽으로 움직이기
			for (int i = 0; i < n; i++) {
				for (int j = 1; j < n; j++) {
					moveDetail(i, j, dir, arr);
				}
			}
		} else if (dir == 1) { // 오른쪽
			for (int i = 0; i < n; i++) {
				for (int j = n - 2; j >= 0; j--) {
					moveDetail(i, j, dir, arr);
				}
			}
		} else if (dir == 2) { // 위쪽
			for (int i = 0; i < n; i++) {
				for (int j = 1; j < n; j++) {
					moveDetail(j, i, dir, arr);
				}
			}
		} else if (dir == 3) { // 아래쪽
			for (int i = 0; i < n; i++) {
				for (int j = n - 2; j >= 0; j--) {
					moveDetail(j, i, dir, arr);
				}
			}
		}
	}

	static void moveDetail(int i, int j, int dir, int[][] map) {
		int nx, ny, num;
		num = map[i][j];
		map[i][j] = 0;
		nx = i;
		ny = j;
		// 빈칸이면 계속 이동
		while (isRange(nx + dx[dir], ny + dy[dir]) && map[nx + dx[dir]][ny + dy[dir]] == 0) {
			nx += dx[dir];
			ny += dy[dir];
		}
		// 합친다
		if (isRange(nx + dx[dir], ny + dy[dir]) && map[nx + dx[dir]][ny + dy[dir]] == num
				&& !combined[nx + dx[dir]][ny + dy[dir]]) {
			combined[nx + dx[dir]][ny + dy[dir]] = true;
			map[nx + dx[dir]][ny + dy[dir]] = num * 2;
		} else { // 다른 숫자이거나 벽이라면
			map[nx][ny] = num;
		}
	}

	static int getMaxNum(int[][] map) {
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				max = Math.max(max, map[i][j]);
			}
		}
		return max;
	}

	static boolean isRange(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}

}