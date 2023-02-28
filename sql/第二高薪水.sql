# leetcode 176 第二高的薪水 https://leetcode.cn/problems/second-highest-salary/
-- SELECT salary AS SecondHighestSalary
-- FROM Employee 
-- ORDER BY salary DESC 
-- limit 1, 1;
SELECT IFNULL (
        (
            SELECT salary
            FROM Employee
            ORDER BY salary DESC
            limit 1, 1
        ), NULL
    ) AS SecondHighestSalary;

/*
    LIMIT的两个参数分别是offset和count，第0行的offset是0，count表示的是选取多少行的数据
    IFNULL的用法：接受两个参数，如果第一个参数的返回结果不是null，则返回第一个参数的返回结果
    如果是null，则返回第二个参数的返回结果
*/