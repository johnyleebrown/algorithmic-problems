--
-- 180
-- Medium
--
-- Write a SQL query to find all numbers that appear at least three times consecutively.

-- beats 75%
SELECT DISTINCT l1.num AS ConsecutiveNums
FROM Logs l1
  JOIN Logs l2
    ON l1.num = l2.num
  JOIN Logs l3
    ON l1.num = l3.num
WHERE l2.id = l1.id - 1 AND l3.id = l1.id - 2

-- beats 97%
SELECT DISTINCT num AS ConsecutiveNums
FROM
  (SELECT
     num,
     CASE
     WHEN @record = num
       THEN @count := @count + 1
     WHEN @record <> @record := num
       THEN @count := 1 END AS n
   FROM
     Logs, (SELECT
              @count := 0,
              @record := (SELECT num
                          FROM Logs
                          LIMIT 0, 1)) r
  ) a
WHERE a.n >= 3