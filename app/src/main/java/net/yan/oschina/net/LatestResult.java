package net.yan.oschina.net;

import net.yan.oschina.Notice;
import net.yan.oschina.tweet.entity.Latest;

import java.util.List;

public class LatestResult {

    private Notice notice;
    private List<Latest> tweetlist;

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setTweetlist(List<Latest> tweetlist) {
        this.tweetlist = tweetlist;
    }

    public List<Latest> getTweetlist() {
        return tweetlist;
    }
}
