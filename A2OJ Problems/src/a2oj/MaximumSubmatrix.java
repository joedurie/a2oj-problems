package a2oj;
import java.util.*;
public class MaximumSubmatrix {
	public static void main(String[]args){
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt(),m=sc.nextInt();
		sc.nextLine();
		int[][]grid=new int[m][m];
		for(int r=0;r<n;r++){
			char[]line=sc.nextLine().toCharArray();
			for(int c=0;c<m;c++){
				if(line[c]=='1'){
					int s=c;
					while(++c<m&&line[c]=='1'){}
					grid[s][c-1]++;
				}
			}
		}
		for(int i=0;i<m;i++)
			for(int j=m-2;j>=i;j--)
				grid[i][j]+=grid[i][j+1];
		for(int i=1;i<m;i++)
			for(int j=i;j<m;j++)
				grid[i][j]+=grid[i-1][j];
		int max=0;
		for(int i=0;i<m;i++)
			for(int j=i;j<m;j++)
				max=Math.max(max,grid[i][j]*(j-i+1));
		System.out.println(max);
		sc.close();	
	}
}