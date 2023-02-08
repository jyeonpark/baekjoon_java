import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		boolean[] visited = new boolean[n + 1];

		combinations(visited, 1, m);

	}

	static void combinations(boolean[] visited, int depth, int num) {
		if (num == 0) { // m개를 모두 뽑았을 경우
			print(visited);
			return;
		}
		if (depth > n) { // 1~n까지 모두 뽑기를 완료한 경우
			return;
		}
		visited[depth] = true;
		combinations(visited, depth+1, num-1);
		
		visited[depth] = false;
		combinations(visited, depth+1, num);

	}

	static void print(boolean[] visited) {
		for(int i=1; i<=n; i++) {
			if (visited[i])
				System.out.print(i + " ");
		}
		System.out.println();
	}
}
