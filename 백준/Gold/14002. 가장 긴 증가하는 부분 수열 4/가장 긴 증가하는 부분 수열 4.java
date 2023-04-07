import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] s = new int[N];
		int[] LIS = new int[N];
		int[] tmp = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			s[i] = Integer.parseInt(st.nextToken());
		}

		LIS[0] = s[0];
		tmp[0] = 0;
		int len = 1;
		for (int i = 1; i < N; i++) {
			int key = s[i];
			// LIS의 마지막 값보다 클 경우 추가
			if (LIS[len - 1] < key) {
				len++;
				LIS[len - 1] = key;
				tmp[i] = len - 1;
			} else {
				// 이분탐색으로 삽입할 장소 찾기 (key보다 큰 가장 작은 값 찾아서 대치하기)
				int left = 0;
				int right = len;
				while (left < right) {
					int mid = (left + right) / 2;
					if (LIS[mid] < key)
						left = mid + 1;
					else
						right = mid;
				}
				LIS[left] = key; // 대치
				tmp[i] = left;
			}
		}

//		System.out.println(Arrays.toString(tmp));

		int[] arr = new int[len];
		for (int i = N - 1; i >= 0; i--) {
			if (arr[tmp[i]] == 0) {
				arr[tmp[i]] = s[i];
			} else if (tmp[i] == len - 1) {
				if (arr[tmp[i]] > s[i])
					arr[tmp[i]] = s[i];
			} else {
				if (s[i] < arr[tmp[i] + 1] && arr[tmp[i]] < s[i])
					arr[tmp[i]] = s[i];
			}
		}
		System.out.println(len);
		for (int i : arr)
			System.out.print(i + " ");
	}
}