package selector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<String[]> waitForSelect = new ArrayList<String[]>();
    public static List<String[]> unacc_sample = new ArrayList<String[]>();
    public static List<String[]> acc_sample = new ArrayList<String[]>();
    public static List<String[]> good_sample = new ArrayList<String[]>();
    public static List<String[]> vgood_sample = new ArrayList<String[]>();
    public static double num=0,acc_num=0,unacc_num=0,good_num=0,vgood_num=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String temp = "";
        waitForSelect.add(null);
        try {
        	//int hang=0;
			BufferedReader br=new BufferedReader(new FileReader(new File("src/res/input2.txt")));
			while(temp!=null) {
				//hang++;
				temp=br.readLine();
				if(temp==null)break;
				String[] buf = temp.split(",");
				if(temp==""||temp==null||buf.length!=7);
				else {
					
					
					num++;
					//System.out.println(temp+"  "+hang);
					switch(buf[6]) {
					case "unacc":unacc_sample.add(buf);unacc_num++;break;
					case "acc":acc_sample.add(buf);acc_num++;break;
					case "good":good_sample.add(buf);good_num++;break;
					default:vgood_sample.add(buf);vgood_num++;break;
					}
					
				}
			}
			//读入样本集
			String[] wait=new String[4];
			wait[0]="low,low,4,4,small,high";
			wait[1]="high,high,4,4,med,med";
			wait[2]="low,high,3,2,med,low";
			wait[3]="low,low,2,4,big,high";
			for(int i=0;i<4;i++) {
				waitForSelect.add(i,wait[i].split(","));
			}
			for(int i=0;i<4;i++) {
				double[][] allnum=new double[4][6];
				for(int jk=0;jk<4;jk++)for(int j=0;j<6;j++) {allnum[jk][j]=0;}//清零
				for(int j=0;j<6;j++) {//漫长的算术过程
					for(String[] ans:unacc_sample) {
						if(waitForSelect.get(i)[j].equals(ans[j]))allnum[0][j]++;
					}
					for(String[] ans:acc_sample) {
						if(waitForSelect.get(i)[j].equals(ans[j]))allnum[1][j]++;
					}
					for(String[] ans:good_sample) {
						if(waitForSelect.get(i)[j].equals(ans[j]))allnum[2][j]++;
					}
					for(String[] ans:vgood_sample) {
						if(waitForSelect.get(i)[j].equals(ans[j]))allnum[3][j]++;
					}
					
				}
				double a0=1,a1=1,a2=1,a3=1;
				for(int j=0;j<6;j++) {a0*=allnum[0][j]/unacc_num; a1*=allnum[1][j]/acc_num;a2*=allnum[2][j]/good_num;a3*=allnum[3][j]/vgood_num; }
				a0*=unacc_num/num;a1*=acc_num/num;a2*=good_num/num;a3*=vgood_num/num;
				int choose=4;
				if(a0>=a1&&a0>=a2&&a0>=a3)choose=0;
				else if(a1>=a0&&a1>=a2&&a1>=a3)choose=1;
				else if(a2>=a1&&a2>=a0&&a2>=a3)choose=2;
				else choose=3;
				System.out.print("对（"+wait[i]+"）来说，他是：");
				switch(choose) {
				case 0: System.out.println("unacc");break;
				case 1: System.out.println("acc");break;
				case 2: System.out.println("good");break;
				case 3: System.out.println("vgood");break;
				default:break;
				}
				
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        catch (IOException e) {
        	System.err.println("IO");
        }
	}

}
