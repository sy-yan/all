package picalculater;

import java.util.concurrent.Semaphore;

public class DoublePiCalculater {
 public  double [] pii;
 public Semaphore[] sema;
 public DoublePiCalculater(){
	 pii=new double[2];
	 sema=new Semaphore[2];
	 for(int i=0;i<2;i++) {
		 sema[i]=new Semaphore(0,true);
	 }
	 for(int i=0;i<2;i++) {
		 new ThreadA(i).start();
	 }
	 for(int i=0;i<2;i++) {
		 try {
			sema[i].acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 double ans=pii[0]-pii[1];
	 System.out.println("pi="+ans);
 }
 public class ThreadA extends Thread {
	 private double pi=0;
	 private double i=1.0;
	 private double n=1.0;
	 private int id;
	 private double s=1.0;
	 private long startTime;
	 public ThreadA(int id) {
		 this.id=id;
		 if(id!=0) {
			 this.i=this.i/3.0;
			 n+=2;
		 }
	 }
	 public void run() {
		 startTime=System.currentTimeMillis();
		 while(Math.abs(i)>=1e-12) {
			 pi+=i;
			 n+=4;
			 i=s/n;
		 }
		 pii[id]=pi*4;
		 System.out.println(id+"”√ ±£∫"+(System.currentTimeMillis()-startTime));
		 sema[id].release();
	 }
 }
}
