package net.yan.oschina.net;

public class URLList {
    public static String domain = "http://www.oschina.net";
    public final static String GET_INFORMATION = domain + "/action/openapi/news_list?access_token=";
    public final static String GET_RECOMMEND = domain + "/action/openapi/blog_recommend_list?access_token=";
    public final static String GET_BLOG = domain + "/action/openapi/blog_list?access_token=";
    public final static String GET_QUESTION = domain + "/action/openapi/post_list?access_token=";
    public final static String GET_LATEST = domain + "/action/openapi/tweet_list?access_token=";
    public final static String GET_HOT = domain + "/action/openapi/tweet_list?access_token=";
    public final static String GET_SOFTWARE = domain + "/action/openapi/project_tag_list?access_token=";
    public final static String SEND_TWEET = domain+ "/action/openapi/tweet_pub";
    public final static String SEND_QUESTION = domain+"/action/openapi/post_pub";
}
