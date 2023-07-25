import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, a, b, num[];
    static long min = Long.MAX_VALUE, sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        num = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }
        int l = 0;
        int r = n-1;
        while(l < r){
            sum = num[l] + num[r];
            if (min > Math.abs(sum)){
                min = Math.abs(sum);
                a = l;
                b = r;
            }
            if (sum >= 0) r--;
            else l++;
        }
        System.out.println(num[a] + " " + num[b]);
    }
}