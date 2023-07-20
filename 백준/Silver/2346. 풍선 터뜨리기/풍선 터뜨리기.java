import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        Deque<Integer> balloons = new ArrayDeque<>();
        int numbers[] = new int[n + 1];
        int num, now;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            balloons.add(i);
        }

        for (int i = 0; i < n; i++) {
            now = balloons.pollFirst();
            sb.append(now + " ");
            num = numbers[now];
            if (balloons.size() > 0) {
                if (num > 0) {
                    for (int j = 0; j < num - 1; j++) {
                        balloons.addLast(balloons.pollFirst());
                    }
                } else {
                    for (int j = 0; j < (num * -1); j++) {
                        balloons.addFirst(balloons.pollLast());
                    }
                }
            }
        }
        System.out.println(sb.toString());
    }
}