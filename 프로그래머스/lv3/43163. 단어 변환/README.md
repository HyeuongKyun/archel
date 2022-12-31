더 나은 풀이  
이유    1. 필요 없이 함수로 빼기 위해서 코드가 길어지는 것을 방지하였습니다.
        2. 방문기록 v에 대해서 false를 안해도 되는 당위성이 충분히 느껴졌습니다.(아쉬움 : 좀 더  깊이 고민했다면 생각해낼 수 있지 않았을까...)
        3. bfs에 대한 이해와 응용이 좋은 코드라고 생각합니다.

import java.util.LinkedList;
import java.util.Queue;

class Solution {

    static class Node {
        String next;
        int edge;

        public Node(String next, int edge) {
            this.next = next;
            this.edge = edge;
        }
    }

    public int solution(String begin, String target, String[] words) {
        int n = words.length, ans = 0;

        // for (int i=0; i<n; i++)
        //  if (words[i] != target && i == n-1) return 0;

        Queue<Node> q = new LinkedList<>();


        boolean[] visit = new boolean[n];
        q.add(new Node(begin, 0));

        while(!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.next.equals(target)) {
                ans = cur.edge;
                break;
            }

            for (int i=0; i<n; i++) {
                if (!visit[i] && isNext(cur.next, words[i])) {
                    visit[i] = true;
                    q.add(new Node(words[i], cur.edge + 1));
                }
            }
        }

        return ans;
    }

    static boolean isNext(String cur, String n) {
        int cnt = 0;
        for (int i=0; i<n.length(); i++) {
            if (cur.charAt(i) != n.charAt(i)) {
                if (++ cnt > 1) return false;
            }
        }

        return true;
    }    
}


# [level 3] 단어 변환 - 43163 

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/43163) 

### 성능 요약

메모리: 77.1 MB, 시간: 0.02 ms

### 구분

코딩테스트 연습 > 깊이／너비 우선 탐색（DFS／BFS）

### 채점결과

<br/>정확성: 100.0<br/>합계: 100.0 / 100.0

### 문제 설명

<p>두 개의 단어 begin, target과 단어의 집합 words가 있습니다. 아래와 같은 규칙을 이용하여 begin에서 target으로 변환하는 가장 짧은 변환 과정을 찾으려고 합니다.</p>
<div class="highlight"><pre class="codehilite"><code>1. 한 번에 한 개의 알파벳만 바꿀 수 있습니다.
2. words에 있는 단어로만 변환할 수 있습니다.
</code></pre></div>
<p>예를 들어 begin이 "hit", target가 "cog", words가 ["hot","dot","dog","lot","log","cog"]라면 "hit" -&gt; "hot" -&gt; "dot" -&gt; "dog" -&gt; "cog"와 같이 4단계를 거쳐 변환할 수 있습니다.</p>

<p>두 개의 단어 begin, target과 단어의 집합 words가 매개변수로 주어질 때, 최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지 return 하도록 solution 함수를 작성해주세요.</p>

<h5>제한사항</h5>

<ul>
<li>각 단어는 알파벳 소문자로만 이루어져 있습니다.</li>
<li>각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같습니다.</li>
<li>words에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없습니다.</li>
<li>begin과 target은 같지 않습니다.</li>
<li>변환할 수 없는 경우에는 0를 return 합니다.</li>
</ul>

<h5>입출력 예</h5>
<table class="table">
        <thead><tr>
<th>begin</th>
<th>target</th>
<th>words</th>
<th>return</th>
</tr>
</thead>
        <tbody><tr>
<td>"hit"</td>
<td>"cog"</td>
<td>["hot", "dot", "dog", "lot", "log", "cog"]</td>
<td>4</td>
</tr>
<tr>
<td>"hit"</td>
<td>"cog"</td>
<td>["hot", "dot", "dog", "lot", "log"]</td>
<td>0</td>
</tr>
</tbody>
      </table>
<h5>입출력 예 설명</h5>

<p>예제 #1<br>
문제에 나온 예와 같습니다.</p>

<p>예제 #2<br>
target인 "cog"는 words 안에 없기 때문에 변환할 수 없습니다.</p>


> 출처: 프로그래머스 코딩 테스트 연습, https://programmers.co.kr/learn/challenges
