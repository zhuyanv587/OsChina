package net.yan.oschina.net;

import net.yan.oschina.Notice;
import net.yan.oschina.tweet.entity.Hot;
import net.yan.oschina.tweet.entity.Thrum;

import java.util.List;

public class ThrumResult {

    private Notice notice;
    private List<Thrum> tweetlist;

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setTweetlist(List<Thrum> tweetlist) {
        this.tweetlist = tweetlist;
    }

    public List<Thrum> getTweetlist() {
        return tweetlist;
    }

}
