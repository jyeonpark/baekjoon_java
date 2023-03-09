import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;
	static int L, W, H, N, min_cnt;
	static int[] cube;
	static boolean check;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(br.readLine());
		cube = new int[20];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()); // 큐브 종류
			int cnt = Integer.parseInt(st.nextToken()); // 큐브 갯수
			cube[num] = cnt;
		}
		divide(L, W, H);
		if (check)	System.out.println(min_cnt);
		else 	System.out.println(-1);
	}
	
	static void divide(int l, int w, int h) {
		if (l==0 || w ==0 || h==0 )	return;
		check = false;
		int size = 0;
		for(int i=19; i>=0; i--) {
			if (cube[i] == 0)	continue;
			
			size = 1<<i;
			if (l >=size && w >= size && h >= size) {
				cube[i]--;
				min_cnt++;
				check = true;
				break;
			}
		}
		if (!check)return; 	// 큐브를 채울 수 없는 경우
		divide(l-size, size, size);
		divide(l,w-size, size);
		divide(l, w, h-size);
		
	}
}