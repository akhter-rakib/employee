package com.rakib.employee.service.Impl;

import com.rakib.employee.dto.EmployeeDto;
import com.rakib.employee.dto.Response;
import com.rakib.employee.entity.Employee;
import com.rakib.employee.enums.ActiveStatus;
import com.rakib.employee.repository.EmployeeRepository;
import com.rakib.employee.service.EmployeeService;
import com.rakib.employee.util.ResponseBuilder;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;
    private final String root = "Employee";

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response save(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        Employee empl = employeeRepository.save(employee);
        if (empl != null) {
            return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root + "Has been Created", null);
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    @Override
    public Response update(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.getByIdAndActiveStatusTrue(id, ActiveStatus.ACTIVE.getValue());
        if (employee != null) {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            employee = modelMapper.map(employeeDto, Employee.class);
            employee = employeeRepository.save(employee);
            if (employee != null) {
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " updated Successfully", null);
            }

            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
    }

    @Override
    public Response getById(Long id) {
        Employee employee = employeeRepository.getByIdAndActiveStatusTrue(id, ActiveStatus.ACTIVE.getValue());
        if (employee != null) {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
            return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retrieved Successfully", employeeDto);
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
    }

    @Override
    public Response del(Long id) {
        return null;
    }

    @Override
    public Response getAll() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeDto> employeeDtoList = this.getEmployee(employeeList);
        if (employeeDtoList.isEmpty() || employeeDtoList == null) {
            return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "There is No  Employee", null);
        }
        return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + "Data Retrieve Successfully", employeeDtoList);
    }

    private List<EmployeeDto> getEmployee(List<Employee> employees) {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employees.forEach(employee -> {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
            employeeDtoList.add(employeeDto);
        });
        return employeeDtoList;
    }
}
