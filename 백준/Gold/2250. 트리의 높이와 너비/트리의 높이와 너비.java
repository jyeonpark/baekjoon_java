import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int num;
		int leftChild;
		int rightChild;

		public Node(int num, int leftChild, int rightChild) {
			this.num = num;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;
	static Set<Integer> root = new HashSet<>();
	static Map<Integer, Node> tree = new HashMap<>();
	static Map<Integer, ArrayList<Integer>> level_col_num = new HashMap<>();
	static int N, num, left, right, col_num, max_level, max_width;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());

		for (int i = 1; i <= N; i++) {
			root.add(i);
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			left = Integer.parseInt(st.nextToken());
			right = Integer.parseInt(st.nextToken());
			tree.put(num, new Node(num, left, right));
			root.remove(left); // 자식이라면 루트가 아니다.
			root.remove(right);
		}

		Iterator<Integer> iter = root.iterator();
		dfs(iter.next(), 1);

		// 레벨별로 해당 레벨에 존재하는 노드 열 번호 탐색
		for (int level : level_col_num.keySet()) {
			ArrayList<Integer> list = level_col_num.get(level);
			int width = list.get(list.size() - 1) - list.get(0) + 1;
			if (max_width < width) { // 마지막 열 번호 - 첫 번째 열번호
				max_width = width;
				max_level = level;
			}
		}

		sb.append(max_level).append(" ").append(max_width);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	// left->root->right 순서대로 열 번호를 부여한다.
	static void dfs(int num, int depth) {

		// 왼쪽 노드 열번호 갱신
		if (tree.get(num).leftChild != -1)
			dfs(tree.get(num).leftChild, depth + 1);

		// 현재 노드 열번호 갱신
		if (!level_col_num.containsKey(depth)) { // 레벨이 존재하지 않는다면
			level_col_num.put(depth, new ArrayList<>());
		}
		level_col_num.get(depth).add(++col_num); // 해당 레벨에 열 번호 추가하기

		// 오른쪽 노드 열번호 갱신
		if (tree.get(num).rightChild != -1)
			dfs(tree.get(num).rightChild, depth + 1);

	}
}
