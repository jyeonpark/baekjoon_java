import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb;
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static final int n = 19;
	static int[][] map;
	static int first_row = -1;
	static int first_col = -1;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = run();
		sb.append(answer);
		if (answer != 0)
			sb.append("\n").append(first_row).append(" ").append(first_col);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int run() {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] > 0) {
					if (i + 4 < n && rowCheck(i, j) == true) {
						first_row = i + 1;
						first_col = j + 1;
						return map[i][j];
					}
					if (j + 4 < n && colCheck(i, j) == true) {
						first_row = i + 1;
						first_col = j + 1;
						return map[i][j];
					}
					if (i + 4 < n && j + 4 < n && firstDiagonalCheck(i, j) == true) {
						first_row = i + 1;
						first_col = j + 1;
						return map[i][j];
					}
					if (i + 4 < n && j - 4 >= 0 && secondDiagonalCheck(i, j) == true) {
						first_row = i + 5;
						first_col = j -4 + 1;
						return map[i][j];
					}
				}
			}
		}
		return 0;
	}

	static boolean rowCheck(int x, int y) {
		int num = map[x][y]; // 바둑알 색

		// 이전 바둑알과 연속되면 안된다.
		if (x > 0 && map[x - 1][y] == num)
			return false;

		for (int i = 1; i < 6; i++) {
			if (i == 5) { // 6번째 바둑알은 연속되면 안된다.
				if (x + i >= n || map[x + i][y] != num) {
					return true;
				}
			} else if (map[x + i][y] != num)
				return false;
		}
		return false;
	}

	static boolean colCheck(int x, int y) {
		int num = map[x][y]; // 바둑알 색

		// 이전 바둑알과 연속되면 안된다.
		if (y > 0 && map[x][y - 1] == num)
			return false;

		for (int i = 1; i < 6; i++) {
			if (i == 5) { // 6번째 바둑알은 연속되면 안된다.
				if (y + i >= n || map[x][y + i] != num)
					return true;
			} else if (map[x][y + i] != num)
				return false;
		}
		return false;
	}

	static boolean firstDiagonalCheck(int x, int y) {
		int num = map[x][y]; // 바둑알 색
		// 이전 바둑알과 연속되면 안된다.
		if (x > 0 && y > 0 && map[x - 1][y - 1] == num)
			return false;

		for (int i = 1; i < 6; i++) {
			if (i == 5) { // 6번째 바둑알은 연속되면 안된다.
				if (x + i >= n || y + i >= n || map[x + i][y + i] != num)
					return true;
			} else if (map[x + i][y + i] != num)
				return false;
		}
		return false;
	}

	static boolean secondDiagonalCheck(int x, int y) {
		int num = map[x][y]; // 바둑알 색

		// 이전 바둑알과 연속되면 안된다.
		if (x > 0 && y <n-1 && map[x - 1][y + 1] == num)
			return false;

		for (int i = 1; i < 6; i++) {
			if (i == 5) { // 6번째 바둑알은 연속되면 안된다.
				if (x + i >= n || y - i < 0 || map[x + i][y - i] != num)
					return true;
			} else if (map[x + i][y - i] != num)
				return false;
		}
		return false;
	}

}