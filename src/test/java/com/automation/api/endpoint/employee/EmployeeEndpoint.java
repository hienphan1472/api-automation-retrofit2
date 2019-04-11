package com.automation.api.endpoint.employee;

import com.automation.model.employee.Employee;
import com.automation.model.employee.EmployeeDTO;
import com.automation.model.message.Success;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface EmployeeEndpoint {

    @GET("v1/employees")
    Call<List<EmployeeDTO>> getAllEmployee();

    @DELETE("v1/delete/{id}")
    Call<Success> deleteAnEmployee(
            @Path("id") String id
    );

    @PUT("v1/update/{id}")
    Call<Employee> updateEmployee(
        @Path("id") String id,
        @Body Employee employeeBody
    );
}
