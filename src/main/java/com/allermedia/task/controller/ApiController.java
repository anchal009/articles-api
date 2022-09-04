package com.allermedia.task.controller;

import com.allermedia.task.dto.ApiResponse;
import com.allermedia.task.exception.ApiProcessingException;
import com.allermedia.task.service.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/articles")
@Slf4j
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping
    public ResponseEntity<ApiResponse> getFormattedJSON() throws ApiProcessingException {

        ApiResponse articles = apiService.getArticles("https://storage.googleapis.com/aller-structure-task/articles.json");

        ApiResponse contentMarketing = apiService.getContentMarketingArticles("https://storage.googleapis.com/aller-structure-task/contentmarketing.json");

        ApiResponse finalApiResponse = apiService.getApiResponse(articles, contentMarketing);
        return ResponseEntity.ok(finalApiResponse);
    }
}
