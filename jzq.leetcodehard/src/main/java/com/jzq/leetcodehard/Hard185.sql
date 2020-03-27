# Write your MySQL query statement below
select d.Name as Department, rank.Name as Employee, rank.Salary as Salary
from 
    (select e1.Name, 
        e1.Salary, 
        e1.DepartmentId, 
        (select count(distinct e2.Salary)
            from Employee e2 
            where e1.DepartmentId = e2.DepartmentId 
            and e2.Salary >= e1.Salary) as Rank
    from Employee e1) as rank, Department d
where rank.Rank <= 3
and rank.DepartmentId = d.Id