package a2oj;
import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class PashmakParmidasProblem {
	static long offset = 2 * (long)pow(10, 9);
	public static void main(String[] args) {
		MyScanner sc = new MyScanner();
		int n = sc.nextInt();
		long[] a = new long[n];
		for(int i = 0; i < n; i++)
			a[i] = sc.nextInt();
		HashMap<Long, Integer> map = new HashMap<Long, Integer>();
		long[] fJ = new long[n];
		for(int i = n - 1; i >= 0; i--) {
			int x = map.containsKey(a[i]) ? map.get(a[i]) : 0;
			fJ[i] = 2 * (n - i) * offset + x;
			map.put(a[i], x + 1);
		}
		Arrays.sort(fJ);
		for(long l : fJ)
			out.println(l);
		map = new HashMap<Long, Integer>();
		long ans = 0;
		for(int i = 0; i < n; i++) {
			int x = map.containsKey(a[i]) ? map.get(a[i]) : 0;
			long fI = (2 * (n - i) - 1) * offset + x;
			ans += bSearch(0, n - 1, a, x) + 1;
			map.put(a[i], x + 1);
		}
		out.println(ans);
		out.close();
	}
	public static int bSearch(int l, int r, long[] a, long x) {
		if(a[l] > x) return -1;
		if(l == r) return l;
		int m = (l + r + 1) / 2;
		return a[m] > x ? bSearch(l, m - 1, a, x) : bSearch(m, r, a, x);
	}
	public static PrintWriter out  = new PrintWriter(new BufferedOutputStream(System.out));
	public static class MyScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String next() {
			while (st == null || !st.hasMoreElements())
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			return st.nextToken();
		}
		int nextInt() {
			return Integer.parseInt(next());
		}
		long nextLong() {
			return Long.parseLong(next());
		}
		double nextDouble() {
			return Double.parseDouble(next());
		}
		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}