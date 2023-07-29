import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

    static final int MAX = Integer.MAX_VALUE;
    static int N, ans = MAX;
    static char[] start[], end; // 0번째 스위치 on/off에 따른 여부 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        start = new char[2][N];
        end = new char[N];
        String tmp = br.readLine();
        start[0] = tmp.toCharArray();
        start[1] = tmp.toCharArray();
        end = br.readLine().toCharArray();

        // 0번째 스위치 안켜기
        go(1, 0,0);

        // 0번째 스위치 켜기
        push(0, 1);
        go(1,1,1);

        System.out.println(ans == MAX ? -1 : ans);
    }

    static void push(int idx, int type){
        for(int i=idx-1; i<=idx+1; i++){ // 스위치로 (i-1 ~ i+1)번째 전구 상태 바꾸기
            if (i>=0 && i<N)    start[type][i] = start[type][i] == '1' ? '0' : '1';
        }
    }

    static void go(int idx, int cnt, int type){
        if (idx == N) {
            if (start[type][idx-1] == end[idx-1])   ans = Math.min(ans, cnt);
        }
        else if (start[type][idx-1] == end[idx-1]) { // idx-1이 같다면 스위치 누르지X
            go(idx+1, cnt, type);
        }
        else { // idx-1이 달라서 스위치를 누른다.
            push(idx, type);
            go(idx+1, cnt+1, type);
        }
    }
}