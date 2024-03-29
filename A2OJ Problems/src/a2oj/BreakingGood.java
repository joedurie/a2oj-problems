package a2oj;
import java.util.*;
public class BreakingGood {
	private static LinkedList<Integer>queue=new LinkedList<Integer>();
	private static HashMap<Integer,HashSet<Integer>>graph=new HashMap<Integer,HashSet<Integer>>();
	private static boolean[]used;
	private static int[]brokenCt;
	private static HashSet<String>unbroken=new HashSet<String>();
	private static HashSet<String>broken=new HashSet<String>();
	private static int[]bestPar;
	private static int[]dist;
	public static void main(String[]args){
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt(),m=sc.nextInt();
		for(int i=0;i<n;i++)
			graph.put(i,new HashSet<Integer>());
		for(int i=0;i<m;i++){
			int a=sc.nextInt()-1,b=sc.nextInt()-1,z=sc.nextInt();
			graph.get(a).add(b);
			graph.get(b).add(a);
			if(z==0)
				broken.add((a+1)+" "+(b+1));
			else
				unbroken.add((a+1)+" "+(b+1));
		}
		used=new boolean[n];
		brokenCt=new int[n];
		dist=new int[n];
		bestPar=new int[n];
		for(int i=1;i<n;i++){
			brokenCt[i]=Integer.MAX_VALUE;
			dist[i]=Integer.MAX_VALUE;
		}
		queue.add(0);
		bestPar[0]=-1;
		while(!queue.isEmpty())
			bfs(queue.remove());
		System.out.println(m+2*brokenCt[n-1]-dist[n-1]-broken.size());
		int city=n-1;
		while(city>0){
			if(hasEdge(broken,city+1,bestPar[city]+1))
				System.out.println((city+1)+" "+(bestPar[city]+1)+" 1");
			else if(unbroken.contains((city+1)+" "+(bestPar[city]+1)))
				unbroken.remove((city+1)+" "+(bestPar[city]+1));
			else
				unbroken.remove((bestPar[city]+1)+" "+(city+1));
			city=bestPar[city];
		}
		for(String s:unbroken)
			System.out.println(s+" "+0);
		sc.close();
	}
	private static boolean hasEdge(HashSet<String>set,int a,int b){
		return set.contains(a+" "+b)||set.contains(b+" "+a);
	}
	private static void bfs(int a){
		used[a]=true;
		for(int i:graph.get(a)){
			int b=hasEdge(broken,a+1,i+1)?1:0;
			if(dist[a]+1<dist[i]||(dist[a]+1==dist[i]&&brokenCt[a]+b<brokenCt[i])){
				dist[i]=dist[a]+1;
				brokenCt[i]=brokenCt[a]+b;
				bestPar[i]=a;
			}
			if(!used[i]){
				used[i]=true;
				queue.add(i);
			}
		}
	}
}
