import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;
	static int n, durability[], weight[], max_cnt;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		durability = new int[n];
		weight = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			durability[i] = Integer.parseInt(st.nextToken());
			weight[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0, 0);

		System.out.println(max_cnt);

	}

	/**
	 * 
	 * @param now : 손에 든 계란 idx
	 * @param cnt : 지금까지 깬 꼐란
	 */
	static void dfs(int now, int cnt) {

		if (now == n) {
			max_cnt = Math.max(max_cnt, cnt);
			return;
		}
		
		if (cnt == n - 1 || durability[now] <= 0) {
			dfs(now + 1, cnt);
			return;
		}
		
		int tmp = cnt;
		for (int i = 0; i < n; i++) {
			if (now == i)
				continue;
			if (durability[i] <= 0)
				continue;

			durability[now] -= weight[i];
			durability[i] -= weight[now];

			if (durability[now] <= 0)
				cnt++;
			if (durability[i] <= 0)
				cnt++;

			dfs(now + 1, cnt);

			durability[now] += weight[i];
			durability[i] += weight[now];

			cnt = tmp;
		}
	}

}