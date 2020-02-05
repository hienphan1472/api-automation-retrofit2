package com.automation.api.service.employee;

import com.automation.api.endpoint.EndpointCaller;
import com.automation.api.endpoint.Endpoints;
import com.automation.model.SuccessResponse;
import com.automation.model.employee.Employee;
import com.automation.model.employee.EmployeeDTO;
import com.automation.model.message.Success;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class EmployeeService {

    private Endpoints endPoints = Endpoints.get();
    private EndpointCaller caller = EndpointCaller.get();
    private SuccessResponse<List<EmployeeDTO>> successResponse;
    private Success success;
    private Employee employee;

    private String idExpected = "1";
    private String ageValue = "25";
    private String nameValue = "Scrum Breakfast";
    private String salaryValue = "2000";

    // --Sending request--
    public void getAllEmployee(){
        successResponse = caller.perform(endPoints.employeeEndpoint().getAllEmployee());
    }

    public void updateEmployee(){
        employee = caller.perform(endPoints.employeeEndpoint().updateEmployee(idExpected, createEmployeeBody()));
    }


    // -- checking status or returned data--
    public void checkGetAllEmployeeSuccessful(){
        assertNotNull(successResponse);
        System.out.println(successResponse.getData().get(0).name);
    }

    public void deleteAnEmployee() {
        success = caller.perform(endPoints.employeeEndpoint().deleteAnEmployee("1"));
    }

    public void checkDeleteAnEmployeeRequest() {
        assertNotNull(success);
        assertEquals("successfully! deleted Records", success.textObject.text);
    }

    public void checkUpdateEmployeeSuccessful(){
        assertNotNull(employee);
        assertEquals(ageValue, employee.age);
        assertEquals(nameValue, employee.name);
        assertEquals(salaryValue, employee.salary);
    }

    private Employee createEmployeeBody(){
        Employee employee = new Employee();
        employee.age = ageValue;
        employee.name = nameValue;
        employee.salary = salaryValue;
        return employee;
    }
}
