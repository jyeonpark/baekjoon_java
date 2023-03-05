import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;
	static int T, n, m, c, map[][], max_honey[], max_total_honey;
	static boolean[][] visited;
	static ArrayList<int[]> hives;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			max_honey = new int[2];
			visited = new boolean[n][n];
			hives = new ArrayList<>();
			for (int i = 0; i < 2; i++) {
				hives.add(new int[m]);
			}
			max_total_honey = 0;
			combination(0);
			sb.append("#").append(t).append(" ").append(max_total_honey).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void combination(int cnt) {
		if (cnt == 2) {
			for (int i = 0; i < 2; i++) {
				boolean[] choose = new boolean[m];
				max_honey[i] = 0;
				subset(i, 0, choose);
			}
			max_total_honey = Math.max(max_total_honey, max_honey[0]+max_honey[1]);
			return;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - m + 1; j++) {
				boolean check = false;
				for (int k = 0; k < m; k++) {
					if (visited[i][j + k]) {
						check = true;
						break;
					}
				}
				if (!check) {
					for (int k = 0; k < m; k++) {
						visited[i][j + k] = true;
						hives.get(cnt)[k] = map[i][j + k]; // cnt번째 일꾼이 선택한 벌꿀 저장
					}
					combination(cnt + 1);
					for (int k = 0; k < m; k++) {
						visited[i][j + k] = false;
					}
				}
			}
		}
	}

	static void subset(int i, int cnt, boolean[] choose) {

		if (cnt == m) { // m개의 벌꿀에 대한 선택 완료
			int total = 0;
			for (int j = 0; j < m; j++) {
				if (choose[j]) {
					total += hives.get(i)[j];
				}
			}
			if (total > c) { // 제한 벌꿀 양을 초과한다면
				return;
			}
			int double_total = 0;
			for (int j = 0; j < m; j++) {
				if (choose[j])
					double_total += hives.get(i)[j] * hives.get(i)[j];
			}

			max_honey[i] = Math.max(max_honey[i], double_total);
			return;
		}

		choose[cnt] = true;
		subset(i, cnt + 1, choose);

		choose[cnt] = false;
		subset(i, cnt + 1, choose);

	}
}