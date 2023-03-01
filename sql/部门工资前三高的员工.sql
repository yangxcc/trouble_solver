# leetcode 185 部门工资前三高的所有员工 https://leetcode.cn/problems/department-top-three-salaries/
SELECT Department.name AS Department,
    E1.name AS Employee,
    E1.salary AS Salary
FROM Employee E1,
    Department
WHERE E1.departmentId = Department.id
    AND 3 > (
        -- 比前三高薪水 还高的薪水不会超过三个
        SELECT COUNT(DISTINCT E2.salary)
        FROM Employee E2
        WHERE E2.salary > E1.salary
            AND E1.departmentId = E2.departmentId
    )