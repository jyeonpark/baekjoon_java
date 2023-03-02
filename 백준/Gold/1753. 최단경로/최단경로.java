import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Node implements Comparable<Node>{
		int num;
		int weight;

		public Node(int num, int weight) {
			super();
			this.num = num;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight; // 가중치 기준 오름차순 정렬
		}
	}

	static int V, E, K;
	static ArrayList<Node>[] graph;
	static boolean[] visited;
	static int[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());

		graph = new ArrayList[V + 1];
		for (int i = 0; i <= V; i++)
			graph[i] = new ArrayList<Node>();

		visited = new boolean[V + 1];
		dist = new int[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		int u, v, w;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			graph[u].add(new Node(v, w));
		}
		
		solution();
		
		for(int i=1; i<=V; i++)
			System.out.println(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]);
	}

	static void solution() {
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		q.add(new Node(K, 0)); // 시작 노드
		dist[K] = 0; 
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			if (visited[node.num])	continue;
			visited[node.num] = true;
			
			for(Node next : graph[node.num]) {
				// start~next 가 start~현재노드 + 현재노드~next노드 보다 크다면
				if (dist[next.num] > dist[node.num] + next.weight) {
					dist[next.num] = dist[node.num] + next.weight;
					q.add(new Node(next.num, dist[next.num]));
				}
			}
		}
	}

}