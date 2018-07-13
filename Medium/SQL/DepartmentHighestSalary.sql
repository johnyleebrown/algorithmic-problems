-- 184
-- Medium
-- Write a SQL query to find employees who have the highest salary in each of the departments.

SELECT
  d.name AS Department,
  e.name AS Employee,
  Salary

FROM Employee e
  JOIN Department d
    ON e.DepartmentId = d.Id

WHERE (e.departmentid, Salary)
      IN (SELECT
            Employee.departmentid,
            MAX(Salary)
          FROM Employee
          GROUP BY DepartmentId)