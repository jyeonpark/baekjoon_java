import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int n = s.length();
		int max = 0;
		for(int i=0; i<n; i++) {
			String sub = s.substring(i, n);
			max = Math.max(max,lps(sub));
		}
		System.out.println(max);
	}

	static int lps(String s) {
		int n = s.length();
		int[] lps = new int[n];
		int leng = 0;
		int max = 0;

		for (int i = 1; i < n; i++) {
			while (leng > 0 && s.charAt(i) != s.charAt(leng)) {
				leng = lps[leng - 1];
			}
			if (s.charAt(i) == s.charAt(leng)) {
				lps[i] = ++leng;
				max = Math.max(max, lps[i]);
			}
		}
		return max;
	}
}