import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;
	static int N, H, size, val, min_val=Integer.MAX_VALUE, min_cnt, top[], down[], test;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		top = new int[H+1];
		down = new int[H+1];

		/**
		 * top[size], down[size] => size크기를 가진 석순, 종유석의 개수
		 */
		for (int i = 1; i <= N; i++) { 
			size = Integer.parseInt(br.readLine());
			if (i % 2 == 0) { // 짝수번째는 석순. 장애물이 위에서부터 내려온다.
				top[size]++;
			} else { // 짝수번째는 종유석. 장애물이 밑에서부터 올라온다.
				down[size]++;
			}
		}
		
		/**
		 * 구간합 구하기
		 * top[i], down[i] => i 구간에 존재하는 장애물의 개수
		 * i 장애물을 지나간다면, 항상 i보다 큰 사이즈의 장애물도 지나가야 한다.
		 */
		for(int i=H-1; i>=1; i--) {
			top[i] += top[i+1];
			down[i] += down[i+1];
		}
		
		/**
		 * 장애물이 최소인 구간 구하기
		 * i번째 구간의 장애물 개수 => top[H-i+1] + down[i] 
		 */
		 for(int i=1; i<=H; i++) {
			 val = top[H-i+1] + down[i];
			 if (val < min_val) {
				 min_val = val;
				 min_cnt = 1;
			 } else if (val==min_val)
				 min_cnt++;
		 }
		
		 System.out.println(min_val + " " + min_cnt);
	}
}