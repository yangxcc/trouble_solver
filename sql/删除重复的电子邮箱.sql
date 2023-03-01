# leetcode 196 删除重复的电子邮箱 https://leetcode.cn/problems/delete-duplicate-emails/
DELETE FROM Person
WHERE id NOT IN (
        SELECT t.id
        FROM (
                SELECT MIN(id) AS id
                FROM Person
                GROUP BY Email
            ) AS t
    )

-- MySQL中不允许在同一张表上先select，再update或者delete。
-- 所以不能够直接 DELETE FROM Person WHERE id NOT IN (SELECT MIN(id) FROM Person GROUP BY Email)
-- 所以需要中间套个表。    