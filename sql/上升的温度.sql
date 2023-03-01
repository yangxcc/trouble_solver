# leetcode 197 上升的温度 https://leetcode.cn/problems/rising-temperature/
SELECT W1.id
FROM Weather W1, Weather W2
WHERE DATEDIFF(W1.recordDate, W2.recordDate) = 1
AND W1.temperature > W2.temperature
