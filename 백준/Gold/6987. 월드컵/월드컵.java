import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	/**
	 * 기자가 보내온 각 나라의 승,무,패 결과 scores[i][j] -> i는 0~5까지이며 각각 "A~F" 를 의미한다. -> j는
	 * 0,1,2로 각각 "승, 무, 패" 를 의미한다.
	 */
	static int[][] scores = new int[6][3];
	static int[] win;
	static int[] draw;
	static int[] lose;
	static int isPossible;
	static int[] aTeam = { 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4 };
	static int[] bTeam = { 1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int tc = 0; tc < 4; tc++) {
			st = new StringTokenizer(br.readLine());
			isPossible = 1;
			for (int i = 0; i < 6; i++) {
				scores[i][0] = Integer.parseInt(st.nextToken()); // 승
				scores[i][1] = Integer.parseInt(st.nextToken()); // 무
				scores[i][2] = Integer.parseInt(st.nextToken()); // 패

				if (scores[i][0] + scores[i][1] + scores[i][2] != 5) {
					isPossible = 0;
					break;
				}
			}

			if (isPossible == 1) {

				win = new int[6];
				draw = new int[6];
				lose = new int[6];
				isPossible = 0;

				match(0);
			}
			sb.append(isPossible).append(" ");
		}
		System.out.println(sb.toString());
	}

	static void match(int cnt) {
		if (cnt == 15) { // 모든 나라에 대해 승부 판별 완료 => 가능한 결과
			isPossible = 1;
			return;
		}
		if (isPossible == 0) { // 아직 판별이 안된경우에만 계속해서 탐색하기
			// start가 이길 경우
			if (win[aTeam[cnt]] + 1 <= scores[aTeam[cnt]][0] && lose[bTeam[cnt]] + 1 <= scores[bTeam[cnt]][2]) {
				win[aTeam[cnt]]++;
				lose[bTeam[cnt]]++;
				match(cnt + 1);
				win[aTeam[cnt]]--;
				lose[bTeam[cnt]]--;
			}
			// 무승부
			if (draw[aTeam[cnt]] + 1 <= scores[aTeam[cnt]][1] && draw[bTeam[cnt]] + 1 <= scores[bTeam[cnt]][1]) {
				draw[aTeam[cnt]]++;
				draw[bTeam[cnt]]++;
				match(cnt + 1);
				draw[aTeam[cnt]]--;
				draw[bTeam[cnt]]--;
			}
			// start가 질 경우
			if (lose[aTeam[cnt]] + 1 <= scores[aTeam[cnt]][2] && win[bTeam[cnt]] + 1 <= scores[bTeam[cnt]][0]) {
				win[bTeam[cnt]]++;
				lose[aTeam[cnt]]++;
				match(cnt + 1);
				win[bTeam[cnt]]--;
				lose[aTeam[cnt]]--;
			}
		}
	}

}