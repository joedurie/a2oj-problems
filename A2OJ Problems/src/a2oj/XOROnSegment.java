package a2oj;
import java.util.*;

public class XOROnSegment {
	static int n;
	static int[] a;
	static long[][] segtree;
	static int[] pending;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		a = new int[n];
		for(int i = 0; i < n; i++)
			a[i] = sc.nextInt();
		segtree = new long[4 * n][20];
		pending = new int[4 * n];
		fillSegtree(1, 0, n - 1);
		int m = sc.nextInt();
		for(int i = 0; i < m; i++){
			int t = sc.nextInt(), l = sc.nextInt() - 1, r = sc.nextInt() - 1;
			if(t == 1)
				System.out.println(rangeSum(1, 0, n - 1, l, r));
			else
				xorUpdate(1, 0, n - 1, l, r, sc.nextInt());
		}
		sc.close();
	}
	public static void xorUpdate(int i, int l, int r, int qL, int qR, int x){
		if(pending[i] > 0)
			fixPending(i, r - l + 1);
		if(qR >= l && qL <= r){
			if(qL <= l && qR >= r){
				xorify(i, x, r - l + 1);
				if(l != r){
					pending[2 * i] ^= x;
					pending[2 * i + 1] ^= x;
				}
			}else{
				int m = (l + r) / 2;
				xorUpdate(2 * i, l, m, qL, qR, x);
				xorUpdate(2 * i + 1, m + 1, r, qL, qR, x);
				combine(i);
			}
		}
	}
	public static long rangeSum(int i, int l, int r, int qL, int qR) {
		if(pending[i] > 0)
			fixPending(i, r - l + 1);
		if(qR < l || qL > r)
			return 0;
		if(qL <= l && qR >= r)
			return sum(i);
		int m = (l + r) / 2;
		return rangeSum(2 * i, l, m, qL, qR) + rangeSum(2 * i + 1, m + 1, r, qL, qR);
	}
	public static void fillSegtree(int i, int l, int r) {
		if(l == r)
			xorify(i, a[l], 1);
		else {
			int m = (l + r) / 2;
			fillSegtree(2 * i, l, m);
			fillSegtree(2 * i + 1, m + 1, r);
			combine(i);
		}
	}
	public static void combine(int i) {
		for(int j = 0; j < 20; j++)
			segtree[i][j] = segtree[2 * i][j] + segtree[2 * i + 1][j];
	}
	public static void xorify(int i, int x, int len) {
		int j = 0;
		while(x >= 1 << j){
			if((x & (1 << j)) > 0)
				segtree[i][j] = len - segtree[i][j];
			j++;
		}
	}
	public static void fixPending(int i, int l) {
		if(l != 1){
			pending[2 * i] ^= pending[i];
			pending[2 * i + 1] ^= pending[i];
		}
		xorify(i, pending[i], l);
		pending[i] = 0;
	}
	public static long sum(int i) {
		long ans = 0;
		for(int j = 0; j < 20; j++)
			ans += segtree[i][j] << j;
		return ans;
	}
}
