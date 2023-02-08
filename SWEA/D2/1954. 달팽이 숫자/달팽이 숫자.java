import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static String str;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { 1, 0, -1, 0 };

		for (int t = 1; t <= T; t++) {

			int dir = 0;
			int x = 0;
			int y = 0;
			int nx = -1;
			int ny = -1;

			int n = Integer.parseInt(br.readLine());
			int[][] arr = new int[n][n];

			for (int i = 0; i < n * n; i++) {
				arr[x][y] = i + 1;

				nx = x + dx[dir];
				ny = y + dy[dir];

				if (!(nx >= 0 && nx < n && ny >= 0 && ny < n && arr[nx][ny] == 0)) {
					dir = (dir + 1) % 4;
				}

				x += dx[dir];
				y += dy[dir];

			}

			System.out.println("#" + t);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++)
					System.out.print(arr[i][j] + " ");
				System.out.println();
			}
		}
	}

}
