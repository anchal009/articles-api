package com.allermedia.task.service;

import com.allermedia.task.TaskApplication;
import com.allermedia.task.dto.ApiResponse;
import com.allermedia.task.dto.Article;
import com.allermedia.task.dto.ArticleItem;
import com.allermedia.task.dto.ContentMarketingItem;
import com.allermedia.task.exception.ApiProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static com.allermedia.task.service.ItemTestHelper.getItemTestData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TaskApplication.class)
public class ApiServiceTest {

    @Mock
    private ApiService apiServiceMock;

    @Autowired
    private ApiService apiServiceNotMocked;

    @Test
    public void given_articles_contentmarketing_test_if_ads_added_or_not() {
        ArticleItem articleItem1 = getItemTestData("Article");
        ArticleItem articleItem2 = getItemTestData("Article");
        ArticleItem articleItem3 = getItemTestData("Article");
        ArticleItem articleItem4 = getItemTestData("Article");
        ArticleItem articleItem5 = getItemTestData("Article");
        ArticleItem articleItem6 = getItemTestData("Article");
        ArticleItem articleItem7 = getItemTestData("Article");
        ArticleItem articleItem8 = getItemTestData("Article");
        ArticleItem articleItem9 = getItemTestData("Article");
        ArticleItem articleItem10 = getItemTestData("Article");
        ArticleItem articleItem11 = getItemTestData("Article");
        List<ArticleItem> articleList = Arrays.asList(articleItem1, articleItem2, articleItem3,
                articleItem4, articleItem5, articleItem6, articleItem7, articleItem8, articleItem9,
                articleItem10, articleItem11);
        ApiResponse apiResponse = new ApiResponse();
        Article article = new Article();
        apiResponse.setHttpStatus(200);
        article.setItems(articleList);
        apiResponse.setResponse(article);
        when(apiServiceMock.getArticles("https://storage.googleapis.com/aller-structure-task/articles.json")).thenReturn(apiResponse);
        ApiResponse apiServiceArticles = apiServiceMock.getArticles("https://storage.googleapis.com/aller-structure-task/articles.json");
        assertNotNull(apiServiceArticles);
        assertEquals(11, apiServiceArticles.getResponse().getItems().size());

        ContentMarketingItem contentMarketingItem = getItemTestData("ContentMarketing");
        List<ArticleItem> contentList = List.of(contentMarketingItem);
        ApiResponse apiResponseContentMarketing = new ApiResponse();
        Article contentMarketingArticle = new Article();
        apiResponseContentMarketing.setHttpStatus(200);
        contentMarketingArticle.setItems(contentList);
        apiResponseContentMarketing.setResponse(contentMarketingArticle);
        when(apiServiceMock.getContentMarketingArticles("https://storage.googleapis.com/aller-structure-task/contentmarketing.json")).thenReturn(apiResponseContentMarketing);
        ApiResponse apiServiceContentMarketing = apiServiceMock.getContentMarketingArticles("https://storage.googleapis.com/aller-structure-task/contentmarketing.json");
        assertNotNull(apiServiceContentMarketing);
        assertEquals(1, apiServiceContentMarketing.getResponse().getItems().size());


        ArticleItem articleAdItem = getItemTestData("Ad");
        List<ArticleItem> finalReturnItemList = Arrays.asList(articleItem1, articleItem2, articleItem3,
                articleItem4, articleItem5, contentMarketingItem, articleItem6, articleItem7, articleItem8, articleItem9,
                articleItem10, articleAdItem, articleItem11);
        ApiResponse apiResponseResult = new ApiResponse();
        Article articleResult = new Article();
        apiResponseResult.setHttpStatus(200);
        articleResult.setItems(finalReturnItemList);
        apiResponseResult.setResponse(articleResult);

        try {
            ApiResponse apiResult = apiServiceNotMocked.getApiResponse(apiServiceArticles, apiServiceContentMarketing);
            assertNotNull(apiResult);
            assertEquals(13, apiResult.getResponse().getItems().size());
        } catch (ApiProcessingException e) {
            e.printStackTrace();
        }
    }
}
