# 문제를 풀면서 알게된 알고리즘
## 제곱근으로 근 소수 판별
- 수가 너무 크고 에라토스테네스 처럼 여러번 소수를 판별 해야하는 경우가 효율이 안 좋을 때 사용
- num이라는 숫자가 있을 때 , Math.sqrt(num) 까지만 소수인지 아닌지 판별하면 된다. 그러면 비용이 log(num)으로 되버린다.
- Math.sqrt(num)까지만 소수인지 확인하면 되는 이유는 다음과 같다.
- num이 합성수라면 2<=x<=sqrt(num) 인 x가 적어도 한번은 사용이 되어야 sqrt(num)보다 크거나 같은 수와 곱해 졌을 때 num이 될 수 있다.
- 그런데 num을 x로 나누었을 때 한번도 나누어 떨어지지 않는다면 이는 소수이다.  

## 소수 판별을 해주는 api는 없다.. ㅜㅜ 

## K진수로 바꿔주는 함수 
- int Num,int K;//10진수 숫자와 변환하고 싶은 진수
- Integer.toString(Num,K);
- Num을 K진수로 나타내 준다.
## K진수를 10진수로 바꿔주는 함수
- String Num,int K;//K진수로 나타낸 숫자(String임을 주의),K진수로 표현
- Integer.parseInt(Num,K);
- Num을 10진수로 변환해서 값을 반환해 준다.
### 만약 위에 2가지 방법을 알았다면 subString 방법을 이용해도 좋았을 것 같다.

# [level 2] k진수에서 소수 개수 구하기 - 92335 

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/92335) 

### 성능 요약

메모리: 78.7 MB, 시간: 0.56 ms

### 구분

코딩테스트 연습 > 2022 KAKAO BLIND RECRUITMENT

### 채점결과

<br/>정확성: 100.0<br/>합계: 100.0 / 100.0

### 문제 설명

<h5>문제 설명</h5>

<p>양의 정수 <code>n</code>이 주어집니다. 이 숫자를 <code>k</code>진수로 바꿨을 때, 변환된 수 안에 아래 조건에 맞는 소수(Prime number)가 몇 개인지 알아보려 합니다.</p>

<ul>
<li><code>0P0</code>처럼 소수 양쪽에 0이 있는 경우</li>
<li><code>P0</code>처럼 소수 오른쪽에만 0이 있고 왼쪽에는 아무것도 없는 경우</li>
<li><code>0P</code>처럼 소수 왼쪽에만 0이 있고 오른쪽에는 아무것도 없는 경우</li>
<li><code>P</code>처럼 소수 양쪽에 아무것도 없는 경우</li>
<li>단, <code>P</code>는 각 자릿수에 0을 포함하지 않는 소수입니다.

<ul>
<li>예를 들어, 101은 <code>P</code>가 될 수 없습니다.</li>
</ul></li>
</ul>

<p>예를 들어, 437674을 3진수로 바꾸면 <code>211</code>0<code>2</code>01010<code>11</code>입니다. 여기서 찾을 수 있는 조건에 맞는 소수는 왼쪽부터 순서대로 211, 2, 11이 있으며, 총 3개입니다. (211, 2, 11을 <code>k</code>진법으로 보았을 때가 아닌, 10진법으로 보았을 때 소수여야 한다는 점에 주의합니다.) 211은 <code>P0</code> 형태에서 찾을 수 있으며, 2는 <code>0P0</code>에서, 11은 <code>0P</code>에서 찾을 수 있습니다.</p>

<p>정수 <code>n</code>과 <code>k</code>가 매개변수로 주어집니다. <code>n</code>을 <code>k</code>진수로 바꿨을 때, 변환된 수 안에서 찾을 수 있는 <strong>위 조건에 맞는 소수</strong>의 개수를 return 하도록 solution 함수를 완성해 주세요.</p>

<hr>

<h5>제한사항</h5>

<ul>
<li>1 ≤ <code>n</code> ≤ 1,000,000</li>
<li>3 ≤ <code>k</code> ≤ 10</li>
</ul>

<hr>

<h5>입출력 예</h5>
<table class="table">
        <thead><tr>
<th>n</th>
<th>k</th>
<th>result</th>
</tr>
</thead>
        <tbody><tr>
<td>437674</td>
<td>3</td>
<td>3</td>
</tr>
<tr>
<td>110011</td>
<td>10</td>
<td>2</td>
</tr>
</tbody>
      </table>
<hr>

<h5>입출력 예 설명</h5>

<p><strong>입출력 예 #1</strong></p>

<p>문제 예시와 같습니다. </p>

<p><strong>입출력 예 #2</strong></p>

<p>110011을 10진수로 바꾸면 110011입니다. 여기서 찾을 수 있는 조건에 맞는 소수는 11, 11 2개입니다. 이와 같이, 중복되는 소수를 발견하더라도 모두 따로 세어야 합니다.</p>

<h5>문제가 잘 안풀린다면😢</h5>

<p>힌트가 필요한가요? [코딩테스트 연습 힌트 모음집]으로 오세요! → <a href="https://school.programmers.co.kr/learn/courses/14743?itm_content=lesson92335" target="_blank" rel="noopener">클릭</a></p>


> 출처: 프로그래머스 코딩 테스트 연습, https://programmers.co.kr/learn/challenges
