//기존의 그리디하게 경우를 줄이는 방법으로는 계속 시간초과떠서 다른 풀이 참조.

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

class Solution {

    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;

        Queue<Double> q1 = new LinkedList<>(
                Arrays.stream(queue1).mapToDouble(i -> (long)i).boxed().collect(Collectors.toList()));
        Queue<Double> q2 = new LinkedList<>(
                Arrays.stream(queue2).mapToDouble(i -> (long)i).boxed().collect(Collectors.toList()));

        double sum1 = q1.stream().reduce(0d, (a, b) -> a + b).longValue();
        double sum2 = q2.stream().reduce(0d, (a, b) -> a + b).longValue();


        int end = (q1.size() + q2.size()) * 2;

        int count = 0;

        while (sum1 != sum2) {

            count++;

            Double poll;
            if (sum1 > sum2) {
                poll = q1.poll();
                sum1 -= poll;
                sum2 += poll;
                q2.offer(poll);
            } else {
                poll = q2.poll();
                sum1 += poll;
                sum2 -= poll;
                q1.offer(poll);
            }

            if (count > end) {
                return -1;
            }
        }
        return count;
    }

}