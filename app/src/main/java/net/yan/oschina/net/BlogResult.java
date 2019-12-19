package net.yan.oschina.net;

import net.yan.oschina.Notice;
import net.yan.oschina.news.entity.Blog;

import java.util.List;

public class BlogResult {

    private Notice notice;
    private List<Blog> bloglist;

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setBloglist(List<Blog> bloglist) {
        this.bloglist = bloglist;
    }

    public List<Blog> getBloglist() {
        return bloglist;
    }
}
