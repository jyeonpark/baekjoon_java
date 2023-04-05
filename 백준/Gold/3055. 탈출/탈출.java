import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int r, c;
	static int result = Integer.MAX_VALUE;
	static char[][] arr;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		arr = new char[r][c];

		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 0; j < c; j++) {
				arr[i][j] = s.charAt(j);
			}
		}

		bfs();

		if (result == Integer.MAX_VALUE)
			System.out.println("KAKTUS");
		else
			System.out.println(result);
	}

	static void bfs() {
		Queue<int[]> s = new LinkedList<>();
		Queue<int[]> w = new LinkedList<>();
		boolean[][] visited = new boolean[r][c];

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (arr[i][j] == 'S') {
					s.add(new int[] { i, j, 0 });
					arr[i][j] = '.';
					visited[i][j] = true;
				}

				if (arr[i][j] == '*') {
					w.add(new int[] { i, j });
				}
			}
		}

		while (!s.isEmpty()) {
			int water_size = w.size();
			for (int k = 0; k < water_size; k++) {
				int[] W = w.poll();
				for (int i = 0; i < 4; i++) {
					int wx = dx[i] + W[0];
					int wy = dy[i] + W[1];

					if (wx >= 0 && wx < r && wy >= 0 && wy < c) {
						if (arr[wx][wy] == '.') {
							arr[wx][wy] = '*';
							w.add(new int[] { wx, wy });
						}
					}
				}
			}

			int move_size = s.size();
			for (int k = 0; k < move_size; k++) {
				int[] S = s.poll();
				for (int i = 0; i < 4; i++) {
					int sx = dx[i] + S[0];
					int sy = dy[i] + S[1];
					int cnt = S[2];

					if (sx >= 0 && sx < r && sy >= 0 && sy < c && !visited[sx][sy]) {
						if (arr[sx][sy] == '.') {
							s.add(new int[] { sx, sy, cnt + 1 });
							visited[sx][sy] = true;
						}

						if (arr[sx][sy] == 'D') {
							result = cnt + 1;
							return;
						}
					}
				}
			}
		}

	}

}