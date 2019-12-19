package net.yan.oschina.net;

import net.yan.oschina.Notice;
import net.yan.oschina.news.entity.Question;

import java.util.List;

public class QuestionResult {

    private List<Question> post_list;
    private Notice notice;

    public void setPost_list(List<Question> post_list) {
        this.post_list = post_list;
    }

    public List<Question> getPost_list() {
        return post_list;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public Notice getNotice() {
        return notice;
    }
}
