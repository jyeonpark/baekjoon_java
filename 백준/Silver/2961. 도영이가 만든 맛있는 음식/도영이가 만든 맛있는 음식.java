import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static long min_ans = Long.MAX_VALUE;
	static int[][] food;

	public static void main(String[] args) throws IOException {

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 재료 개수

		StringTokenizer st;
		food = new int[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			food[i][0] = Integer.parseInt(st.nextToken()); // 신맛
			food[i][1] = Integer.parseInt(st.nextToken()); // 쓴맛
		}

		cook(0, 1, 0); // 신맛은 곱하므로 1로 초기화, 쓴맛은 더하므로 0으로 초기화
		System.out.println(min_ans);

	}

	public static void cook(int depth, int sour, int bitter) {
		if (depth == n) {
			if (sour > 0 && bitter > 0) { // 재료를 1개 이상 선택한 경우
				min_ans = Math.min(min_ans, Math.abs(sour - bitter));
				return;
			}
			return;
		}

		// depth번째 재료 선택
		cook(depth + 1, sour * food[depth][0], bitter + food[depth][1]);

		// depth번째 재료 선택X
		cook(depth + 1, sour, bitter);

	}
}
