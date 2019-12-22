package net.yan.oschina.net;

import net.yan.oschina.news.entity.Software;

import java.util.List;

public class SoftwareResult {

    private List<Software> projectlist;
    private int count;

    public void setProjectlist(List<Software> projectlist) {
        this.projectlist = projectlist;
    }

    public List<Software> getProjectlist() {
        return projectlist;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
