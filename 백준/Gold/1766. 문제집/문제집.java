import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;
	static int n, m, degree[];
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		degree = new int[n + 1];
		graph = new ArrayList<>();
		for(int i=0; i<=n; i++) 
			graph.add(new ArrayList<Integer>());
		int a, b;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			degree[b] += 1;
		}
		solution();
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void solution() {
		PriorityQueue<Integer> q = new PriorityQueue<>(); // 문제번호가 작은 순서대로 출력되도록 우선순위큐 사용
		for (int i = 1; i <= n; i++) {
			if (degree[i] == 0)
				q.offer(i);
		}
		while (!q.isEmpty()) {
			int num = q.poll();
			sb.append(num).append(" ");
			for(int next : graph.get(num)) { // num 다음에 풀어야 할 문제
				degree[next] -= 1; // num을 풀었으므로 진입차수 1 감소
				if (degree[next] ==0) // 0이라면 큐에 넣기
					q.offer(next);
			}
		}
	}
}