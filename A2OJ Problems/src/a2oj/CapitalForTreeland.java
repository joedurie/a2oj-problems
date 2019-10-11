package a2oj;
import java.util.*;
public class CapitalForTreeland {
	private static HashMap<Integer,HashSet<Integer>>tree=new HashMap<Integer,HashSet<Integer>>();
	private static int badCt=0;
	private static int max=0;
	private static TreeSet<Integer>ans=new TreeSet<Integer>();
	public static void main(String[]args){
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		for(int i=1;i<=n;i++)
			tree.put(i,new HashSet<Integer>());
		for(int i=0;i<n-1;i++){
			int a=sc.nextInt(),b=sc.nextInt();
			tree.get(a).add(b);
			tree.get(b).add(-a);
		}
		sc.close();
		fillBad(1,-1,0);
		System.out.println(badCt-max);
		while(!ans.isEmpty())
			System.out.print(ans.pollFirst()+" ");
	}
	private static void fillBad(int a,int p,int b){
		if(b==max)
			ans.add(a);
		else if(b>max){
			max=b;
			ans=new TreeSet<Integer>();
			ans.add(a);
		}
		for(int i:tree.get(a)){
			int j=Math.abs(i);
			if(j!=p){
				if(j!=i)
					badCt++;
				fillBad(j,a,b+(i==j?-1:1));
			}
		}
	}
}
