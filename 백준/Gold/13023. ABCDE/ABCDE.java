import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static StringBuilder sb;
	static StringTokenizer st;
	static ArrayList<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		list = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++)
			list[i] = new ArrayList<Integer>();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}

		int check = 0;
		for (int i = 0; i <= n; i++) {
			if (dfs(i, 0, 1 << i)) {
				check = 1;
				break;
			}
				
		}
		sb.append(check);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static boolean dfs(int node, int cnt, int flag) {
		if (cnt == 4) {
			return true;
		}

		for (int friend : list[node]) {
			if ((flag & (1 << friend)) != 0)
				continue;
			if (dfs(friend, cnt + 1, flag | (1 << friend)))
				return true;
		}

		return false;
	}
}