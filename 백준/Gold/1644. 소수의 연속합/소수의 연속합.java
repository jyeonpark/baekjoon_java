import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		boolean check[] = new boolean[n + 1];
		ArrayList<Integer> primes = new ArrayList<>();

		check[0] = check[1] = true;
		// 소수 구하기
		for (int i = 2; i <= n; i++) {
			if (!check[i]) {
				int j = 2;
				while (i * j <= n) {
					check[i * j] = true;
					j++;
				}
			}
		}

		for (int i = 2; i <= n; i++) {
			if (!check[i])
				primes.add(i);
		}

		int cnt = 0;
		if (n == 1) {
			cnt = 0;
		} else {

			// 두포인터로 부분합 구하기

			int size = primes.size();
			int sum = primes.get(0);
			int left = 0;
			int right = 0;
			while (left < size && right < size) {
				if (sum == n) {
					cnt++;
					sum -= primes.get(left);
					left++;
				}

				if (sum < n) {
					right++;
					if (right < size)
						sum += primes.get(right);
				}

				if (sum > n) {
					sum -= primes.get(left);
					left++;
				}
			}
		}
		System.out.println(cnt);
	}

}
