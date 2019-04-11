package com.automation.api.service.article;

import com.automation.api.endpoint.EndpointCaller;
import com.automation.api.endpoint.Endpoints;
import com.automation.model.article.Article;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;

public class ArticleService {
    private Endpoints endPoints = Endpoints.get();
    private EndpointCaller caller = EndpointCaller.get();
    private Article article;
    private List<Article> articles;

    public void createArticle(String contentType, Article article){
        this.article = caller.perform(endPoints.articleEndpoint().createArticle(contentType, article));
    }

    public void checkCreateArticleSuccessful(){
        assertNotNull(article);
        assertNotNull(article.id);
        assertNotNull(article.userId);
        assertNotNull(article.title);
        assertNotNull(article.body);
    }

    public void getAllArticle(){
        articles = caller.perform(endPoints.articleEndpoint().getAllArticle());
    }

    public void checkGetAllArticleSuccessful(){
        assertNotNull(articles);
        assertNotNull(articles.get(0).id);
        assertNotNull(articles.get(0).userId);
        assertNotNull(articles.get(0).title);
        assertNotNull(articles.get(0).body);
    }
}
