import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {

    static int r, c, nowX, nowY;
    static char[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Point> fireQ; // 불이 난 위치
    static Queue<Point> jihoonQ; // 지훈이가 갈 수 있는 위치 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        fireQ = new LinkedList<>();
        jihoonQ = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'J') {
                    nowX = i;
                    nowY = j;
                    map[i][j] = '.';
                } else if (map[i][j] == 'F') {
                    fireQ.add(new Point(i, j));
                }
            }
        }
        int time = bfs();
        System.out.println(time == -1 ? "IMPOSSIBLE" : time);
    }

    static int bfs() {
        int time = 0;
        boolean[][] visited = new boolean[r][c];
        visited[nowX][nowY] = true;
        jihoonQ.add(new Point(nowX, nowY)); // 지훈이가 있을 수 있는 좌표 경우의 수
        while (!jihoonQ.isEmpty()) { // 지훈이가 이동가능한 위치가 없을 때까지 반복
            time++; // 1분 증가

            int qSize = jihoonQ.size();
            for (int i = 0; i < qSize; i++) {
                Point now = jihoonQ.poll();
                if (map[now.x][now.y] == '.') {
                    if (now.x == 0 || now.x == r - 1 || now.y == 0 || now.y == c - 1) {
                        return time; // 가장자리라면 탈출한다.
                    }
                    for (int d = 0; d < 4; d++) {
                        int nx = now.x + dx[d];
                        int ny = now.y + dy[d];
                        if (nx >= 0 && nx < r && ny >= 0 && ny < c && map[nx][ny] == '.' && !visited[nx][ny]) {
                            jihoonQ.add(new Point(nx, ny));
                            visited[nx][ny] = true;
                        }
                    }
                }
            }

            // 불 확산시키기
            spreadFire(); // 불이 확산된 상태의 새로운 map
        }
        return -1;
    }

    static void spreadFire() {
        int fireCnt = fireQ.size();
        for (int i = 0; i < fireCnt; i++) {
            Point nowFire = fireQ.poll();
            for (int d = 0; d < 4; d++) { // 불이 사방으로 번진다.
                int nx = nowFire.x + dx[d];
                int ny = nowFire.y + dy[d];
                if (nx >= 0 && nx < r && ny >= 0 && ny < c && map[nx][ny] == '.') { // 벽이 아니라면 확산된다.
                    map[nx][ny] = 'F';
                    fireQ.add(new Point(nx, ny));
                }
            }
        }
    }
}