package a2oj;
import java.util.*;
public class BloodCousins{
	private static HashMap<Integer,HashSet<Integer>>children=new HashMap<Integer,HashSet<Integer>>();
	private static int[][]descendants;
	private static int[]parent;
	public static void main(String[]args){
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		parent=new int[n];
		for(int i=0;i<n;i++)
			children.put(i,new HashSet<Integer>());
		for(int child=0;child<n;child++){
			int par=sc.nextInt()-1;
			parent[child]=par;
			if(par!=-1)children.get(par).add(child);
		}
		descendants=new int[n][n];
		for(int i=0;i<n;i++)
			if(parent[i]==-1)
				fillDes(i);
		int m=sc.nextInt();
		for(int i=0;i<m;i++){
			int v=sc.nextInt()-1,p=sc.nextInt(),ancestor=parP(v,p);
			System.out.print(ancestor!=-1?descendants[ancestor][p-1]-1+" ":"0 ");
		}
		sc.close();
	}
	public static int parP(int v,int p){
		while(parent[v]!=-1&&p-->0)
			v=parent[v];
		return  p>0?-1:v;
	}
	public static void fillDes(int i){
		for(int c:children.get(i)){
			fillDes(c);
			int ind=0;
			while(descendants[c][ind]>0)
				descendants[i][ind+1]+=descendants[c][ind++];
			descendants[i][0]++;
		}
	}
}