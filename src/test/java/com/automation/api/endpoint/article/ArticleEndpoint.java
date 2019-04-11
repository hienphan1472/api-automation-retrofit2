package com.automation.api.endpoint.article;

import com.automation.model.article.Article;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ArticleEndpoint {

    @POST("/posts")
    Call<Article> createArticle(
            @Header("Content-type") String contentType,
            @Body Article article
    );

    @GET("/posts")
    Call<List<Article>> getAllArticle();

}
