package com.automation.model.employee;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeDTO {
    public String id;

    @JsonProperty("employee_name")
    public String name;

    @JsonProperty("employee_salary")
    public String salary;

    @JsonProperty("employee_age")
    public String age;

    public String profile_image;
}
