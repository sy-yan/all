package picalculater;

import java.util.concurrent.Semaphore;

public class Quadruple {
	public  double [] pii;
	 public Semaphore[] sema;
	 public Quadruple(){
		 pii=new double[4];
		 sema=new Semaphore[4];
		 for(int i=0;i<4;i++) {
			 sema[i]=new Semaphore(0,true);
		 }
		 for(int i=0;i<4;i++) {
			 new ThreadA(i).start();
		 }
		 for(int i=0;i<4;i++) {
			 try {
				sema[i].acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 double ans=pii[0]+pii[1]+pii[2]+pii[3];
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
			 switch(id) {
			 case 0:break;
			 case 1:i=1.0/5;n=5;break;
			 case 2:i=-1.0/3;n=3;s=-1.0;break;
			 case 3:i=-1.0/7;n=7;s=-1.0;break;
			 default:break;
			 }
		 }
		 public void run() {
			 startTime=System.currentTimeMillis();
			 while(Math.abs(i)>=1e-10) {
				 pi+=i;
				 n+=8;
				 i=s/n;
			 }
			 pii[id]=pi*4;
			 System.out.println(id+"”√ ±£∫"+(System.currentTimeMillis()-startTime));
			 sema[id].release();
		 }
	 }
}
