import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static String str;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			str = br.readLine();
			String tmp = "";
			for (int i = 0; i < str.length(); i++)
				tmp += "0";
			System.out.printf("#%d %d\n", t, recursion(0, tmp));
		}

	}

	static int recursion(int i, String tmp) {
		if (tmp.equals(str))
			return i;

		for (int j = 0; j < tmp.length(); j++) {

			if (str.charAt(j) != tmp.charAt(j)) {
				String tmp2 = tmp.substring(0, j);
				int length = tmp.length() - tmp2.length();
				for (int k = 0; k < length; k++) {
					tmp2 += str.charAt(j);
				}
				return recursion(i + 1, tmp2);
			}
		}
		return 0;
	}

}
