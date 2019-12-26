package net.yan.oschina.news.entity;

import net.yan.oschina.Notice;

import java.util.List;

public class NewsInformation {
    private int id;
    private String body;
    private String pubDate;
    private String author;
    private String title;
    private int authorid;
    private List<News> relativies;
    private Notice notice;
    private int favorite;
    private int commentCount;
    private String url;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
    }

    public int getAuthorid() {
        return authorid;
    }

    public void setRelativies(List<News> relativies) {
        this.relativies = relativies;
    }

    public List<News> getRelativies() {
        return relativies;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
