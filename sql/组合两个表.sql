# leetcode 175 组合两个表 https://leetcode.cn/problems/combine-two-tables/
SELECT p.FirstName,
    p.LastName,
    a.City,
    a.State
FROM Person AS p
    LEFT JOIN Address AS a ON p.PersonId = a.PersonId;
# 一定要注意，JOIN得和ON一起使用，而不能使用WHERE