package main;
import java.math.*;



public class Main {
   
	static int spanNum=2000;
	static double amplitude =300;
	
	public static void main(String args[]) {
		double[] runningSpan=new double[spanNum];
		double amplitude1=amplitude/2;
		double spanTime=2.0/(double)spanNum;
		double radius=0;
		for(int i=0;i<spanNum;i++) {
			runningSpan[i]=amplitude1+Math.sin(Math.PI*radius)*amplitude;
			radius+=spanTime;
		}
		long startTime=0;
		int i=0;
		while(i<spanNum) {
			startTime=System.currentTimeMillis();
			while(System.currentTimeMillis()-startTime<runningSpan[i]);
			try {
				Thread.sleep((long)(amplitude-System.currentTimeMillis()+startTime));
				System.out.println("working");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    if(i<spanNum)i++;
		    if(i==spanNum)i=0;
		}
	}
}
