import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        char[][] map;
        while (!line.equals("end")) {
            map = new char[3][3];
            int oCnt = 0, xCnt = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    map[i][j] = line.charAt(3 * i + j); // 3x3 map으로 저장
                    if (map[i][j] == 'O') oCnt++;
                    else if (map[i][j] == 'X') xCnt++;
                }
            }
            boolean xCheck = check(map, 'X');
            boolean oCheck = check(map, 'O');
            // 게임판이 다 채워진 경우
            if (oCnt == 4 && xCnt == 5) {
                if (xCheck && oCheck) sb.append("invalid\n"); // x,o 둘다 빙고이면 안됨
                else if (oCheck) sb.append("invalid\n"); // 마지막돌은 x이므로, o가 이길 수 없음
                else sb.append("valid\n"); // x가 이긴경우 성립가능
            } else {
                if (xCheck && oCheck) sb.append("invalid\n"); // x,o 둘다 빙고이면 안됨
                else if (xCheck && xCnt - oCnt == 1) sb.append("valid\n"); // x가 이긴경우 x가 하나 더 많다.
                else if (oCheck && xCnt == oCnt) sb.append("valid\n"); // o가 이긴경우 x,o개수가 똑같다.
                else sb.append("invalid\n"); // 말이 다 안채워졌는데 둘다 안이긴 경우는 없다.
            }
            line = br.readLine();
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static boolean check(char[][] map, char c) {
        // 가로 체크
        for (int i = 0; i < 3; i++) {
            int cnt = 0;
            for (int j = 0; j < 3; j++) {
                if (map[i][j] == c) cnt++;
            }
            if (cnt == 3) return true;
        }

        // 세로 체크
        for (int j = 0; j < 3; j++) {
            int cnt = 0;
            for (int i = 0; i < 3; i++) {
                if (map[i][j] == c) cnt++;
            }
            if (cnt == 3) return true;
        }

        // 왼쪽 대각선 체크
        if ((map[0][0] == c) && (map[1][1] == c) && (map[2][2] == c)) return true;

        // 오른쪽 대각선 체크
        if ((map[0][2] == c) && (map[1][1] == c) && (map[2][0] == c)) return true;

        return false;
    }
}