package com.learnwebclient.service;


import com.learnwebclient.dto.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


// http://localhost:8081/employeeservice/v1/allEmployees

public class EmployeeRestClientTest {

    private static final String baseUrl = "http://localhost:8081/employeeservice/";
    private WebClient webClient = WebClient.create(baseUrl);

    EmployeeRestClient employeeRestClient = new EmployeeRestClient(webClient);

    @Test
    void retrieveAllEmployees() {

        List<Employee> employeeList = employeeRestClient.retrieveAllEmployees();
        System.out.println("employeeList = " + employeeList);
        assertTrue(employeeList.size() > 0);

    }

    @Test
    void retrieveEmployeeById() {

        int employeeId = 1;
        Employee employee = employeeRestClient.retrieveEmployeeById(employeeId);
        assertEquals("Chris" , employee.getFirstName());

    }

    @Test
    void retrieveEmployeeById_notFound() {

        int employeeId = 10;
        assertThrows(WebClientResponseException.class , () -> employeeRestClient.retrieveEmployeeById(employeeId));

    }

    @Test
    void retrieveEmployeeByName() {
        String name = "Chris";

        List<Employee> employees = employeeRestClient.retrieveEmployeeByName(name);

        assertTrue(employees.size() >0);

    }
}
