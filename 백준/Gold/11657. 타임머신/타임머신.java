import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;
	static int n, m, a, b, c;
	static long dist[];
	static ArrayList<Edge> edges;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		edges = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			edges.add(new Edge(a, b, c));
		}

		dist = new long[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;
		boolean cycle = false;
		for (int i = 1; i <= n; i++) {
			for (Edge edge : edges) {
				if (dist[edge.u] != Integer.MAX_VALUE && dist[edge.v] > dist[edge.u] + edge.w) {
					dist[edge.v] = dist[edge.u] + edge.w;
					if (i == n) {
						cycle = true;
						break;
					}
				}
			}
		}
		if (cycle)
			System.out.println(-1);
		else {
			for (int i = 2; i <= n; i++) {
				if (dist[i] == Integer.MAX_VALUE)	
					System.out.println(-1);
				else	
					System.out.println(dist[i]);
			}
		}

	}

	static class Edge {
		int u;
		int v;
		int w;

		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

	}
}