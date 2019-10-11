package a2oj;
import java.util.*;
import java.io.*;

public class Password {
	public static void main(String[] args) {
		MyScanner sc = new MyScanner();
		out = new PrintWriter(new BufferedOutputStream(System.out));
		char[] s = sc.nextLine().toCharArray();
		int[] lps = new int[s.length];
		int ind = 1, len = 0;
		while(ind < s.length)
			if(s[ind] == s[len]) {
				lps[ind] = ++len;
				ind++;
			} else if(len == 0) {
				lps[ind] = 0;
				ind++;
			} else 
				len = lps[len - 1];
		ind = 1;
		len = 0;
		int max = 0;
		while(ind < s.length - 1){
			if(s[ind] == s[len]) {
				len++;
				ind++;
			} else if(len == 0)
				ind++;
			else
				len = lps[len - 1];
			max = Math.max(max, len);
		}
		len = lps[s.length - 1];
		while(len > max)
			len = lps[len - 1];
		if(len == 0)
			out.println("Just a legend");
		else {
			for(int i = 0; i < len; i++)
				out.print(s[i]);
			out.println();
		}
		out.close();
	}
	public static PrintWriter out;
	public static class MyScanner {
		BufferedReader br;
		StringTokenizer st;
		public MyScanner() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
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