package idontknow;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {
    public int [][] maze;
    public int [][] flag;
    public int[]time= {0,0,0,0};
    public int num=0;
    boolean flagA=false;
    boolean flagB=true;
    public Semaphore arun=new Semaphore(1,true);
    public Semaphore brun=new Semaphore(1,true);
    public int alldone_number=0;
    public Semaphore adone=new Semaphore(0,true);
    public Semaphore bdone=new Semaphore(0,true);
    public int adone_number=0;
    public Semaphore alldone=new Semaphore(0,true);
    BufferedReader br=null;
	String str=null;
	int size=0;
	String[] position=new String[2];
    Semaphore[][] s=new Semaphore[4][4];
    
	public Main() {
		for(int i=0;i<4;i++) {
    	for(int j=0;j<4;j++)s[i][j]=new Semaphore(1,true);
    }
		init();
	}
    public void init() {
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
			num=Integer.parseInt(br.readLine());
			while(str!=null) {
				str=br.readLine();
				if(str!=null) {
				position=str.split(",");
				maze[Integer.parseInt(position[0])][Integer.parseInt(position[1])]=1;}
			}
			br.close();		
			int sizeA=size/4;
			TheadA a=new TheadA(0,0,sizeA);
			TheadA b=new TheadA(1,sizeA,2*sizeA);
			TheadA c=new TheadA(2,2*sizeA,3*sizeA);
			TheadA d=new TheadA(3,3*sizeA,size);
			ThreadB a1=new ThreadB(0,0,sizeA);
			ThreadB b1=new ThreadB(1,sizeA,2*sizeA);
			ThreadB c1=new ThreadB(2,2*sizeA,3*sizeA);
			ThreadB d1=new ThreadB(3,3*sizeA,size);
		    a.start();b.start();c.start();d.start();
		    a1.start();b1.start();c1.start();d1.start();
			alldone.acquire();
			this.toString();
			alldone.release();
			
			
			
		}
	    catch(IOException | InterruptedException e){
	    	System.out.println("IOEXCEPTION!");
	    } 
    }
    public class TheadA extends Thread{
    	private int left,right,id;
    	public TheadA(int id,int left,int right) {
    		this.id=id;
    		this.left=left;
    		this.right=right;
    	}
    	public void run() {
    			
				    
			    try {
			    	for(int index=0;index<num;index++) {
			    		arun.acquire();
		    			if(!flagB)
		    			{bdone.acquire();
		    			flagB=true;}
		    			arun.release();
			    		System.out.println("A"+id);
			    		
					for(int j=left;j<right;j++) {
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
					time[id]++;
					
					
					arun.acquire();
						adone_number++;
						if(adone_number==4) {
							adone_number=0;
							flagB=false;
							adone.release();
						}
					arun.release();
			    	}
			    	
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
			   
			
    	}
    
    
    public class ThreadB extends Thread{
    	private int left,right,id;
    	public ThreadB(int id, int left, int right) {
			this.id=id;
			this.left=left;
			this.right=right;
			// TODO Auto-generated constructor stub
		}

		public void run() {
			
    		
    		try {
    			for(int index=0;index<num;index++) {
    		    brun.acquire();
    			if(!flagA)
    			{adone.acquire();
    			flagA=true;}
    			brun.release();
    			
    			for(int j=left;j<right;j++) {
					for(int k=0;k<size;k++) {
						if(flag[j][k]==1)maze[j][k]=1;
						if(flag[j][k]==-1)maze[j][k]=0;
					}
				}
				
    			brun.acquire();
				{
				adone_number++;
				if(adone_number==4) {
					adone_number=0;
					flagA=false;
					bdone.release();
				}
			}
			brun.release();
    			}
    			brun.acquire();
    			alldone_number++;
    			if(alldone_number==4) {
    				alldone.release();
    			}
    			brun.release();
    			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    public String toString() {
    	
    	BufferedWriter wr;
		try {
			wr = new BufferedWriter (new FileWriter(new File("src/res/output.txt")));
			for(int j=0;j<size;j++) {
			for(int k=0;k<size;k++) {
				if(maze[j][k]==1) {
					str=j+","+k;
					System.out.println(str);
					wr.write(str);wr.newLine();
				}
			}
		}
		
		wr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	return null;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
         Main app=new Main();
         System.out.println(System.currentTimeMillis()-startTime);
	}

}
