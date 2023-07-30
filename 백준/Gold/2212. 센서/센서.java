import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[] censor = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)  censor[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(censor); // 오름차순으로 센서 좌표 정렬하기
        Queue<Integer> q = new PriorityQueue<>();
        for(int i=0; i<n-1; i++){
            q.offer(censor[i+1] - censor[i]); // (i+1~i)센서 좌표간의 거리 저장
        }
        int ans = 0;
        for(int i=0; i<n-k; i++) ans += q.poll();
        System.out.println(ans);
    }
}