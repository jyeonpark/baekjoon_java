import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;
	static int T, n, idx;
	static String a, b;
	static HashMap<String, Integer> network;
	static ArrayList<Integer> group, count;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			network = new HashMap<>();
			group = new ArrayList<>();
			count = new ArrayList<>();
			idx = 0;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				a = st.nextToken();
				b = st.nextToken();
				if (!network.containsKey(a) && !network.containsKey(b)) { // 둘다 존재X
					network.put(a, idx);
					network.put(b, idx);
					group.add(idx);
					count.add(2);
					idx++;
				} else if (network.containsKey(a) && network.containsKey(b)) { // 둘다 존재O
					int groupA = find(network.get(a));
					int groupB = find(network.get(b));
					if (groupA != groupB) { // 서로 다른 그룹이라면
						if (groupA < groupB) {
							group.set(groupB, groupA); // 더 작은 그룹으로 합치기
							count.set(groupA, count.get(groupA) + count.get(groupB));
							count.set(groupB, 0);
						} else {
							group.set(groupA, groupB); // 더 작은 그룹으로 합치기
							count.set(groupB, count.get(groupB) + count.get(groupA));
							count.set(groupA, 0);
						}
					}
				} else if (network.containsKey(a) && !network.containsKey(b)) { // a만 존재
					int groupA = find(network.get(a));
					network.put(b, groupA);
					count.set(groupA, count.get(groupA) + 1);
				} else if (!network.containsKey(a) && network.containsKey(b)) { // b만 존재
					int groupB = find(network.get(b));
					network.put(a, groupB);
					count.set(groupB, count.get(groupB) + 1);
				}

				//System.out.println(count.get(find(network.get(a))));
				sb.append(count.get(find(network.get(a)))).append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
		
	}
	
	static int find(int x) {
		if (group.get(x) == group.get(group.get(x)))	return group.get(x);
		return find(group.get(x));
	}
}