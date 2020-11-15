package idontknow;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	public static void main(String args[]) {
		BufferedReader br=null;
		String str=null;
		int size=0;
		String[] position=new String[2];
		int [][] maze;
		int [][] flag;
		try {
			br=new BufferedReader(new FileReader(new File("src/res/input.txt")));
			str=br.readLine();
			size=Integer.parseInt(str);
			maze=new int[size][];
			flag=new int[size][];
			for(int i=0;i<size;i++) {
				maze[i]=new int[size];
				flag[i]=new int [size];
			}
			for(int i=0;i<size;i++) {
				for(int j=0;j<size;j++) {
					maze[i][j]=0;
					flag[i][j]=0;
				}
			}
			int num=Integer.parseInt(br.readLine());
			while(str!=null) {
				str=br.readLine();
				if(str!=null) {
				position=str.split(",");
				maze[Integer.parseInt(position[0])][Integer.parseInt(position[1])]=1;}
			}
			
			for(int i=0;i<num;i++) {
				
				for(int j=0;j<size;j++) {
					for(int k=0;k<size;k++) {
						int partner=0;
						if(j>0&&maze[j-1][k]==1)partner++;
						if(j+1<size&&maze[j+1][k]==1)partner++;
						if(k>0&&maze[j][k-1]==1)partner++;
						if(k+1<size&&maze[j][k+1]==1)partner++;
						if(j>0&&k>0&&maze[j-1][k-1]==1)partner++;
						if(j>0&&k+1<size&&maze[j-1][k+1]==1)partner++;
						if(j+1<size&&k+1<size&&maze[j+1][k+1]==1)partner++;
						if(j+1<size&&k>0&&maze[j+1][k-1]==1)partner++;
						if(partner==2) flag[j][k]=0;
						else if(partner==3)flag[j][k]=1;
						else flag[j][k]=-1;
					}
				}
				for(int j=0;j<size;j++) {
					for(int k=0;k<size;k++) {
						if(flag[j][k]==1)maze[j][k]=1;
						if(flag[j][k]==-1)maze[j][k]=0;
					}
				}
				System.out.println(i);
				
			}
			BufferedWriter wr=new BufferedWriter (new FileWriter(new File("src/res/output.txt")));
			for(int j=0;j<size;j++) {
				for(int k=0;k<size;k++) {
					if(maze[j][k]==1) {
						str=j+","+k;
						System.out.println(str);
						wr.write(str);wr.newLine();
					}
				}
			}
			
			br.close();
			wr.close();
		}
	    catch(IOException e){
	    	System.out.println("IOEXCEPTION!");
	    }
		
		
	}
	
}
