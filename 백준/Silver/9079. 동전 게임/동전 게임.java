import java.awt.Point;
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
	static int[][] map;
	static int min_cnt;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			map = new int[3][3];
			for (int i = 0; i < 3; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 3; j++) {
					map[i][j] = st.nextToken().charAt(0) == 'H' ? 1 : 0;
				}
			}
			min_cnt = Integer.MAX_VALUE;
			bfs();
			sb.append(min_cnt == Integer.MAX_VALUE ? -1 : min_cnt).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bfs() {

		Queue<int[]> q = new ArrayDeque<int[]>(); // {배열방문표시, cnt}
		boolean[] visited = new boolean[513];
		int firstNum = arrayToInt(map);
		q.add(new int[] { firstNum, 0 });
		visited[firstNum] = true;

		while (!q.isEmpty()) {

			int arrInt = q.peek()[0];
			int cnt = q.poll()[1];
			int[][] arr = intToArray(arrInt);

			if (isRightArray(arr)) {
				min_cnt = cnt;
				break;
			}

			// 행 뒤집기
			for (int i = 0; i < 3; i++) {
				int[][] tmp = copyArray(arr);
				reverseRowArray(i, tmp);
				// 이미 만든적 있던 모양이라면
				int num = arrayToInt(tmp);
				if (visited[num])
					continue;
				visited[num] = true;
				q.add(new int[] { num, cnt + 1 });

			}

			// 열 뒤집기
			for (int i = 0; i < 3; i++) {
				int[][] tmp = copyArray(arr);
				reverseColArray(i, tmp);
				int num = arrayToInt(tmp);
				if (visited[num])
					continue;
				visited[num] = true;
				q.add(new int[] { num, cnt + 1 });

			}

			// 대각선 뒤집기
			for (int i = 1; i < 3; i++) {
				int[][] tmp = copyArray(arr);
				reverseDiagnol(i, tmp);
				int num = arrayToInt(tmp);
				if (visited[num])
					continue;
				visited[num] = true;
				q.add(new int[] { num, cnt + 1 });
			}
		}

	}

	static void reverseRowArray(int row, int[][] arr) {
		for (int i = 0; i < 3; i++)
			arr[row][i] = (1 - arr[row][i]);
	}

	static void reverseColArray(int col, int[][] arr) {
		for (int i = 0; i < 3; i++)
			arr[i][col] = (1 - arr[i][col]);
	}

	static void reverseDiagnol(int num, int[][] arr) {
		if (num == 1) {
			for (int i = 0; i < 3; i++)
				arr[i][2 - i] = (1 - arr[i][2 - i]);
		}
		if (num == 2) {
			for (int i = 0; i < 3; i++)
				arr[i][i] = (1 - arr[i][i]);
		}
	}

	static int arrayToInt(int[][] arr) { // 현재 배열을 int 로 변환하여 방문 처리 한다.
		int num = 0;
		int multi = 1;
		for (int i = 2; i >= 0; i--) {
			for (int j = 2; j >= 0; j--) {
				num += arr[i][j] * multi;
				multi *= 2;
			}
		}
		return num;
	}

	static int[][] intToArray(int num) {
		int[][] arr = new int[3][3];
		for (int i = 2; i >= 0; i--) {
			for (int j = 2; j >= 0; j--) {
				arr[i][j] = num % 2;
				num /= 2;
			}
		}
		return arr;
	}

	static boolean isRightArray(int[][] arr) {
		int total = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				total += arr[i][j];
			}
		}
		return (total == 0 || total == 9 ? true : false);
	}

	static int[][] copyArray(int[][] arr) {
		int[][] tmp = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tmp[i][j] = arr[i][j];
			}
		}
		return tmp;
	}

	static void print(int[][] arr) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

}