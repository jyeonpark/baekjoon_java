import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		String[] arr = br.readLine().split("\\-");
		
		int ans = 0;
		for(String s : arr[0].split("\\+")) {
			ans += Integer.parseInt(s);
		}
		
		for(int i=1; i<arr.length; i++) {
			for(String s : arr[i].split("\\+")) {
				ans -= Integer.parseInt(s);
			}
		}
		
		System.out.println(ans);
	}

}