package com.classified.database.models;

 /*create table newsarticle(
         newsid serial primary key,
         imageurl varchar(254),
         title varchar(254) not null unique,
         contents text not null,
         category varchar(254) not null,
         writername varchar(254) not null,
         publisheddate date not null,
         writerid integer references newswriter(writerid),
         viewcount integer
         );*/

public class NewsArticle {
    private Integer newsId;
    private String imageUrl;
    private String title;
    private String contents;
    private String category;
    private String publishedDate;
    private Integer writerId;
    private String writername;
    private Integer viewCount;

    public NewsArticle() {
    }

    public NewsArticle(Integer newsId, String imageUrl, String title, String contents, String category, String writername,Integer writerid, Integer viewCount) {
        this.newsId = newsId;
        this.imageUrl = imageUrl;
        this.title = title;
        this.contents = contents;
        this.category = category;
        this.writerId = writerid;
        this.writername = writername;
        this.viewCount = viewCount;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getcontents() {
        return contents;
    }

    public void setcontents(String contents) {
        this.contents = contents;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Integer getWriterId() {
        return writerId;
    }

    public void setWriterId(Integer writer) {
        this.writerId = writer;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getWritername() {
        return writername;
    }

    public void setWritername(String writername) {
        this.writername = writername;
    }

    @Override
    public String toString() {
        return "NewsArticle{" +
                "newsId=" + newsId +
                ", imageUrl='" + imageUrl + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", category='" + category + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", writerId=" + writerId +
                ", writername='" + writername + '\'' +
                ", viewCount=" + viewCount +
                '}';
    }
}
