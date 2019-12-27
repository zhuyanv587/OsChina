package net.yan.oschina.tweet.entity;

public class Subject {
    private long id;
    private String pubDate;
    private String body;
    private String author;
    private long authorid;
    private long commentCount;
    private String portrait;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthorid(long authorid) {
        this.authorid = authorid;
    }

    public long getAuthorid() {
        return authorid;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getPortrait() {
        return portrait;
    }
}
