import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	static StringBuilder sb;
	static int n, m, v;
	static boolean[] visited;
	static TreeMap<Integer, PriorityQueue<Integer>> dfs_graph = new TreeMap<>();
	static TreeMap<Integer, PriorityQueue<Integer>> bfs_graph = new TreeMap<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());

		visited = new boolean[n + 1];
		for (int i = 1; i <= n; i++) {
			dfs_graph.put(i, new PriorityQueue<Integer>());
			bfs_graph.put(i, new PriorityQueue<Integer>());
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			dfs_graph.get(from).add(to);
			dfs_graph.get(to).add(from);
			bfs_graph.get(from).add(to);
			bfs_graph.get(to).add(from);
			
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
		while (dfs_graph.get(v).size() > 0) { // node[v] 와 인접한 노드들 탐색
			int num = dfs_graph.get(v).poll(); // 정점 번호가 가장 작은 것 먼저 방문
			if (visited[num])
				continue; // 이미 방문한 노드
			visited[num] = true;
			dfs(num);
		}
	}

	static void bfs() {
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(v);
		visited[v] = true;

		while (!q.isEmpty()) {
			int node = q.poll();
			sb.append(node).append(" ");
			while (bfs_graph.get(node).size() > 0) { // node[v] 와 인접한 노드들 탐색
				int num = bfs_graph.get(node).poll(); // 정점 번호가 가장 작은 것 먼저 방문
				if (visited[num])
					continue; // 이미 방문한 노드
				visited[num] = true;
				q.add(num);
			}
		}
	}

}