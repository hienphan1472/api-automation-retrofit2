package com.automation.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EmployeeSteps extends BaseSteps {

    @When("^requesting get all employee$")
    public void requesting_get_all_employee() {
        employeeService.getAllEmployee();
    }

    @Then("^the request get all employee returned successful$")
    public void the_request_get_all_employee_returned_successful() {
        employeeService.checkGetAllEmployeeSuccessful();
    }

    @When("^requesting update employee$")
    public void requesting_update_employee() {
        employeeService.updateEmployee();
    }

    @Then("^the request update employee returned successful$")
    public void the_request_update_employee_returned_successful() {
        employeeService.checkUpdateEmployeeSuccessful();
    }


    @When("^requesting delete an employee$")
    public void requesting_delete_an_employee() {
        employeeService.deleteAnEmployee();
    }

    @Then("^the request delete employee successful$")
    public void the_request_delete_employee_successful() {
        employeeService.checkDeleteAnEmployeeRequest();
    }
}
