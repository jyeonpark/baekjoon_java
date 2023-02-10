import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

	static class Problem {
		int p; // 문제번호
		int l; // 난이도

		public Problem(int p, int l) {
			this.p = p;
			this.l = l;
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		// => 최대 난이도, 최소 난이도를 반환하기 위한 treeset
		TreeSet<Problem> ts = new TreeSet<>((o1, o2) -> {
			if (o1.l == o2.l) { // 문제 난이도가 같다면
				return o1.p - o2.p; // 문제 번호가 작은 것 순서대로
			}
			return o1.l - o2.l; // 문제 난이도 오름차순
		});

		Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // 문제번호-난이도 쌍을 관리하기 위한 map

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			Problem problem = new Problem(p, l);
			ts.add(problem);
			map.put(p, l);
		}

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "add":
				int p = Integer.parseInt(st.nextToken());
				int l = Integer.parseInt(st.nextToken());
				Problem problem = new Problem(p, l);
				ts.add(problem);
				map.put(p, l);
				break;
			case "recommend":
				int num = Integer.parseInt(st.nextToken());
				if (num == 1) { // 가장 어려운 문제의 번호를 출력 => 최대힙
					System.out.println(ts.last().p);
				} else if (num == -1) { // 가장 쉬운 문제의 번호를 출력 => 최소힙
					System.out.println(ts.first().p);
				}
				break;
			case "solved":
				int s_p = Integer.parseInt(st.nextToken());
				int s_l = map.get(s_p);
				ts.remove(new Problem(s_p, s_l));
				break;
			default:
				break;
			}
			
		}
	}

}
