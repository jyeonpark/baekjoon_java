import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        Map<String, Node> child;

        public Node() {
            child = new HashMap<>();
        }
    }

    static StringBuilder sb;
    static int n;
    static Node root;
    static Node cur;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());

        root = new Node();
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            cur = root; // cur은 루트
            for(int j=0; j<k; j++){
                add(st.nextToken());
            }
        }

        print(root, 0);

        System.out.println(sb.toString());

    }

    public static void add(String s){
        if (cur.child.get(s) == null){
            cur.child.put(s, new Node());
        }
        cur = cur.child.get(s); // 자식을 cur 로 변경
    }

    public static void print(Node cur, int depth){
        if (cur.child != null){
            List<String> children = new ArrayList<>(cur.child.keySet()); // cur의 자식 key list
            Collections.sort(children); // 사전순대로 정렬하기

            for(String child : children){
                sb.append(getLine(depth)).append(child).append("\n");
                print(cur.child.get(child), depth+1);
            }
        }
    }

    public static String getLine(int depth){
        StringBuilder sb = new StringBuilder("");
        for(int i=0; i<depth*2; i++)
            sb.append("-");
        return sb.toString();
    }

}