# leetcode 182 查找重复的电子邮箱 https://leetcode.cn/problems/duplicate-emails/
SELECT Email
FROM Person
GROUP BY Email
HAVING COUNT(Email) > 1;

# 执行顺序 where>group by>having>order by
# where后面不能够跟着聚合函数，因为聚合函数是对结果集进行筛选，而where条件使用的时机是结果集还没确定