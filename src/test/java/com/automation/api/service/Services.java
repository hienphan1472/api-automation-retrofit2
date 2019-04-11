package com.automation.api.service;

import com.automation.api.service.article.ArticleService;
import com.automation.api.service.employee.EmployeeService;

public class Services {

    // Singleton
    private static Services instance;
    private ArticleService articleService;
    private EmployeeService employeeService;

    public static Services get() {

        if (instance == null) {
            instance = new Services();
        }
        return instance;

    }

    public ArticleService getArticleService() {
        if (articleService == null) {
            articleService = new ArticleService();
        }
        return articleService;
    }

    public EmployeeService getEmployeeService() {
        if (employeeService == null) {
            employeeService = new EmployeeService();
        }
        return employeeService;
    }
}
