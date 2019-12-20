package net.yan.oschina.net;

import net.yan.oschina.Notice;
import net.yan.oschina.tweet.entity.Hot;
import net.yan.oschina.tweet.entity.Latest;

import java.util.List;

public class HotResult {

    private Notice notice;
    private List<Hot> tweetlist;

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setTweetlist(List<Hot> tweetlist) {
        this.tweetlist = tweetlist;
    }

    public List<Hot> getTweetlist() {
        return tweetlist;
    }
}
