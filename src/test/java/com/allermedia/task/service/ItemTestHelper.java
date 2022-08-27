package com.allermedia.task.service;

import com.allermedia.task.dto.ContentMarketingItem;

public class ItemTestHelper {


    // create a test user data
    public static ContentMarketingItem getItemTestData(String type) {
        ContentMarketingItem articleItem = new ContentMarketingItem();
        articleItem.setType(type);
        return articleItem;
    }

}
