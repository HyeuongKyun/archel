package alchel;

import java.util.Scanner;

public class P11653 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int inc = 2;
		while(N>1) {
			if(N%inc==0) {
				N = N/inc;
				System.out.println(inc);
			}else {
				inc++;
			}
		}
		
		sc.close();
	}//main
}//class
