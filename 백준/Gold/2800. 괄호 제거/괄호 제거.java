import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Deque<Integer> stk = new ArrayDeque<>();
        List<int[]> pair = new ArrayList<>();
        TreeSet<String> ans = new TreeSet<>();
        String exp = br.readLine();

        for (int i = 0; i < exp.length(); i++) { // 스택을 통해 괄호쌍 인덱스 저장
            char c = exp.charAt(i);
            if (c == '(') {
                stk.push(i);
            } else if (c == ')') {
                int s = stk.pop();
                pair.add(new int[]{s, i});
            }
        }

        int len = pair.size();
        for (int i = 1; i < (1 << len); i++) { // 제거할 괄호 쌍 조합
            List<Integer> chk = new ArrayList<>();
            StringBuilder tmp = new StringBuilder(exp);
            for (int j = 0; j < len; j++) {
                if ((i & (1 << j)) != 0) {
                    chk.add(pair.get(j)[0]);
                    chk.add(pair.get(j)[1]);
                }
            }
            chk.sort(Comparator.reverseOrder()); // 큰 인덱스부터 삭제되야 exception 발생 안됨
            for (int k : chk) // 괄호 지우기
                tmp.deleteCharAt(k);

            ans.add(tmp.toString()); // 괄호 지운 문자열 저장
        }

        while (!ans.isEmpty()) {
            sb.append(ans.pollFirst()).append("\n");
        }

        System.out.println(sb.toString());
    }
}