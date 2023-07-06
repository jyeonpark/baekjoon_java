import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static HashSet<String> keywords;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        keywords = new HashSet<>();
        for(int i=0; i<n; i++){
            keywords.add(br.readLine());
        }
        for(int j=0; j<m; j++){
            st = new StringTokenizer(br.readLine(), ",");
            while(st.hasMoreTokens()){
                keywords.remove(st.nextToken());
            }
            sb.append(keywords.size()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}