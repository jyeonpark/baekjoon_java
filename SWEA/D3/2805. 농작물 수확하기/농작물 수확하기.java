import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static String str;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			int n = Integer.parseInt(br.readLine());
			int[][] arr = new int[n][n];
			for (int i = 0; i < n; i++) {
				String str = br.readLine();
				for (int j = 0; j < n; j++) {
					arr[i][j] = str.charAt(j) - '0';
				}
			}

			int revenue = 0;
			int half = n / 2;

			for (int i = 0; i < n; i++) {
				if (i<=half) {
					for(int j=half-i; j<n-(half-i); j++)
						revenue += arr[i][j];
				}else {
					for(int j=i-half; j<n-(i-half); j++)
						revenue += arr[i][j];
				}
			}

			System.out.printf("#%d %d\n", t, revenue);

		}
	}

}
