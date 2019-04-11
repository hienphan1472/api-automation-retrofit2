package com.automation.steps;

import com.automation.model.article.Article;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ArticleSteps extends BaseSteps {

    @When("^requesting create an article$")
    public void requesting_create_an_article_resource() {
        articleService.createArticle(getDefaultHeader(), getArticle());
    }

    @Then("^the request creating an article successfully$")
    public void the_request_creating_an_article_successfully() {
        articleService.checkCreateArticleSuccessful();
    }

    @When("^requesting get all article$")
    public void requesting_get_all_articles() {
        articleService.getAllArticle();
    }

    @Then("^the request get all article successfully$")
    public void the_request_get_all_articles_successfully() {
        articleService.checkGetAllArticleSuccessful();
    }

    private String getDefaultHeader(){
        return configuration.getTestProperty("header.content.type.value");
    }

    private Article getArticle(){
        String title = "Title of the post";
        String body = "Body of the post";
        String userID = "TTT_01_03";
        Article article = new Article();
        article.body = body;
        article.title = title;
        article.userId = userID;
        return article;
    }

}
