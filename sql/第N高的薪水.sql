# leetcode 177 第N高的薪水 https://leetcode.cn/problems/nth-highest-salary/
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT 
BEGIN
    SET N = N - 1;
    RETURN (
        SELECT IFNULL(
                (
                    SELECT DISTINCT salary
                    FROM Employee
                    ORDER BY salary DESC
                    limit N, 1
                ), NULL
            )
);
END

# 不能直接在limit那里写N-1，这样的语法是不对的