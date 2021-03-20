package com.rakib.employee.controller;

import com.rakib.employee.annotations.ApiController;
import com.rakib.employee.dto.EmployeeDto;
import com.rakib.employee.dto.Response;
import com.rakib.employee.service.EmployeeService;
import com.rakib.employee.util.UrlConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@ApiController
@RequestMapping(UrlConstraint.EmployeeManagement.ROOT)
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Response saveEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.save(employeeDto);
    }

    @GetMapping
    public Response getAll() {
        return employeeService.getAll();
    }

    @GetMapping(value = UrlConstraint.EmployeeManagement.GET)
    public Response getById(@PathVariable("employeeId") Long employeeId) {
        return employeeService.getById(employeeId);
    }

    @PutMapping(value = UrlConstraint.EmployeeManagement.PUT)
    public Response updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable("employeeId") Long employeeId) {
        return employeeService.update(employeeId, employeeDto);
    }
}
