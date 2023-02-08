import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static String str;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <=10; t++) {
			int n = Integer.parseInt(br.readLine());
			int[] arr = new int[100];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<100; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			for(int i=0; i<n; i++) {
				Arrays.sort(arr);
				arr[99]--;
				arr[0]++;
			}
			Arrays.sort(arr);
			System.out.printf("#%d %d\n",t,arr[99]-arr[0]);
			
		}

	}

}
