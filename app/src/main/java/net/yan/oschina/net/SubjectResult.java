package net.yan.oschina.net;

import net.yan.oschina.Notice;
import net.yan.oschina.tweet.entity.Hot;
import net.yan.oschina.tweet.entity.Subject;

import java.util.List;

public class SubjectResult {
    private Notice notice;
    private List<Subject> tweetlist;

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setTweetlist(List<Subject> tweetlist) {
        this.tweetlist = tweetlist;
    }

    public List<Subject> getTweetlist() {
        return tweetlist;
    }
}
