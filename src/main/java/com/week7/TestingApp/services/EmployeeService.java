package com.week7.TestingApp.services;

import com.week7.TestingApp.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto getEmployeeById(Long Id);
    EmployeeDto createNewEmployee(EmployeeDto employeeDto);
    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
    void deleteEmployee(Long id);
}
