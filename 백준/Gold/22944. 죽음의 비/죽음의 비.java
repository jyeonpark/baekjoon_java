import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, h, d, nowX, nowY, safeX, safeY;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        map = new char[n][n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'S') {
                    nowX = i;
                    nowY = j;
                }
            }
        }
        System.out.printf(Integer.toString(move()));
    }

    static int move() {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int[][] visited = new int[n][n];
        visited[nowX][nowY] = h;
        PriorityQueue<State> q = new PriorityQueue<>();
        q.offer(new State(nowX, nowY, h, 0, 0));
        while (!q.isEmpty()) {
            State state = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = state.x + dx[dir];
                int ny = state.y + dy[dir];
                int nHealth = state.health;
                int nDurability = state.durability;
                if (isRanged(nx, ny)) {
                    if (map[nx][ny] == 'E') return state.cnt + 1; // 안전지대 도착
                    if (map[nx][ny] == 'U') { // 다음 자리에 우산이 있다면
                        nDurability = d; // 새 우산으로 교체한다.
                    }
                    if (nDurability == 0) nHealth--; // 우산 내구도가 0인 경우 우산이 없어지고 체력 1감소
                    else nDurability--; // 우산 내구도 1감소

                    if (nHealth == 0)  continue; // 체력 0인 경우 더이상 진행X

                    if (visited[nx][ny] < nHealth + nDurability) { // 이전에 방문했던 노드가 현재 이동가능 거리보다 작다면 계속 진행 가능
                        visited[nx][ny] = nHealth + nDurability;
                        q.offer(new State(nx, ny, nHealth, nDurability, state.cnt + 1));
                    }
                }
            }
        }
        return -1;
    }

    static boolean isRanged(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    static class State implements Comparable<State> {
        int x; // 현재위치 x좌표
        int y; // 현재위치 y좌표
        int health; // 체력
        int durability; // 내구도
        int cnt; // 이동횟수

        public State(int x, int y, int health, int durability, int cnt) {
            this.x = x;
            this.y = y;
            this.health = health;
            this.durability = durability;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(State o) {
            return this.cnt - o.cnt;
        }


    }
}