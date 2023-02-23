import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	static StringBuilder sb;
	static int n, m, v;
	static boolean[] visited;
	static ArrayList<Integer>[] list;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		list = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++)
			list[i] = new ArrayList<Integer>();
		visited = new boolean[n + 1];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);
			list[to].add(from);
		}

		for (int i = 1; i <= n; i++) {
			Collections.sort(list[i]);
		}

		visited[v] = true;
		dfs(v);

		sb.append("\n");
		Arrays.fill(visited, false);
		bfs();

		System.out.println(sb.toString());
	}

	static void dfs(int v) {
		sb.append(v).append(" ");
		for (int i : list[v]) { // node[v] 와 인접한 노드들 탐색
			if (visited[i])
				continue; // 이미 방문한 노드
			visited[i] = true;
			dfs(i);
		}
	}

	static void bfs() {
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(v);
		visited[v] = true;

		while (!q.isEmpty()) {
			int node = q.poll();
			sb.append(node).append(" ");
			for (int i : list[node]) { // node[v] 와 인접한 노드들 탐색
				if (visited[i])
					continue; // 이미 방문한 노드
				visited[i] = true;
				q.add(i);
			}
		}
	}

}