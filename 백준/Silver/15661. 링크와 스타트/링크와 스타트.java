import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb;
	static StringTokenizer st;
	static int n, min_power_gap=Integer.MAX_VALUE;
	static int[][] s;
	static boolean[] team;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		s = new int[n][n];
		team = new boolean[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				s[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		subset(0);
		sb.append(min_power_gap);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
	}

	static void subset(int cnt) {
		if (cnt == n) {
			ArrayList<Integer> start_team = new ArrayList<>();
			ArrayList<Integer> link_team = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				if (team[i])
					start_team.add(i);
				else
					link_team.add(i);
			}
			if (start_team.size() > 0 && link_team.size() > 0) {
				int start_power = getPower(start_team);
				int link_power = getPower(link_team);
				min_power_gap = Math.min(min_power_gap, Math.abs(start_power-link_power));
			}
			return;
		}

		team[cnt] = true;
		subset(cnt + 1);

		team[cnt] = false;
		subset(cnt + 1);
	}

	static int getPower(ArrayList<Integer> team) {
		int power = 0;
		for (int i : team) {
			for (int j : team) {
				if (i != j) {
					power += s[i][j];
				}
			}
		}
		return power;
	}
}