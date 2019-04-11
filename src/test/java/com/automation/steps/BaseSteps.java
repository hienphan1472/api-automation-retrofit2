package com.automation.steps;

import com.automation.api.service.Services;
import com.automation.api.service.article.ArticleService;
import com.automation.api.service.employee.EmployeeService;
import com.automation.support.Configuration;


public class BaseSteps {

    // Init services
    protected EmployeeService employeeService = Services.get().getEmployeeService();
    protected ArticleService articleService = Services.get().getArticleService();
    protected Configuration configuration = Configuration.get();

}