import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int h, w, height, answer, blocks[], rain[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        blocks = new int[w];
        rain = new int[w];
        height = 0;
        for(int i = 0; i<w; i++){
            blocks[i] = Integer.parseInt(st.nextToken());
            height = Math.max(height, blocks[i]);
            rain[i] = height;
        }
        height = 0;
        for(int i=w-1; i>=0; i--){
            height = Math.max(height, blocks[i]);
            rain[i] = Math.min(rain[i], height);
            answer += (rain[i] - blocks[i]);
        }

        bw.write(Integer.toString(answer));
        bw.flush();
        bw.close();
    }
}