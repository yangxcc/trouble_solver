1. 到现在对于输入还是观察的不够仔细，还不能够充分的利用起输入中给出的条件
   - 输入中常见的条件就是给定数值的范围，这一点有时候能够被充分的利用起来
   - 给定数值的范围，还需要考虑到是否会发生越界，比如Problem11中，没考虑到10^14是超过了Integer的数值范围，结果通过用例0

2. 两个整型数字的二进制相加，如果在相加过程中不进位，那么相加的结果等于两个数异或的结果
3. `Arrays.sort(arr, ()->{})`其中要求arr必须要是包装类，比如Integer类型的，如果没有后面的lamdba表达式，那么arr的类型可以是基本数据类型了
4. 有不少的情况下会使用并查集来做，需要记住并查集这种解决方式，实在不会的时候用并查集试试，尤其是涉及到上下左右四个方向时，如果dfs实在不会了，也要能想起来并查集，这一思想在岛问题那里学习过
5. 一种更快的输入处理
   ```java
   import java.io.BufferedReader;import java.io.InputStreamReader;import java.util.Arrays;    
   class Main {
        public static void main(String[] args){
          BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
          int[] inputArray = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
   }
   ```
