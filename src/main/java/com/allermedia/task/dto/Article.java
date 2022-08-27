package com.allermedia.task.dto;

import lombok.Data;

import java.util.List;

@Data
public class Article {
    public List<ArticleItem> items;
}
