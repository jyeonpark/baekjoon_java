import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
    static int n=9;
    static int[][] a = new int[10][10];
    static boolean[][] c = new boolean[10][10];
    static boolean[][] c2 = new boolean[10][10];
    static boolean[][] c3 = new boolean[10][10];
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i=0; i<n; i++) {
            String line = br.readLine();
            for (int j=0; j<n; j++) {
                a[i][j] = line.charAt(j) - '0';
                if (a[i][j] != 0) {
                    c[i][a[i][j]] = true;
                    c2[j][a[i][j]] = true;
                    c3[square(i,j)][a[i][j]] = true;
                }
            }
        }
        go(0);
    }
    public static boolean go(int z) {

        if (z == 81) {
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    System.out.print(a[i][j]);
                }
                System.out.println();
            }
            return true;
        }
        
        int x = z/n;
        int y = z%n;
        if (a[x][y] != 0) {
            return go(z+1);
        } else {
            for (int i=1; i<=9; i++) {
                if (!c[x][i] && !c2[y][i] && !c3[square(x,y)][i]) {
                    c[x][i] = c2[y][i] = c3[square(x,y)][i] = true;
                    a[x][y] = i;
                    if (go(z+1)) {
                        return true;
                    }
                    a[x][y] = 0;
                    c[x][i] = c2[y][i] = c3[square(x,y)][i] = false;
                }
            }
        }
        return false;
    }
    
    public static int square(int x, int y) {
        return (x/3)*3+(y/3);
    }
}
