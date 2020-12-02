package picalculater;

public class PiCalculater {
double s=1;
double pi=0;
static double i=1.0;
double n=1.0;
public PiCalculater() {
	while(Math.abs(i)>=1e-20) {
	pi+=i;
	n=n+2;
	s=-s;
	i=s/n;
}
	System.out.println(pi*4);
}
public static void main(String[] args) {
	long startTime=System.currentTimeMillis()
;	new Quadruple();
    System.out.println(System.currentTimeMillis()-startTime);
}
}
