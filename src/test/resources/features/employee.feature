Feature: Employee
  As a user, I want to update and delete information for employee
  http://dummy.restapiexample.com/api

  Scenario: Get all Employee - GET: /v1/employee
    When requesting get all employee
    Then the request get all employee returned successful

#  Scenario: Update Employee - PUT: /v1/update/{id}
#    When requesting update employee
#    Then the request update employee returned successful
#
#  Scenario: Delete Employee - DELETE: /v1/delete/{id}
#    When requesting delete an employee
#    Then the request delete employee successful
