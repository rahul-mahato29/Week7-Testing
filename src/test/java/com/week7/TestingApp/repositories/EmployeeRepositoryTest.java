package com.week7.TestingApp.repositories;

import com.week7.TestingApp.TestContainerConfiguration;
import com.week7.TestingApp.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(TestContainerConfiguration.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;     //injecting, so that we can use it & test the method

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .name("rahul")
                .email("rahul@gmail.com")
                .salary(100L)
                .build();
    }

    //happy-case
    @Test
    void testFindByEmail_whenEmailIsPresent_thenReturnEmployee() {
        //Arrange
        employeeRepository.save(employee);

        //Act
        List<Employee> employeeList = employeeRepository.findByEmail(employee.getEmail());

        //Assert
        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isNotEmpty();
        assertThat(employeeList.get(0).getEmail()).isEqualTo(employee.getEmail());
    }

    //non-happy-case
    @Test
    void testFindByEmail_whenEmailIsNotFound_thenReturnEmptyEmployeeList() {
        //Arrange
        String email = "notPresent.123@gmail.com";

        //Act
        List<Employee> employeeList = employeeRepository.findByEmail(email);

        //Assert
        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isEmpty();   //given email is not present, then employeeList will be empty only.
    }
}