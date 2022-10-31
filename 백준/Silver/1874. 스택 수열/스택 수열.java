import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int N = sc.nextInt();
		int[] nums = new int[N+1];

		for(int n=1;n<=N;n++) 
			nums[n]=sc.nextInt();
		
		int n=1;
		int idx = 1;
		while(idx<=N) {
			int num = nums[n++];
			while(idx<=num) {
				stack.add(idx++);
				sb.append("+\n");
			}
			
			if(stack.isEmpty()) {
				System.out.println("NO");
				return;
			}//if 여기서 그냥 비면 잘못수를 넣은거라서 안됌
			
			if(stack.peek()<=num) {
				stack.pop();
				sb.append("-\n");
			}//if
			else {
				System.out.println("NO");
				return;
			}
		}//for
		
		int max=N;
		while(!stack.isEmpty()) {
			int temp = stack.pop();
			if(max<temp || (n<=N &&temp != nums[n++])) {
				System.out.println("NO");
				return;
			}
			else {
				max = temp;
				sb.append("-\n");
			}
		}
		
		System.out.println(sb);
		
		sc.close();
	}//main
}//class
