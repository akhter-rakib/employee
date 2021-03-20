package com.rakib.employee.repository;

import com.rakib.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("from Employee where id = :employeeId and activeStatus = :activeStatus")
    Employee getByIdAndActiveStatusTrue(@Param("employeeId") Long employeeId, @Param("activeStatus") Integer activeStatus);
}
