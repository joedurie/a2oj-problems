package a2oj;
import java.util.*;

public class LevkoAndArray {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), k = sc.nextInt();
		int[] a = new int[n];
		for(int i = 0; i < n; i++)
			a[i] = sc.nextInt();
		sc.close();
		System.out.println(bs(a, k, 0, Integer.MAX_VALUE));
	}
	public static int bs(int[] a, int k, int l, int r){
		if(l == r)
			return l;
		int m = (l + r) / 2;
		if(works(a, k, m))
			return bs(a, k, l, m);
		return bs(a, k, m + 1, r);
	}
	public static int iter(int[] a, int s, int d, int x){
		int k = 0, last = a[s];
		for(int i = s + d; i >= 0 && i < a.length; i += d) 
			if(Math.abs(a[i] - last) > x) {
				k++;
				if(i == 0 || i == a.length -1 || a[i + d] > last)
					last += x;
				else
					last -= x;
			} else
				last = a[i];
		return k;
	}
	public static boolean works(int[] a, int k, int x) {
		for(int i = 0; i < a.length; i++)
			if(iter(a, i, -1, x) + iter(a, i, 1, x) <= k)
				return true;
		return false;
	}
}
