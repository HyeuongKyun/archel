# [level 2] 두 큐 합 같게 만들기 - 118667 

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/118667) 

### 성능 요약

메모리: 113 MB, 시간: 88.56 ms

### 구분

코딩테스트 연습 > 2022 KAKAO TECH INTERNSHIP

### 채점결과

<br/>정확성: 100.0<br/>합계: 100.0 / 100.0

### 문제 설명

<p>길이가 같은 두 개의 큐가 주어집니다. 하나의 큐를 골라 원소를 추출(pop)하고, 추출된 원소를 <strong>다른 큐</strong>에 집어넣는(insert) 작업을 통해 각 큐의 원소 합이 같도록 만들려고 합니다. 이때 필요한 작업의 최소 횟수를 구하고자 합니다. 한 번의 pop과 한 번의 insert를 합쳐서 작업을 1회 수행한 것으로 간주합니다.</p>

<p>큐는 먼저 집어넣은 원소가 먼저 나오는 구조입니다. 이 문제에서는 큐를 배열로 표현하며, 원소가 배열 앞쪽에 있을수록 먼저 집어넣은 원소임을 의미합니다. 즉, pop을 하면 배열의 첫 번째 원소가 추출되며, insert를 하면 배열의 끝에 원소가 추가됩니다. 예를 들어 큐 <code>[1, 2, 3, 4]</code>가 주어졌을 때, pop을 하면 맨 앞에 있는 원소 1이 추출되어 <code>[2, 3, 4]</code>가 되며, 이어서 5를 insert하면 <code>[2, 3, 4, 5]</code>가 됩니다.</p>

<p>다음은 두 큐를 나타내는 예시입니다.</p>
<div class="highlight"><pre class="codehilite"><code>queue1 = [3, 2, 7, 2]
queue2 = [4, 6, 5, 1]
</code></pre></div>
<p>두 큐에 담긴 모든 원소의 합은 30입니다. 따라서, 각 큐의 합을 15로 만들어야 합니다. 예를 들어, 다음과 같이 2가지 방법이 있습니다.</p>

<ol>
<li>queue2의 4, 6, 5를 순서대로 추출하여 queue1에 추가한 뒤, queue1의 3, 2, 7, 2를 순서대로 추출하여 queue2에 추가합니다. 그 결과 queue1은 [4, 6, 5], queue2는 [1, 3, 2, 7, 2]가 되며, 각 큐의 원소 합은 15로 같습니다. 이 방법은 작업을 7번 수행합니다.</li>
<li>queue1에서 3을 추출하여 queue2에 추가합니다. 그리고 queue2에서 4를 추출하여 queue1에 추가합니다. 그 결과 queue1은 [2, 7, 2, 4], queue2는 [6, 5, 1, 3]가 되며, 각 큐의 원소 합은 15로 같습니다. 이 방법은 작업을 2번만 수행하며, 이보다 적은 횟수로 목표를 달성할 수 없습니다.</li>
</ol>

<p>따라서 각 큐의 원소 합을 같게 만들기 위해 필요한 작업의 최소 횟수는 2입니다.</p>

<p>길이가 같은 두 개의 큐를 나타내는 정수 배열 <code>queue1</code>, <code>queue2</code>가 매개변수로 주어집니다. 각 큐의 원소 합을 같게 만들기 위해 필요한 작업의 최소 횟수를 return 하도록 solution 함수를 완성해주세요. 단, 어떤 방법으로도 각 큐의 원소 합을 같게 만들 수 없는 경우, -1을 return 해주세요.</p>

<hr>

<h5>제한사항</h5>

<ul>
<li>1 ≤ <code>queue1</code>의 길이 = <code>queue2</code>의 길이 ≤ 300,000</li>
<li>1 ≤ <code>queue1</code>의 원소, <code>queue2</code>의 원소 ≤ 10<sup>9</sup></li>
<li>주의: 언어에 따라 합 계산 과정 중 산술 오버플로우 발생 가능성이 있으므로 long type 고려가 필요합니다.</li>
</ul>

<hr>

<h5>입출력 예</h5>
<table class="table">
        <thead><tr>
<th>queue1</th>
<th>queue2</th>
<th>result</th>
</tr>
</thead>
        <tbody><tr>
<td>[3, 2, 7, 2]</td>
<td>[4, 6, 5, 1]</td>
<td>2</td>
</tr>
<tr>
<td>[1, 2, 1, 2]</td>
<td>[1, 10, 1, 2]</td>
<td>7</td>
</tr>
<tr>
<td>[1, 1]</td>
<td>[1, 5]</td>
<td>-1</td>
</tr>
</tbody>
      </table>
<hr>

<h5>입출력 예 설명</h5>

<p><strong>입출력 예 #1</strong></p>

<p>문제 예시와 같습니다.</p>

<p><strong>입출력 예 #2</strong></p>

<p>두 큐에 담긴 모든 원소의 합은 20입니다. 따라서, 각 큐의 합을 10으로 만들어야 합니다. queue2에서 1, 10을 순서대로 추출하여 queue1에 추가하고, queue1에서 1, 2, 1, 2와 1(queue2으로부터 받은 원소)을 순서대로 추출하여 queue2에 추가합니다. 그 결과 queue1은 [10], queue2는 [1, 2, 1, 2, 1, 2, 1]가 되며, 각 큐의 원소 합은 10으로 같습니다. 이때 작업 횟수는 7회이며, 이보다 적은 횟수로 목표를 달성하는 방법은 없습니다. 따라서 7를 return 합니다.</p>

<p><strong>입출력 예 #3</strong></p>

<p>어떤 방법을 쓰더라도 각 큐의 원소 합을 같게 만들 수 없습니다. 따라서 -1을 return 합니다.</p>


> 출처: 프로그래머스 코딩 테스트 연습, https://programmers.co.kr/learn/challenges



```
//풀이 방법 :  각각의 큐에 합을 모두 구한 뒤 더 큰 곳에서 작은 큐로 원소를 하나씩 넘겨주는 방식을 선택한다.
// 고려해야할 점은 두 개의 합이 같아 질 수 없을 때인데 다음과 같은 상황을 고려한다.
//1. [1,1],[1,5] 와 같은 경우 한쪽이 empty가 되는 -1 return으로 해주면 된다.
//2. [3,3],[3,5] 와 같은 경우 똑같은 연산이 계속해서 반복되는 경우
// -> 단순히 합이 반복되는 걸로는 [3,2],[3,5]와 같은 상황을 처리할 수 없다.
// 이런 경우는 순환이 되는데 순환이 되는걸 검사해주면 된다.
// 새롭게 q1,q2에 들어가는 원소들이 원래 배열에 있던 원소들과 계속해서 일치해서 결국에 끝까지 다 일치하면 순환이기 때문에 return -1을 해준다.
//그런데 순환을 하려면 q1의 값이 q2가 되는 순간이 존재 해야하기 때문에
//q1이 q2랑 같으면 -1을 return
//조사 방법을 길이 우선 확인 그런데
//큐의 길이가 300,000까지 이므로 150,000개의 배열이 같은지를 1턴마다 확인하면 최대 150,000*150,000 = 10^10 시간초과가 날 가능성 다분 우선은 하자.

//큐로 하면 시간이 너무 오래 걸리니까 List로 한다.
import java.util.*;

class Solution {
    static long sum1, sum2;
    static List<Integer> q1,q2;
    static int[] queue1,queue2;
    public int solution(int[] queue1, int[] queue2) {
        sum1=0;
        sum2=0;
        this.queue1 = queue1;
        this.queue2 = queue2;
        q1 = new ArrayList<>();
        q2 = new ArrayList<>();
        for(int i=0;i<queue1.length;i++){

            int temp1 = queue1[i];
            sum1+=temp1;
            q1.add(temp1);

            int temp2 = queue2[i];
            sum2+=temp2;
            q2.add(temp2);
        }//q에 값 넣기


        int answer = 0;
        while(true){
            //값 옮기기
            if(sum1>sum2){
                answer++;
                oper(0);//메서드에게 1이 크다라는 정보를 넘기기 위한 매개변수
            }
            else if(sum1<sum2){
                answer++;
                oper(1);// 2가 크다는 정보를 넘기기위한 매개변수
            }else{//sum1==sum2
                return answer;
            }
            // System.out.println(q1);
            // System.out.println(q2);
            //값을 옮기고 나서 상태 살핌
            if(q1.isEmpty() || q2.isEmpty()){
                // System.out.println("empty");
                return  -1;//answer을 -1로 return 한거랑 같음
            }//둘 중 한쪽이 원소가 없을 때 양쪽 큐는 같아 질 수 없다. (왜냐하면 이 경우는 나머지를 다 더한거 보다 하나의 원소 값이 제일 크다는 의미이기 때문이다.)
            int q1Size = q1.size();
            int q2Size = q2.size();

            if(q1Size==q2Size && q1.get(q1Size-1)==queue2[q1Size-1] && q2.get(q1Size-1)==queue1[q1Size-1]){
                // System.out.println("size");
                //이걸 안해줘도 되네..? 그래도 시간 초과가 뜸
                // if(check()) {
                return -1;
                // }
            }//두 개의 사이즈가 같을 때는 q1과 q2가 원소를 서로 바꿔 가졌을 때를 검사해야한다. 왜냐하면 이 경우는 순환이 되기 때문이다.
        }//while
    }//main

    static boolean check(){
        // System.out.println("----");

        int size = q1.size();

//         for(int i=0;i<size;i++){
//             int tempVal1 = q1.get(i);
//             if(queue2[i]!=tempVal1) {
//                 return false;
//             }

//             int tempVal2 = q2.get(i);
//             if(queue1[i]!=tempVal2) {
//                 return false;
//             }

//         }

        return true;
    }//check

    static void oper(int bigQueue){
        List<Integer> bigQ;
        List<Integer> smallQ;
        if (bigQueue==0){
            bigQ = q1;
            smallQ = q2;
        }else{//1일 때
            bigQ = q2;
            smallQ = q1;
        }

        int temp = bigQ.remove(0);
        if(bigQueue==0) {
            sum1 -= temp;
            sum2 += temp;
        } else {
            sum2 -= temp;
            sum1 += temp;
        }
        smallQ.add(temp);

    }//oper

}
```
