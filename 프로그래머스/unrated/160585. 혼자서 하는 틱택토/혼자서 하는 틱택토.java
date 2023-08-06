import java.util.*;
class Solution {
    static char[][] map;
    public int solution(String[] board) {
        int answer = 0;
        map = new char[3][3];
        int oCnt = 0, xCnt = 0;
        for (int i = 0; i < 3; i++) {
            map[i] = board[i].toCharArray();
            for (int j = 0; j < 3; j++) {
                if (map[i][j] == 'O') oCnt++;
                else if (map[i][j] == 'X') xCnt++;
            }
        }
        boolean oCheck = check('O');
        boolean xCheck = check('X');
        // 게임판이 다 채워진 경우
        if (oCnt == 5 && xCnt == 4) {
            if (oCheck && !xCheck) answer = 1;
        } else { // 게임판이 다 채워지기 전
            if (oCheck && !xCheck && oCnt - xCnt == 1) answer = 1; // o가 이긴경우 o가 하나 더 많다.
            else if (xCheck && !oCheck && xCnt == oCnt) answer = 1; // x가 이긴경우 x,o개수가 똑같다.
            else if (!oCheck && !xCheck){ // 아직 게임 진행 중인 경우 
                 if (oCnt == xCnt || oCnt == xCnt + 1)  answer = 1;
            }
        }
        return answer;
    }
    
    static boolean check(char c) {
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