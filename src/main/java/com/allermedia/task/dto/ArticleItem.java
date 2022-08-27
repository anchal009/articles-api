package com.allermedia.task.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ArticleItem {
    public String type;
    public String harvesterId;
    @JsonProperty("cerebro-score")
    public double cerebroScore;
    public String url;
    public String title;
    public String cleanImage;
    public String commercialPartner;
    public String logoURL;
}
