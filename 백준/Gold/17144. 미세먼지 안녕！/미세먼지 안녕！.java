import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static int r, c, t, map[][];
	static int airCleanerHead = -1;
	static int airCleanerTail = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					if (airCleanerHead == -1) {
						airCleanerHead = i;
					} else {
						airCleanerTail = i;
					}
				}
			}
		}
		run();
	}

	static void run() {
		int time = 0;
		while (time < t) {
			spread(); // 미세먼지가 확산한다.
			operate(); // 공기청정기가 작동한다.
			time++;
		}
		System.out.println(getSum());
	}

	static void spread() {
		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };
		int[][] tmp = new int[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] > 0) { // 미세먼지
					int dust = map[i][j] / 5;
					int cnt = 0;
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if (nx < 0 || nx >= r || ny < 0 || ny >= c)
							continue;
						if (map[nx][ny] == -1)
							continue;
						tmp[nx][ny] += dust;
						cnt++;
					}
					tmp[i][j] += map[i][j] - dust * cnt;
				}
			}
		}
		copyArray(map, tmp);
		map[airCleanerHead][0] = -1;
		map[airCleanerTail][0] = -1;
	}

	static void copyArray(int[][] arr, int[][] tmp) {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				arr[i][j] = tmp[i][j];
			}
		}
	}

	static void operate() {
		// 공기청정기 위 : 반시계방향
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();

		for (int i = 0; i < airCleanerHead-1; i++)
			q.add(map[i][0]);
		q.add(0);
		for (int i = 1; i < c; i++)
			q.add(map[airCleanerHead][i]);
		for (int i = airCleanerHead - 1; i >= 0; i--)
			q.add(map[i][c - 1]);
		for (int i = c - 2; i > 0; i--)
			q.add(map[0][i]);
		q.addFirst(q.pollLast());
	

		for (int i = 0; i < airCleanerHead; i++)
			map[i][0] = q.pollFirst();
		for (int i = 1; i < c; i++)
			map[airCleanerHead][i] = q.pollFirst();
		for (int i = airCleanerHead - 1; i >= 0; i--)
			map[i][c - 1] = q.pollFirst();
		for (int i = c - 2; i > 0; i--)
			map[0][i] = q.pollFirst();

		// 공기청정기 아래 방향
		
		for (int i = 1; i < c; i++)
			q.add(map[airCleanerTail][i]);
		for (int i = airCleanerTail + 1; i < r; i++)
			q.add(map[i][c - 1]);
		for (int i = c - 2; i >= 0; i--)
			q.add(map[r - 1][i]);
		for (int i = r - 2; i > airCleanerTail-1; i--)
			q.add(map[i][0]);
		q.add(0);

		q.addFirst(q.pollLast());

		for (int i = 1; i < c; i++)
			map[airCleanerTail][i] = q.pollFirst();
		for(int i=airCleanerTail+1; i<r; i++)	map[i][c-1] = q.pollFirst();
		for(int i=c-2; i>=0; i--)	map[r-1][i] = q.pollFirst();
		for(int i=r-2; i>airCleanerTail; i--)	map[i][0]= q.pollFirst();

		map[airCleanerHead][0] = -1;
		map[airCleanerTail][0] = -1;
	}
	
	static int getSum() {
		int total = 0;
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if (map[i][j] != -1)	total += map[i][j];
			}
		}
		return total;
	}
}