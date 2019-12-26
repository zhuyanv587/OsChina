package net.yan.oschina.my.entity;

import net.yan.oschina.Notice;

import java.util.List;

public class MyDetail {

    private int gender;
    private String joinTime;
    private String city;
    private int fansCount;
    private String portrait;
    private List<String> expertise;
    private List<String> platforms;
    private long uid;
    private String lastLoginTime;
    private String province;
    private String name;
    private int followersCount;
    private int favoriteCount;
    private Notice notice;

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getGender() {
        return gender;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setExpertise(List<String> expertise) {
        this.expertise = expertise;
    }

    public List<String> getExpertise() {
        return expertise;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getUid() {
        return uid;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public Notice getNotice() {
        return notice;
    }
}
