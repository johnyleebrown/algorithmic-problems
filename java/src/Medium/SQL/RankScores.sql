-- 178
-- Medium
-- Write a SQL query to rank scores.
-- If there is a tie between two scores, both should have the same ranking.
-- Note that after a tie, the next ranking number should be the next consecutive integer value.
-- In other words, there should be no "holes" between ranks.

-- lol
SELECT
  Score,
  Rank
FROM (
       SELECT
         score,
         (SELECT @r3 := @r3 + 1) AS ra3,
         IF(score = (
           SELECT score
           FROM (SELECT
                   s.score,
                   (SELECT @r2 := @r2 + 1) AS ra2
                 FROM Scores s,
                   (SELECT @r2 := 0) r2
                 ORDER BY s.score DESC
                ) t
           WHERE ra2 = @r3 - 1
         ), (SELECT @r := cast(@r AS UNSIGNED)), (SELECT @r := cast(@r + 1 AS UNSIGNED))) AS Rank
       FROM Scores,
         (SELECT @r := 0) rr,
         (SELECT @r3 := 0) rrr
       ORDER BY score DESC
     ) tt;

-- or
SELECT
  Score,
  @rank := @rank + (@prev <> (@prev := Score)) Rank
FROM
  Scores,
  (SELECT
     @rank := 0,
     @prev := -1) init
ORDER BY Score DESC;

-- smart
SELECT
  Score,
  (SELECT count(*)
   FROM (SELECT DISTINCT Score s
         FROM Scores) tmp
   WHERE s >= Score) Rank
FROM Scores
ORDER BY Score DESC