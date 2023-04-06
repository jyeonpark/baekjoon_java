import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] s = new int[N];
		int[] LIS = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			s[i] = Integer.parseInt(st.nextToken());
		}
 
		LIS[0] = s[0];
		int len = 1;
		for (int i = 1; i < N; i++) {
			int key = s[i];
			// LIS의 마지막 값보다 클 경우 추가
			if (LIS[len - 1] < key) {
				len++;
				LIS[len - 1] = key;
			} 
			else {
				// 이분탐색
				int left = 0;
				int right = len;
				while (left < right) {
					int mid = (left + right) / 2;
					if(LIS[mid] < key) left = mid + 1;
					else right = mid;
				}
				LIS[left] = key; // 대치
			}
		}
		System.out.println(len);
	}
}