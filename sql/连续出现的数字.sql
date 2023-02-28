# leetcode 180 连续出现的数字 https://leetcode.cn/problems/consecutive-numbers/
SELECT l1.Num AS ConsecutiveNums
FROM Logs l1,
    Logs l2,
    Logs l3
WHERE l1.Id = l2.Id - 1
    AND l2.Id = l3.Id - 1
    AND l1.Num = l2.Num
    AND l2.Num = l3.Num;

-- 因为题目中要求的是连续出现三次的数字，所以这里是使用了三表连接的做法    