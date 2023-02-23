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
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int r, c, max_cnt;
	static char[][] map;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		for (int i = 0; i < r; i++)
			map[i] = br.readLine().toCharArray();

		dfs(0, 0, 1, 1 << map[0][0]);
		sb.append(max_cnt);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int x, int y, int cnt, int flag) {
		if (cnt > max_cnt) {
			max_cnt = cnt;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
			if ((flag & (1<<map[nx][ny])) != 0) continue;
			dfs(nx,ny, cnt+1, flag | (1<<map[nx][ny]));
		}
	}

}