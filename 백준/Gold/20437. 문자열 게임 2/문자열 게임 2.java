import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    static int T, alpha[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String w = br.readLine();
            int len = w.length();
            int k = Integer.parseInt(br.readLine());

            if (k==1){
                System.out.println(1 + " " + 1);
                continue;
            }

            int min_len = 10001;
            int max_len = -1;
            alpha = new int[26];
            for(int i=0; i<len; i++){
                alpha[w.charAt(i) - 'a']++; // 알파벳 개수 섹;
            }

            for(int i=0; i<len; i++){
                int c = w.charAt(i); // c가 k개인 부분 문자열 구하기
                if (alpha[c-'a'] < k) continue; // k개 미만인 숫자일 경우 탐색X
                int cnt = 0;
                for(int j=i; j<len; j++){
                    if (c == w.charAt(j))   cnt++;
                    if (cnt == k) {
                        min_len = Math.min(min_len, j-i+1);
                        max_len = Math.max(max_len, j-i+1);
                        break;
                    }
                }
            }

            if (min_len == 10001) System.out.println(-1);
            else System.out.println(min_len + " " + max_len);
        }
    }
}