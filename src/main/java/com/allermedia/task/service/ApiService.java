package com.allermedia.task.service;

import com.allermedia.task.dto.AdItem;
import com.allermedia.task.dto.ApiResponse;
import com.allermedia.task.dto.Article;
import com.allermedia.task.dto.ArticleItem;
import com.allermedia.task.exception.ApiProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class ApiService {
    public ApiResponse getArticles(String url) throws IllegalArgumentException {
        return getForObject(url);
    }

    public ApiResponse getContentMarketingArticles(String url) throws IllegalArgumentException {
        return getForObject(url);
    }

    private ApiResponse getForObject(String url) {
        return new RestTemplate().getForObject(url, ApiResponse.class);
    }

    public ApiResponse getApiResponse(ApiResponse articles, ApiResponse contentMarketing) throws ApiProcessingException {
        ApiResponse finalApiResponse = new ApiResponse();
        AtomicInteger articleCounter = new AtomicInteger();
        AtomicInteger contentMarketingCounter = new AtomicInteger(1);

        List<ArticleItem> articleItems = new ArrayList<>();
        try {
            if (null != articles && null!= contentMarketing
            && articles.getResponse().getItems().size() > 0
            && contentMarketing.getResponse().getItems().size() > 0) {
                articles.getResponse().getItems()
                        .forEach(articleItem -> {
                            articleCounter.getAndIncrement();
                            articleItems.add(articleItem);
                            if (articleCounter.get() % 5 == 0 && (contentMarketingCounter.get() <= contentMarketing.getResponse().getItems().size())) {
                                articleItems.add(contentMarketing.getResponse().getItems().get(contentMarketingCounter.get() - 1));
                                contentMarketingCounter.getAndIncrement();
                            } else if (articleCounter.get() % 5 == 0) {
                                AdItem adArticleItem = new AdItem();
                                adArticleItem.setType("Ad");
                                articleItems.add(adArticleItem);
                            }
                        });
                finalApiResponse.setHttpStatus(200);
                Article article = new Article();
                article.setItems(articleItems);
                finalApiResponse.setResponse(article);
                return finalApiResponse;
            }
        } catch (Exception e) {
          throw new ApiProcessingException(e.getMessage());
        }
        finalApiResponse.setHttpStatus(500);
        return finalApiResponse;
    }
}
