import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] dp = new int[n + 2];
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i < n + 1; i++) {
			// 마지막에만 %10007 연산을 해줄 시 중간에 저장되는 값들이 int값을 넘어서 오버플로우 발생!
			dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;

		}
		System.out.println(dp[n]);

	}

}
