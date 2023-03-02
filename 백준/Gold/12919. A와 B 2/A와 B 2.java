import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static String s, t;
	static int s_length;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		s = br.readLine();
		t = br.readLine();
		s_length = s.length();
		System.out.println(dfs(t) ? 1: 0);
	}

	static boolean dfs(String str) {
		
		if (str.equals(s))
			return true;
		if (str.length() <= s.length())
			return false;
		
		if (str.charAt(str.length()-1) == 'A') {
			if (dfs(str.substring(0, str.length()-1)))	return true;
		}
		
		if (str.charAt(0) == 'B') {
			sb = new StringBuilder(str);
			if (dfs(sb.reverse().toString().substring(0, str.length()-1))) return true;
		}
		
		return false;
	}
}