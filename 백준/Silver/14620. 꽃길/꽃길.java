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
	static int n, min_price = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 0);
		sb.append(min_price);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int cnt, int price) {

		if (cnt == 3) {
			min_price = Math.min(min_price, price);
			return;
		}

		for (int i = 1; i < n - 1; i++) {
			for (int j = 1; j < n - 1; j++) {
				if (!visited[i][j] && !visited[i - 1][j] && !visited[i + 1][j] && !visited[i][j - 1]
						&& !visited[i][j + 1]) {
					visited[i][j] = true;
					visited[i - 1][j] = true;
					visited[i + 1][j] = true;
					visited[i][j - 1] = true;
					visited[i][j + 1] = true;
					dfs(cnt + 1, 
							price + map[i][j] + map[i - 1][j] + map[i + 1][j] + map[i][j - 1] + map[i][j + 1]);
					visited[i][j] = false;
					visited[i - 1][j] = false;
					visited[i + 1][j] = false;
					visited[i][j - 1] = false;
					visited[i][j + 1] = false;
				}
			}
		}
	}

}