import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

	static class Fish implements Comparable<Fish> {
		int x;
		int y;
		int size;

		public Fish(int x, int y, int size) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
		}

		@Override
		public int compareTo(Fish o) {
			if (this.size == o.size) {
				if (this.x == o.x)
					return this.y - o.y;
				return this.x - o.x;
			}
			return this.size - o.size;
		}
	}

	static class Distance implements Comparable<Distance> {
		int x;
		int y;
		int dist;

		public Distance(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Distance o) {
			if (this.dist == o.dist) {
				if (this.x == o.x) {
					return this.y - o.y;
				}
				return this.x - o.x;
			}
			return this.dist - o.dist;
		}

	}

	static int n, size = 2;
	static int[][] map;
	static int x, y;
	static int time;
	static TreeSet<Fish> fishes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		fishes = new TreeSet<Fish>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] >= 1 && map[i][j] <= 6) { // 물고기
					fishes.add(new Fish(i, j, map[i][j]));
				} else if (map[i][j] == 9) { // 아기 상어 위치
					x = i;
					y = j;
				}
			}
		}

		move();
		System.out.println(time);
	}

	static void move() {
		int eaten = 0;

		while (true) {

			if (fishes.size() == 0 || fishes.first().size >= size) { // 더 이상 먹을 수 있는 물고기가 없는 경우
				break;
			}
			if (fishes.size() == 1) { // 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
				Fish f = fishes.pollFirst();
				if (f.size < size)
					time += Math.abs(x - f.x) + Math.abs(y - f.y);
				break;
			} else { // 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다
				int check = 0;
				for (Fish f : fishes) {
					if (f.size < size) {
						check = 1;
						break;
					}
				}
				if (check == 1) {
					int[] info = bfs();
					if (info != null) {
						fishes.remove(new Fish(info[0], info[1], map[info[0]][info[1]]));
						eaten++;
						time += info[2];
						map[x][y] = 0;
						x = info[0];
						y = info[1];
						map[x][y] = 9;
					}
					else break;
				} else {
					break;
				}
			}

//			System.out.printf("size : %d eaten : %d 시간 : %d\n", size, eaten, time);
//			print();

			if (eaten == size) { // 자신의 크기와 같은 수의 물고기를 먹을 때 크기가 1 증가
				size++;
				eaten = 0;
			}

		}

	}

	static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.printf(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static int[] bfs() {
		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };

		PriorityQueue<Distance> q = new PriorityQueue<>();
		q.add(new Distance(x, y, 0)); // 좌표x, 좌표x, 이동거리

		int[][] dp = new int[n][n];
		for (int i = 0; i < n; i++)
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		dp[x][y] = 0;

		while (!q.isEmpty()) {
			Distance d = q.poll();

			if (map[d.x][d.y] >= 1 && map[d.x][d.y] <= 6 && map[d.x][d.y] < size) {
				return new int[] { d.x, d.y, dp[d.x][d.y] };
			}

			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= n)
					continue;
				if (map[nx][ny] > size)
					continue; // 더 큰 물고기라면 못 지나간다.
				if (dp[nx][ny] > dp[d.x][d.y] + 1) {
					dp[nx][ny] = dp[d.x][d.y] + 1;
					q.add(new Distance(nx, ny, dp[nx][ny]));
				}
			}
		}
		return null;
	}
}