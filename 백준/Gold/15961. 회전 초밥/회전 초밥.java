import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	static int n, d, k, c, arr[], cnt, max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<Integer, Integer> sushi = new HashMap<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		arr = new int[n];
		for (int i = 1; i <= d; i++)
			sushi.put(i, 0);
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if (i < k) {
				if (sushi.get(arr[i]) == 0) {
					cnt++;
				}
				sushi.put(arr[i], sushi.get(arr[i]) + 1);
			}
		}

		int start = 1;
		int end = start + k - 1;

		while (start < n) {
			sushi.put(arr[start - 1], sushi.get(arr[start - 1]) - 1); // 이전 초밥 제외하기
			if (sushi.get(arr[start - 1]) == 0)
				cnt -= 1;			

			if (sushi.get(arr[end]) == 0)
				cnt += 1;
			sushi.put(arr[end], sushi.get(arr[end]) + 1); // 새로운 초밥 추가하기

			start++;
			end = (end + 1) % n;

			max = Math.max(max, sushi.get(c) == 0 ? cnt+1 : cnt);
		}

		System.out.println(max);
	}
}