package net.yan.oschina.news.activity;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import net.yan.oschina.R;
import net.yan.oschina.net.URLList;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BlogDetailActivity extends AppCompatActivity {
    private WebView wvBlog;
    private WebSettings settings;
    private Toolbar toolbar;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail);

        //设置标题栏回退按钮，及点击事件
        toolbar=findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlogDetailActivity.this.finish();
            }
        });
        wvBlog=findViewById(R.id.wv_blog_detail);
        //接收到来自RecyclerViewActivity的数据
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null){
            //传值拿什么接收呢？在之前将点击的Item的信息传过来
//                 BlogInfomation blogInfomation=bundle.get("blog");
                 //让它加载这个网页
            wvBlog.loadUrl(URLList.GET_BLOG);
            settings=wvBlog.getSettings();
            settings.setBuiltInZoomControls(true);
            settings.setJavaScriptEnabled(true);
            settings.setBlockNetworkImage(false);//解决图片不能加载
            settings.setUseWideViewPort(true);//将图片调整到合适webView
            settings.setLoadWithOverviewMode(true);//缩放至屏幕的大小
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            //解决http和https混合问题

            //设置网页在webView打开
            wvBlog.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    wvBlog.loadUrl(wvBlog.getUrl());
                    return true;
                }
                //解决https图片不能加载

                @Override
                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                    handler.proceed();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        if(wvBlog!=null){
            wvBlog.loadDataWithBaseURL(null,"","text/html","utf-8",null);
            wvBlog.clearHistory();
            ((ViewGroup)wvBlog.getParent()).removeView(wvBlog);
            wvBlog.destroy();;
            wvBlog=null;
        }
        super.onDestroy();

    }
}
