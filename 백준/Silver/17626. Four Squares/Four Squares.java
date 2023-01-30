import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] dp = new int[n+1];
		dp[1]=1;
		for(int i=2; i<n+1; i++)
			dp[i] = 5; // 제곱수의 개수가 4이하여야 하므로 
		for(int i=1; i<=n; i++) {
			int j = 1;
			while(i-j*j>=0) {
				dp[i] = Math.min(dp[i], dp[i-j*j]+1);
				j++;
			}
		}
		System.out.println(dp[n]);
	}

}
