package net.yan.oschina.news.entity;

public class Question{

    private long answerCount;
    private String author;
    private long id;
    private long viewCount;
    private String title;
    private String portrait;
    private long authorid;
    private String pubDate;

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public long getAnswerCount() {
        return answerCount;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setAuthorid(long authorid) {
        this.authorid = authorid;
    }

    public long getAuthorid() {
        return authorid;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getPubDate() {
        return pubDate;
    }
}
