import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int n, m, chicken[], min_time = Integer.MAX_VALUE, buildingA, buildingB;
	static int[][] dist;
	static final int INF = 1000;
	static ArrayList<Integer>[] road;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		chicken = new int[2];
		dist = new int[n + 1][n + 1];
		road = new ArrayList[n + 1];

		for (int i = 0; i < n + 1; i++)
			road[i] = new ArrayList<Integer>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			road[a].add(b);
			road[b].add(a);
		}

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == j)
					continue;
				else
					dist[i][j] = dist[j][i] = INF;
			}
		}

		for (int i = 1; i <= n; i++) { // 인접 노드
			for (int r : road[i])
				dist[i][r] = dist[r][i] = 1;
		}

		for (int i = 1; i <= n; i++) { // 플로이드와샬
			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= n; k++) {
					if (dist[i][j] > dist[i][k] + dist[k][j])
						dist[i][j] = dist[j][i] = dist[i][k] + dist[k][j];
				}
			}
		}

//		for (int j = 1; j <= n; j++) {
//			for (int k = 1; k <= n; k++) {
//				System.out.print(dist[j][k] + " ");
//			}
//			System.out.println();
//		}

		combination(0, 1);
		sb.append(buildingA).append(" ").append(buildingB).append(" ").append(min_time);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void combination(int cnt, int start) {

		if (cnt == 2) { // 치킨집 2개 선택 완료

			int totalTime = getTurnAroundTime();
			if (totalTime <= min_time) {
				min_time = totalTime;
				buildingA = chicken[0];
				buildingB = chicken[1];
			} 
			return;
		}

		for (int i = start; i <= n; i++) {
			chicken[cnt] = i;
			combination(cnt + 1, i + 1);
		}

	}

	static int getTurnAroundTime() {
		int time = 0;
		for (int i = 1; i <= n; i++) {
			time += Math.min(dist[i][chicken[0]], dist[i][chicken[1]]); // 더 가까운 치킨집 고르기
		}
		return time * 2; // 왕복시간 리턴
	}

}