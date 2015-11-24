package com.veyndan.news.model;

public class Article {
    private String title;
    private String publisher;
    private String publisherImg;
    private String pubDate;
    private String img;

    public Article(String title, String publisher, String publisherImg, String pubDate, String img) {
        this.title = title;
        this.publisher = publisher;
        this.publisherImg = publisherImg;
        this.pubDate = pubDate;
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

    public String getPubDate() {
        return pubDate;
    }

    public String getImg() {
        return img;
    }
}
