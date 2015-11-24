package com.veyndan.news.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "rss", strict = false)
public class Feed {
    // TODO article.publisher doesn't work as articles defined too early, not allowing for the
    // new value of publisher to be inherited. Look at documentation of simplexml on inheritance.
    @Path("channel")
    @ElementList(name = "item", inline = true)
    public List<Article> articles;

    @Root(name = "item", strict = false)
    public static class Article extends Feed {
        @Element
        public String title;

        @Element
        public String pubDate;


        @Root(name = "image", strict = false)
        public static class Image {
            @Element(name = "url")
            public String url;
        }
    }
}
