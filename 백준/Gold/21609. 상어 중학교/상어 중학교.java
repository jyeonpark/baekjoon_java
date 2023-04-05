import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int n, m, map[][], score, blockGroup[][];
	static HashMap<Integer, Set<Node>> blockGroupRainbows;
	static PriorityQueue<Block> blockGroups;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		autoPlay();
		System.out.println(score);
	}

	static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static void autoPlay() {
		while (true) {
			blockGroup = new int[n][n];
			blockGroups = new PriorityQueue<>();
			blockGroupRainbows = new HashMap<>();

			findBlockGroup(); // 블록 그룹 찾기
			if (blockGroups.isEmpty())
				break;
//			System.out.printf("%d %d %d \n", blockGroups.peek().standard_x, blockGroups.peek().standard_y,
//					blockGroups.peek().blockCnt);
			deleteBlock(blockGroups.poll());
//			print();

			applyGravity();
//			print();
			rotate();
//			print();

			applyGravity();
//			print();

//			System.out.println(score);
		}
	}

	static void findBlockGroup() {
		int groupNum = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] > 0 && blockGroup[i][j] == 0) { // 일반 블록이고 아직 그룹에 포함이 되지 않았다면
					if (bfs(i, j, groupNum))
						groupNum++;
				}
			}
		}
	}

	static void deleteBlock(Block block) {
		score += block.blockCnt * block.blockCnt;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (blockGroup[i][j] == block.blockNum)
					map[i][j] = -2; // 블록그룹에 포함된 블록 제거하기
			}
		}
		for (Node rainbow : blockGroupRainbows.get(block.blockNum)) {
			map[rainbow.x][rainbow.y] = -2; // 무지개지우기
		}
	}

	static void applyGravity() {
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] >= 0) {
					int num = map[i][j];
					map[i][j] = -2;
					int nx = i;
					while (nx + 1 <= n - 1 && map[nx + 1][j] == -2) {
						nx++;
					}
					map[nx][j] = num;
				}
			}
		}
	}

	static void rotate() {
		int[][] arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = map[j][n - i - 1];
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = arr[i][j];
			}
		}
	}

	static boolean bfs(int x, int y, int groupNum) {
		Queue<Node> q = new LinkedList<>();
		int color = map[x][y]; // 일반 블록 색
		int standard_x = x; // 기준블록 x
		int standard_y = y; // 기준블록 y
		int blockcnt = 0;
		int rainbowCnt = 0;
		q.add(new Node(x, y));
		blockGroup[x][y] = groupNum;
		Set<Node> rainbows = new HashSet<>();

		while (!q.isEmpty()) {
			Node p = q.poll();
			blockcnt++;
			if (map[p.x][p.y] == 0)
				rainbowCnt++;
			for (int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				// 블록의 색이 같거나 무지개색이고, 블록그룹에 포함되지 않은 블록이라면
				if (isRange(nx, ny) && (map[nx][ny] == color || map[nx][ny] == 0)
						&& (blockGroup[nx][ny] == 0 && !rainbows.contains(new Node(nx, ny)))) {
					if (map[nx][ny] == color) { // 일반 블록일 떼 기준 블록이 될 수 있는지 확인하기
						if (nx < standard_x || (nx == standard_x && ny < standard_y)) {
							standard_x = nx;
							standard_y = ny;
						}
						blockGroup[nx][ny] = groupNum;
					}
					if (map[nx][ny] == 0) {
						rainbows.add(new Node(nx, ny));
					}
					q.add(new Node(nx, ny));
				}
			}
		}

		if (blockcnt > 1) { // 블록 개수가 2보다 같다면 블록그룹으로 인정!
			blockGroups.add(new Block(standard_x, standard_y, rainbowCnt, blockcnt, groupNum));
			blockGroupRainbows.put(groupNum, rainbows);
			return true;
		} else {
			blockGroup[x][y] = 0;
			return false;
		}
	}

	static boolean isRange(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

	}

	static class Block implements Comparable<Block> {
		int standard_x;
		int standard_y;
		int rainbowBlockCnt;
		int blockCnt;
		int blockNum;

		public Block(int standard_x, int standard_y, int rainbowBlockCnt, int blockCnt, int blockNum) {
			super();
			this.standard_x = standard_x;
			this.standard_y = standard_y;
			this.rainbowBlockCnt = rainbowBlockCnt;
			this.blockCnt = blockCnt;
			this.blockNum = blockNum;
		}

		@Override
		public int compareTo(Block o) {
			if (this.blockCnt == o.blockCnt) {
				if (this.rainbowBlockCnt == o.rainbowBlockCnt) {
					if (this.standard_x == o.standard_x) {
						return o.standard_y - this.standard_y; // 행이 같다면 기준 블록의 열이 가장 큰 것
					} else {
						return o.standard_x - this.standard_x; // 기준 블록의 행이 가장 큰 것
					}
				} else {
					return o.rainbowBlockCnt - this.rainbowBlockCnt;
				}
			} else {
				return o.blockCnt - this.blockCnt;
			}

		}

	}
}