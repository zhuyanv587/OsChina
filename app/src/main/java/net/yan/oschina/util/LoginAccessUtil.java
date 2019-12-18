package net.yan.oschina.util;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.fastjson.JSON;

import net.yan.oschina.net.OauthClient;

import androidx.annotation.RequiresApi;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthOschinaRequest;

public class LoginAccessUtil {
    private static String code;
    private static String state;

    /**
     * 传入参数，获取用户数据，json格式
     * {"gender":"MALE","nickname":"robot_man","location":"江苏 南京",
     * "avatar":"https://static.oschina.net/uploads/user/1854/3708595_50
     * .jpeg?t=1508155510000","source":"OSCHINA","blog":"https://my.oschina
     * .net/u/37xxx08595","uuid":"3708595",
     * "email":"17835789-d843-4aa0-8e1f-2b4d44b48e2d","token":{"accessToken
     * ":"4673df12-a662-4cc9-8472-1d26bfe6461b","uid":"3708595",
     * "expireIn":420125,"refreshToken":"78af8587-6ae6-4fc4-bf5d-4ef221c23b6a
     * "},"username":"robot_man"}
     *
     * @param activity 使用方法所在的activity,需要实现CallBackForUser接口
     * @param client   客户端
     * @return
     * @Data 2012.12.12
     */
    public static void login(Activity activity, OauthClient client) {
        Log.e("aaa","login: " +client.toString());

        new Thread(() -> {
            // 创建授权request
            AuthOschinaRequest authRequest =
                    new AuthOschinaRequest(AuthConfig.builder().clientId(client.getClientId())
                                                   .clientSecret(client.getClientSecret())
                                                   .redirectUri(client.getRedirectUrl())
                                                   .build());
            // 生成授权页面
            String url1 = authRequest.authorize();
            activity.runOnUiThread(() -> {
                WebView myWebView = new WebView(activity);
                myWebView.setWebViewClient(new WebViewClient() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        myWebView.evaluateJavascript("javascript:if(document" +
                                                             ".getElementById" +
                                                             "('f_email') != " +
                                                             "null){" +
                                                             "document" +
                                                             ".getElementById" +
                                                             "('f_email')" +
                                                             ".value= '" + client.getUsername() + "';" +
                                                             "document" +
                                                             ".getElementById" +
                                                             "('f_pwd')" +
                                                             ".value= '" + client.getPassword() + "';" +
                                                             "}" +
                                                             "document" +
                                                             ".getElementsByName('authorize')[0].click();", value -> {
                        });
                    }

                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view,
                                                            String url) {
                        //监听url的变化，通过比较是否包含回调地址确定是否是包含code的url
                        if (url.contains(client.getRedirectUrl())) {
                            code = Uri.parse(url).getQueryParameter("code");
                            state = Uri.parse(url).getQueryParameter("state");
                            // 构造AuthCallback请求参数，填入code和state
                            AuthCallback callback = new AuthCallback();
                            callback.setCode(code);
                            callback.setState(state);
                            // 开启多线程登录
                            new Thread(() -> {
                                // 通过login方法登录，获得token和用户信息
                                AuthResponse<AuthUser> response = authRequest.login(callback);
                                AuthUser user = response.getData();

                                String userString = JSON.toJSON(user).toString();
                                ((CallBackForUser) activity).getUserMsg(userString);
                            }).start();
                        }
                        return false;
                    }
                });

                // WebView的设置参数类
                WebSettings webSettings = myWebView.getSettings();
                // 让WebView能够执行javaScript
                webSettings.setJavaScriptEnabled(true);
                myWebView.loadUrl(url1);
            });
        }).start();
    }
}
