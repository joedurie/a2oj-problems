package a2oj;
import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class FoolsAndFoolproofRoads {
	static int[] dsu;
	static long[] len;
	static TreeSet<String> edges = new TreeSet<String>();
	static LinkedList<String> newEdges = new LinkedList<String>();
	static TreeMap<Integer, LinkedList<Integer>> sets = new TreeMap<Integer, LinkedList<Integer>>();
	public static void main(String[] args) {
		MyScanner sc = new MyScanner();
		int n = sc.nextInt(), m = sc.nextInt(), p = sc.nextInt(), q = sc.nextInt();
		dsu = new int[n];
		len = new long[n];
		for(int i = 0; i < n; i++)
			dsu[i] = i;
		for(int i = 0; i < m; i++) {
			int u = sc.nextInt() - 1, v = sc.nextInt() - 1, a = dsu(u), b = dsu(v);
			long w = sc.nextLong();
			if(a != b)
				len[a] += len[b];
			len[a] += w;
			dsu[b] = a;
			edges.add(s(u, v));
			edges.add(s(v, u));
		}
		TreeSet<X> components = new TreeSet<X>();
		for(int i = 0; i < n; i++)
			if(dsu[i] == i)
				components.add(new X(i, len[i]));
		while(components.size() >= 2 && components.size() > q && newEdges.size() < p) {
			X first = components.pollFirst(), second = components.pollFirst();
			long newL = min(1000000000L, first.l + second.l + 1);
			components.add(new X(first.i, first.l + second.l));// + newL));
			dsu[dsu(second.i)] = first.i;
			edges.add(s(first.i, second.i));
			edges.add(s(second.i, first.i));
			newEdges.add(s(first.i, second.i));
		}
		if(components.size() != q) {
			out.println("NO");
			out.close();
			return;
		}
		for(X x : components)
			sets.put(x.i, new LinkedList<Integer>());
		for(int i = 0; i < n; i++)
			sets.get(dsu(i)).add(i);
		for(int par : sets.keySet()) {
			for(int a : sets.get(par))
				for(int b : sets.get(par))
					if(newEdges.size() == p) {
						finish();
						return;
					} else if (a != b && !edges.contains(s(a, b))) {
						newEdges.add(s(a, b));
						edges.add(s(a, b));
						edges.add(s(b, a));
					}
		}
		if(newEdges.size() == p) {
			finish();
			return;
		}
		out.println("NO");
		out.close();
	}
	static void finish() {
		out.println("YES");
		for(String s : newEdges)
			out.println(s);
		out.close();
	}
	static String s(int u, int v) {
		return (u + 1) + " " + (v + 1);
	}
	static class X implements Comparable<X>{
		int i;
		long l;
		public X(int I, long L) {
			i = I;
			l = L;
		}
		public int compareTo(X x) {
			return l == x.l ? i - x.i : ((Long)l).compareTo(x.l);
		}
		public String toString() {
			return i + " (" + l + ")";
		}
	}
	static int dsu(int i) {
		return dsu[i] == i ? i : (dsu[i] = dsu(dsu[i]));
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
