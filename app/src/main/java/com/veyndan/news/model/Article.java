package com.veyndan.news.model;

public class Article {
    private String title;
    private String publisher;
    private String pubDate;
    private String img;

    public Article(String title, String publisher, String pubDate, String img) {
        this.title = title;
        this.publisher = publisher;
        this.pubDate = pubDate;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getImg() {
        return img;
    }
}
