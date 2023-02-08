import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 스위치 개수
		int[] state = new int[n + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			state[i] = Integer.parseInt(st.nextToken());
		}

		int m = Integer.parseInt(br.readLine()); // 학생수
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken()); // 성별
			int num = Integer.parseInt(st.nextToken()); // 받은 번호

			if (gender == 1) { // 남자
				for (int j = num; j <= n; j += num) { // 받은 수의 배수 번호에 있는 스위치 상태 바꾸기
					state[j] = (1 - state[j]);
				}

			} else { // 여자
				state[num] = (1 - state[num]);
				for (int j = 1; j <= n; j++) {
					if (num - j > 0 && num + j <= n) { // 범위 안
						if (state[num - j] == state[num + j]) { // 좌우 대칭이라면
							state[num - j] = (1 - state[num - j]); // 상태 변경
							state[num + j] = (1 - state[num + j]); // 상태 변경
						} else
							break;
					} else
						break;
				}
			}
		}
		for (int i = 1; i <= n; i++) {
			System.out.print(state[i] + " ");
			if (i%20==0)
				System.out.println();
		}

	}
}
