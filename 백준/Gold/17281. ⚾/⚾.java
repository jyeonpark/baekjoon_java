import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int n, scores[][], maxScore, order[];
	static boolean visited[];

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		scores = new int[n][9];
		order = new int[9];
		order[3] = 0;
		visited = new boolean[9];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++)
				scores[i][j] = Integer.parseInt(st.nextToken());
		}
		permutation(0);
		System.out.println(maxScore);
	}

	static void permutation(int cnt) {
		if (cnt == 9) {
			run();
			return;
		}
		if (cnt == 3) { // 4번째 타석은 이미 정해짐
			permutation(cnt + 1);
		} else {
			for (int i = 1; i < 9; i++) {
				if (visited[i])
					continue;
				order[cnt] = i;
				visited[i] = true;
				permutation(cnt + 1);
				visited[i] = false;
			}
		}
	}

	static void run() {

		int totalScore = 0;
		int player = 0; // 현재 타석에 있는 타자 인덱스

		for (int i = 0; i < n; i++) { // n이닝

			int out = 0; // 아웃 개수
			int base = 0;

			while (out < 3) { // 3아웃이 발생할 때까지
				int hit = scores[i][order[player]];
				if (hit == 0) {
					out++;
				} else {
					base = base << hit; // 주자 이동
					base += 1<<(hit-1); // 타자 이동
					if (base >= 1<<3) { // 홈에 도착한 선수들이 있다면
						totalScore += Integer.bitCount(base>>3); // 홈에 들어온 선수 명 수 세기
						base %= 1<<3; // 홈 이상으로 있는 비트 제거
					}
				}
				player = (player + 1) % 9;
			}
		}

		maxScore = Math.max(maxScore, totalScore);
	}

}