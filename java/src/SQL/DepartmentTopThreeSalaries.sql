-- 185
-- Hard
--
-- The Employee table holds all employees. Every employee has an Id, and there is also a column for the department Id.
-- The Department table holds all departments of the company.
-- Write a SQL query to find employees who earn the top three salaries in each of the department.

SELECT
  d.Name  AS 'Department',
  e1.Name AS 'Employee',
  e1.Salary
FROM
  Employee e1
  JOIN
  Department d ON e1.DepartmentId = d.Id
WHERE
  3 > (SELECT COUNT(DISTINCT e2.Salary)
       FROM
         Employee e2
       WHERE
         e2.Salary > e1.Salary
         AND e1.DepartmentId = e2.DepartmentId
  );
