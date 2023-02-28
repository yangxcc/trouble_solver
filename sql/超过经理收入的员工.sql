# leetcode 181 超过经理收入的员工 https://leetcode.cn/problems/employees-earning-more-than-their-managers/
SELECT a.Name AS Employee
FROM Employee AS a
    INNER JOIN Employee AS b ON a.managerId = b.Id
    AND a.salary > b.salary;

/*
Inner join和join是一样的
*/