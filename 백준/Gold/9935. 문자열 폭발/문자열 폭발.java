import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String bomb = br.readLine();
		
		int strLen = str.length();
		int bombLen = bomb.length();
		Stack<Character> stack = new Stack<Character>();
		
		for (int i = 0; i < strLen; i++) {
			stack.add(str.charAt(i));
			if (stack.size() >= bombLen) { // 폭발 문자열 길이보다 크다면
				boolean check = true;
				for (int idx = 0; idx < bombLen; idx++) {
					char c1 = stack.get(stack.size() - bombLen + idx);
					char c2 = bomb.charAt(idx);
					// System.out.println(c1 + " " + c2);
					if (c1 != c2) {
						check = false;
						break;
					}
				}
				if (check) {
					for (int j = 0; j < bombLen; j++) {
						stack.pop();
					}
				}
			}
		}
		if (stack.isEmpty()) {
			System.out.println("FRULA");
		} else {
			StringBuilder sb = new StringBuilder();
			for (char c : stack)
				sb.append(c);
			System.out.println(sb.toString());
		}
	}
}