package net.yan.oschina.news.entity;

public class Blog {

    private int id;
    private String pubDate;
    private String author;
    private String title;
    private long authorid;
    private int type;
    private int commentCount;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    public void setAuthorid(long authorid) {
        this.authorid = authorid;
    }

    public long getAuthorid() {
        return authorid;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getCommentCount() {
        return commentCount;
    }
}
