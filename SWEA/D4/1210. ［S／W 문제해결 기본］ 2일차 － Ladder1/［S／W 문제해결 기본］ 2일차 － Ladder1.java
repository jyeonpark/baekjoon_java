import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static String str;

	static int[] dx = { -1, 1, 0 };
	static int[] dy = { 0, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= 10; t++) {
			Integer.parseInt(br.readLine());
			int[][] arr = new int[100][100];
			int arrive_x = -1;
			int arrive_y = -1;

			StringTokenizer st;
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] == 2) { // 도착지라면
						arrive_x = j;
						arrive_y = i;
					}
				}
			}
	
            // 도착지에서 위로 올라가며 출발지 찾기
			int answer_x = move(arr, arrive_x, arrive_y);

			System.out.printf("#%d %d\n", t, answer_x);

		}

	}

	public static int move(int[][] arr, int x, int y) {
		while (true) {
			if (y == 0) // 출발지 찾음
				break;
			for (int i = 0; i < 3; i++) { // 좌,우,위 순서로 탐색
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx >= 0 && nx < 100 && ny >= 0 && arr[ny][nx] == 1) { // 범위 안 && 길이 있다면 (1)
					x = nx;
					y = ny;
					arr[y][x] = 0; // 중복해서 탐색하지 못하도록 0으로 설정
//					System.out.println("X : " + x + " Y : " + y);
					break;
				}
			}
		}
		return x;
	}

}
