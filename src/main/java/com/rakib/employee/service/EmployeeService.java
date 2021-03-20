package com.rakib.employee.service;

import com.rakib.employee.dto.EmployeeDto;
import com.rakib.employee.dto.Response;

public interface EmployeeService {
    public Response save(EmployeeDto employeeDto);

    public Response update(Long id, EmployeeDto employeeDto);

    public Response getById(Long id);

    public Response del(Long id);

    public Response getAll();
}
