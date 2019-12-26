package net.yan.oschina.net;

import net.yan.oschina.Notice;
import net.yan.oschina.news.entity.Focus;

import java.util.List;

public class FousResult {

    private Notice notice;
    private List<Focus> tweetlist;

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public List<Focus> getTweetlist() {
        return tweetlist;
    }

    public void setTweetlist(List<Focus> tweetlist) {
        this.tweetlist = tweetlist;
    }
}
