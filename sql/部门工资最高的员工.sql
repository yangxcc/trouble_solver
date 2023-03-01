# leetcode 184 部门工资最高的员工 https://leetcode.cn/problems/department-highest-salary/
SELECT Department.name AS Department,
    Employee.name AS Employee,
    Employee.salary AS Salary
FROM Employee,
    Department
WHERE Employee.departmentId = Department.id
    AND (Employee.salary, Employee.departmentId) IN ( -- 这里得有括号
        SELECT MAX(salary),
            departmentId
        FROM Employee
        GROUP BY departmentId
    )