package net.yan.oschina.net;

import net.yan.oschina.Notice;
import net.yan.oschina.tweet.entity.Me;

import java.util.List;

public class MyResult {

    private Notice notice;
    private List<Me> tweetlist;

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setTweetlist(List<Me> tweetlist) {
        this.tweetlist = tweetlist;
    }

    public List<Me> getTweetlist() {
        return tweetlist;
    }
}
