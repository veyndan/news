package com.veyndan.news.model;

import android.support.annotation.Nullable;

public class Article {
    private String title;
    private String publisher;
    private String publisherImg;
    private String img;

    public Article(String publisher, String publisherImg, String title, @Nullable String img) {
        this.title = title;
        this.publisher = publisher;
        this.publisherImg = publisherImg;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublisherImg() {
        return publisherImg;
    }

    public String getImg() {
        return img;
    }
}
