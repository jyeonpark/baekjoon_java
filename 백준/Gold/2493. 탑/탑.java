import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {

		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		Stack<Tower> s = new Stack<Tower>(); // 현재 탑 보다 왼쪽에 있는 탑들의 집합
		
		for(int i=0; i<n; i++) {
			Tower now = new Tower(Integer.parseInt(st.nextToken()),i+1);
			
			while (!s.isEmpty()) { // stack 탐색
				if (now.height <= s.peek().height) { // now 보다 높은 탑이라면
					sb.append(s.peek().idx + " "); // 수신할 수 있음
					break;
				} else { // now보다 작은 탑이라면
					s.pop(); // now의 오른쪽에 있는 탑들은 해당 탑에게 송신할 경우가 절대 없으므로 pop
				}
			}
			
			if (s.empty()) { // now 보다 높은 탑이 왼쪽에 존재X
				sb.append(0 + " ");
			}
			
			s.push(now);
			
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static class Tower{
		int height; // 탑 높이
		int idx; // 탑 인덱스
		
		public Tower(int height, int idx) {
			this.height = height;
			this.idx = idx;
		}
		
		
	}

}
