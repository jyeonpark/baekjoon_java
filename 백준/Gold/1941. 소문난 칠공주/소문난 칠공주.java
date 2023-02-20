import java.io.*;
import java.util.*;

public class Main {

    static char[][] arr;
    static int[] selected;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new char[5][5];
        for (int i = 0; i < 5; i++) {
            String s = br.readLine();
            for (int j = 0; j < 5; j++) {
                arr[i][j] = s.charAt(j);
            }
        }

        selected = new int[7];
        combination(0, 0, 0);

        System.out.println(answer);
    }

    /**
     * 조합으로 7명의 학생을 구하고, 해당 경우의 수로 칠공주 규칙을 만족하는지 검사한다.
     *
     * @param idx:    현재 탐색중인 학생 idx (0~24)
     * @param cnt:    지금까지 고른 칠공주 수
     * @param cntLee: 지금까지 고른 칠공주 중 이다솜파의 수
     */
    public static void combination(int idx, int cnt, int cntLee) {

        if (cnt - cntLee > 3)
            return;

        if (cnt == 7) { // 7명의 칠공주를 모두 구한것
            if (cntLee >= 4) { // 이다솜파가 4명 이상 포함되어 있는 경우 공주들이 인접한지 검사하기
                if (check()) {
                    answer++;
                }
            }

            return;
        }

        for (int i = idx; i < 25; i++) {
            selected[cnt] = i;
            combination(i + 1, cnt + 1, arr[i / 5][i % 5] == 'S' ? cntLee + 1 : cntLee);
        }
    }

    /**
     * @return 공주들이 모두 인접한다면 true, 그렇지 않다면 false
     */
    public static boolean check() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(selected[0]);

        HashSet<Integer> hs = new HashSet<>(); // 현재 선택된 공주들의 번호를 저장한 hs
        for (int i = 1; i < 7; i++) {
            hs.add(selected[i]);
        }

        int tmp = 1;
        while (!q.isEmpty()) {
            int now = q.poll();

            for (int i = 0; i < 4; i++) { // 상하좌우에 다른 공주가 인접해있는지 탐색
                int nx = now / 5 + dx[i];
                int ny = now % 5 + dy[i];
                int num = nx * 5 + ny;
                if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && hs.contains(num)) { // 인접한 공주가 있다면
                    q.add(num);
                    hs.remove(num);
                    tmp++;
                }
            }
        }
        if (tmp == 7) {
            return true;
        }
        return false;
    }
}