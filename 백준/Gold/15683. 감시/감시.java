import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static class Camera {
        int num;
        int x;
        int y;

        public Camera(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }

    static int n, m;
    static ArrayList<Camera> cameras = new ArrayList<>();
    static int min_spot = Integer.MAX_VALUE;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][][] rotation = {{{0}},
            {{0}, {1}, {2}, {3}},
            {{0, 2}, {1, 3}},
            {{0, 3}, {0, 1}, {1, 2}, {2, 3}},
            {{0, 2, 3}, {0, 1, 3}, {0, 1, 2}, {1, 2, 3}},
            {{0, 1, 2, 3}}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] >= 1 && arr[i][j] <= 5) {
                    cameras.add(new Camera(arr[i][j], i, j));
                }
            }
        }

        solution(0, arr);
        System.out.println(min_spot);

    }

    static void solution(int idx, int[][] arr) {
        if (idx == cameras.size()) {
            int res = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] == 0) res++;
                }
            }
            min_spot = Math.min(min_spot, res); // 최소 사각지대 구하기
            return;
        }

        int num = cameras.get(idx).num;
        int x = cameras.get(idx).x;
        int y = cameras.get(idx).y;

        for (int i = 0; i < rotation[num].length; i++) { // cctv가 회전할 수 있는 경우의 수 만큼
            int tmp[][] = copyArr(arr);
            for (int j = 0; j < rotation[num][i].length; j++) { // 감시해야 하는 방향 수
                int dir = rotation[num][i][j];

                check(tmp, dir, x, y);
            }
            solution(idx + 1, tmp); // 다음 cctv에 대해서 탐색
        }


    }

    static void check(int[][] arr, int dir, int x, int y) {
        while (true) {
            x += dx[dir];
            y += dy[dir];

            if (x < 0 || x >= n || y < 0 || y >= m || arr[x][y] == 6)
                break;
            if (arr[x][y] == 0) { // 빈칸일 경우
                arr[x][y] = -1;
            }
        }
    }

    static int[][] copyArr(int[][] arr) {
        int[][] tmp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmp[i][j] = arr[i][j];
            }
        }
        return tmp;
    }
}