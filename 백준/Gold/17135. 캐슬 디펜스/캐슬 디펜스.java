import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	static StringBuilder sb;
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int n, m, d, max_enemies_cnt, total_enemies_cnt;
	static int[][] map;
	static int[] archers;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		archers = new int[3];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total_enemies_cnt += map[i][j];
			}
		}

		combination(0, 0);
		sb.append(max_enemies_cnt);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void combination(int cnt, int start) {
		if (cnt == 3) { // 궁수 3명 위치를 다 고른것
			// 적 제거하기
			int[][] tmp = copyArray(map);
			fight(tmp);
			return;
		}

		for (int i = start; i < m; i++) {
			archers[cnt] = i;
			combination(cnt + 1, i + 1);
		}
	}

	static void fight(int[][] arr) {
		int remove_enemy_cnt = 0; // 제거한 적 수
		while (true) {

			if (getEnemyCnt(arr) == 0)
				break;

			// 이번 턴에서 궁수들이 공격할 수 있는 적 좌표 집합
			Set<Point> enemies = new HashSet<Point>();
			for (int i = 0; i < 3; i++) {
				Point p = selectEnemy(n, archers[i], arr);
				if (p != null)
					enemies.add(p);
			}


			for (Point p : enemies) { // 이번 턴에서 제거될 적
				arr[p.x][p.y] = 0;
				remove_enemy_cnt++;
			}

			// 궁수의 공격이 끝나면, 적들을 아래로 한 칸 이동시킨다.
			moveEnemies(arr);
		}

		max_enemies_cnt = Math.max(max_enemies_cnt, remove_enemy_cnt);
	}

	static void moveEnemies(int[][] arr) {
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 1) {
					if (i == n - 1) { // 마지막 줄에 있는 적들은 성으로 이동한다.
						arr[i][j] = 0;
					} else { // 마지막 줄이 아니라면 한 칸 아래로 이동한다.
						arr[i + 1][j] = arr[i][j];
						arr[i][j] = 0;
					}
				}
			}
		}
	}

	static Point selectEnemy(int x, int y, int[][] arr) {
		int max_dis = d;
		int enemy_x = -1;
		int enemy_y = m;
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 1) {
					int dis = Math.abs(x - i) + Math.abs(y - j);
					if (dis < max_dis || (dis==max_dis && enemy_y>j)) {
						enemy_x = i;
						enemy_y = j;
						max_dis = dis;
					}
				}
			}
		}
		return (enemy_x==-1 ? null : new Point(enemy_x, enemy_y));
	}

	static int[][] copyArray(int arr[][]) {
		int[][] tmp = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				tmp[i][j] = arr[i][j];
			}
		}
		return tmp;
	}

	static int getEnemyCnt(int[][] arr) {
		int total = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				total += arr[i][j];
			}
		}
		return total;
	}

}