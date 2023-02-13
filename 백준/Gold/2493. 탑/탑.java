import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Stack<Tower> s = new Stack<Tower>();
		
		for(int i=0; i<n; i++) {
			Tower now = new Tower(Integer.parseInt(st.nextToken()),i+1);
			
			while (!s.isEmpty()) {
				if (now.height <= s.peek().height) {
					sb.append(s.peek().idx + " ");
					break;
				} else {
					s.pop();
				}
			}
			
			if (s.empty()) {
				sb.append(0 + " ");
			}
			
			s.push(now);
			
		}

		System.out.println(sb.toString());
	}
	
	static class Tower{
		int height;
		int idx;
		
		public Tower(int height, int idx) {
			this.height = height;
			this.idx = idx;
		}
		
		
	}

}
