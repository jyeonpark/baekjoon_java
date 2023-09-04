import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int left = 0;
        int right = 0;
        int answer = 0;
        int[] cnt = new int[100001];
        while(left <= right && right<n){
            if (cnt[arr[right]] < k){
                cnt[arr[right]]++;
                right++;
            } else {
                cnt[arr[left]]--;
                left++;
            }
            answer = Math.max(answer, right-left);
        }
        System.out.println(answer);
    }
}