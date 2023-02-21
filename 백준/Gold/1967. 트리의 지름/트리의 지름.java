import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int num;
		int weight;

		public Node(int num, int weight) {
			this.num = num;
			this.weight = weight;
		}
	}

	static int n, max_cost, max_node;
	static ArrayList<Node>[] tree;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		tree = new ArrayList[n + 1];
		visited = new boolean[n + 1];
		for (int i = 0; i < n + 1; i++) {
			tree[i] = new ArrayList<Node>(); // tree[i] 의 자식 노드들을 저장
		}

		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			tree[parent].add(new Node(child, weight));
			tree[child].add(new Node(parent, weight));
		}

		// 트리지름의 끝 노드 찾기
		visited[1] = true;
		dfs(1, 0);

		// 트리지름의 끝 노드을 시작노드로 하여 반대쪽 끝 노드 찾기
		max_cost = 0;
		Arrays.fill(visited, false);
		visited[max_node] = true;
		dfs(max_node, 0);
		System.out.println(max_cost);
	}

	public static void dfs(int idx, int cost) { // i에서 가장 멀리 있는 노드 찾기

		if (cost > max_cost) {
			max_cost = cost;
			max_node = idx;
		}

		for (int i = 0; i < tree[idx].size(); i++) {
			Node node = tree[idx].get(i); // idx 의 i번째 자식노드
			int num = node.num;
			if (!visited[num]) {
				int weight = node.weight;
				visited[num] = true;
				dfs(num, cost + weight);
			}
		}
	}
}