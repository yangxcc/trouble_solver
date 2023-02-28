# leetcode 178 分数排名 https://leetcode.cn/problems/rank-scores
SELECT score,
    DENSE_RANK() OVER (
        ORDER BY score DESC
    ) AS `rank`
FROM Scores;

/*
四个排名函数：
1. ROW_NUMBER() OVER ([PARTITION_BY_clause | ORDER_BY_clause]) 为每一行生成一个行号，按照顺序依次 +1 递增。分组内序列的最大值就是该分组内的行的数目。
2. RANK() OVER ([PARTITION_BY_clause | ORDER_BY_clause]) 为每一行生成一个行号，如果按照ORDER_BY排序，对于相同的值生成相同的序号，接下来的序号是不连续的
3. DENSE_RANK() OVER ([PARTITION_BY_clause | ORDER_BY_clause]) 稠密排序，与RANK()不同的地方在于即使有相同的值，生成相同的序号后，记下来的序号仍然是连续的
4. NTILE() OVER ([PARTITION_BY_clause | ORDER_BY_clause]) 按照指定数目将数据分组，比如NTILE(4) OVER (ORDER BY rate)按照分数将所有数据分成四组
*/
