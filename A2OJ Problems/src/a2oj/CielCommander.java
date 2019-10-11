package a2oj;
import java.util.*;
import java.io.*;

public class CielCommander {
	static int[] sz;
	static char[] ans;
	static ArrayList<HashSet<Integer>> tree = new ArrayList<HashSet<Integer>>();
	public static void main(String[] args) {
		MyScanner sc = new MyScanner();
		int n = sc.nextInt();
		sz = new int[n];
		ans = new char[n];
		for(int i = 0; i < n; i++)
			tree.add(new HashSet<Integer>());
		for(int i = 0; i < n - 1; i++) {
			int a = sc.nextInt() - 1, b = sc.nextInt() - 1;
			tree.get(a).add(b);
			tree.get(b).add(a);
		}
		fill('A', 0);
		for(char c : ans)
			out.print(c + " ");
		out.println();
		out.close();
	}
	static void fill(int c, int i) {
		getSize(i, -1);
		i = centroid(i, -1, sz[i]);
		ans[i] = (char)c;
		for(int j : tree.get(i)) {
			tree.get(j).remove(i);
			fill(c + 1, j);
		}
	}
	static int centroid(int i, int p, int n) {
		for(int j : tree.get(i))
			if(j != p && sz[j] > n / 2)
				return centroid(j, i, n);
		return i;
	}
	static int getSize(int i, int p) {
		sz[i] = 1;
		for(int j : tree.get(i))
			if(j != p)
				sz[i] += getSize(j, i);
		return sz[i];
	}
	public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
	public static class MyScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int nextInt() {
			while (st == null || !st.hasMoreElements())
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			return Integer.parseInt(st.nextToken());
		}
	}
}