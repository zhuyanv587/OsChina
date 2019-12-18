package net.yan.oschina.net;

import net.yan.oschina.Notice;
import net.yan.oschina.news.entity.Information;

import java.util.List;

public class InformationResult {
    private List<Information> newslist;
    private Notice notice;

    public List<Information> getNewsList() {
        return newslist;
    }

    public void setNewsList(List<Information> newsList) {
        this.newslist = newsList;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }
}
