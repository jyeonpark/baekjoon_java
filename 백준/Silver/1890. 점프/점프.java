import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][n];
		long[][] dp = new long[n][n];

		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(input[j]);
			}
		}

		int answer = 0;

		// 시작지점의 경로개수는 1
		dp[0][0] = 1;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				
				// 도착지점
				if (i==n-1 && j==n-1) {
					break;
				}
				int cnt = arr[i][j]; // 한 번에 이동할 칸의 개수
				
				// 아래로 이동
				if (i+cnt<n) {
					dp[i+cnt][j] += dp[i][j];
				}
				
				// 오른쪽으로 이동
				if (j+cnt<n) {
					dp[i][j+cnt] += dp[i][j];
				}
			}
		}

		System.out.println(dp[n-1][n-1]);
	}

}
