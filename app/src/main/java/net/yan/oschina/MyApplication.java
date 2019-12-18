package net.yan.oschina;

import android.app.Application;
import android.os.Environment;

import com.okhttplib.OkHttpUtil;
import com.okhttplib.annotation.CacheType;
import com.okhttplib.annotation.Encoding;
import com.okhttplib.cookie.PersistentCookieJar;
import com.okhttplib.cookie.cache.SetCookieCache;
import com.okhttplib.cookie.persistence.SharedPrefsCookiePersistor;

import java.io.File;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String downloadFileDir = Environment.getExternalStorageDirectory().getPath() +
                "/myOsChina/okHttp_download/";
        String cacheDir = Environment.getExternalStorageDirectory().getPath() +
                "/myOsChina/okHttp_cache";
        OkHttpUtil.init(getApplicationContext())
                .setConnectTimeout(15)
                .setWriteTimeout(15)
                .setReadTimeout(15)
                .setMaxCacheSize(10*1024*1024)
                .setCacheType(CacheType.FORCE_NETWORK)
                .setHttpLogTAG("HttpLog")
                .setIsGzip(false)
                .setShowHttpLog(true)
                .setShowLifecycleLog(false)
                .setRetryOnConnectionFailure(false)
                .setCachedDir(new File(cacheDir))
                .setDownloadFileDir(downloadFileDir)
                .setResponseEncoding(Encoding.UTF_8)
                .setRequestEncoding(Encoding.UTF_8)
                .setCookieJar(new PersistentCookieJar(new SetCookieCache(),new SharedPrefsCookiePersistor(getApplicationContext())))
                .build();
    }
}
