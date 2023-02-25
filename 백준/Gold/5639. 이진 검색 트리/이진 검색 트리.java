import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int data;
		Node left;
		Node right;

		public Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		void addNode(int num) {
			if (this.data > num) {
				if (this.left == null) {
					this.left = new Node(num, null, null);
				}else
					this.left.addNode(num);
			} else {
				if (this.right == null) {
					this.right = new Node(num,null, null);
				}else
					this.right.addNode(num);
			}
		}

	}

	static StringBuilder sb;
	static StringTokenizer st;
	static Node root;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		root = new Node(Integer.parseInt(br.readLine()), null, null);
		String line;
		while ((line = br.readLine()) != null) {
			int num = Integer.parseInt(line);
			root.addNode(num);
		}
		
		postOrder(root);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static void postOrder(Node node) {
		if (node.left != null) {
			postOrder(node.left);
		} 
		if (node.right!=null) {
			postOrder(node.right);
		} 
		sb.append(node.data).append("\n");
	}

}