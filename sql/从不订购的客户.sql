# leetcode 183 从不订购的客户 https://leetcode.cn/problems/customers-who-never-order/
SELECT Name AS Customers
FROM Customers
WHERE Customers.id NOT IN (
        SELECT CustomerId
        FROM Orders
    );