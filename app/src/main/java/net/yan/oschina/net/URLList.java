package net.yan.oschina.net;

public class URLList {
    public static String domain = "http://www.oschina.net";
    //获取新闻列表
    public final static String GET_INFORMATION = domain + "/action/openapi/news_list?access_token=";
    //获取博客推荐列表
    public final static String GET_RECOMMEND = domain + "/action/openapi/blog_recommend_list?access_token=";
    //获取博客列表
    public final static String GET_BLOG = domain + "/action/openapi/blog_list?access_token=";
    //获取讨论区的帖子列表
    public final static String GET_QUESTION = domain + "/action/openapi/post_list?access_token=";
    //获取动弹列表
    public final static String GET_LATEST = domain + "/action/openapi/tweet_list?access_token=";
    //获取动弹列表
    public final static String GET_HOT = domain + "/action/openapi/tweet_list?access_token=";
    //获取软件分类列表
    public final static String GET_SOFTWARE = domain + "/action/openapi/project_tag_list?access_token=";
    //发布动弹
    public final static String SEND_TWEET = domain + "/action/openapi/tweet_pub";
    //发布帖子
    public final static String SEND_QUESTION = domain + "/action/openapi/post_pub";
    //获取当前登录用户的账户信息
    public final static String GET_MY_INFORMATION = domain + "/action/openapi/user?access_token=";
    //获取博客详情
    public final static String GET_BLOG_INFORMATION = domain + "/action/openapi/blog_detail";
    //获取新闻详情
    public final static String GET_INFORMATION_DETAIL = domain + "/action/openapi/news_detail";
    //个人主页详情
    public final static String GET_MY_DETAIL = domain + "/action/openapi/my_information?access_token=";
    //我的动弹
    public final static String GET_MY_TWEET = domain + "/action/openapi/tweet_list?access_token=";

}
