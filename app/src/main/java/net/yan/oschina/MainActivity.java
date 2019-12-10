package net.yan.oschina;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.next.easynavigation.view.EasyNavigationBar;

import net.yan.oschina.discovery.fragment.DiscoveryFragment;
import net.yan.oschina.my.fragment.MyFragment;
import net.yan.oschina.news.fragment.NewsFragment;
import net.yan.oschina.tweet.fragment.Tweetragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EasyNavigationBar bar;
    private String[] tabText = {"综合", "动弹", "发现", "我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.news_normal, R.mipmap.tweet_normal, R.mipmap.discovery_normal, R.mipmap.my_normal};
    //选中时icon
    private int[] selectIcon = {R.mipmap.news_light, R.mipmap.tweet_light, R.mipmap.discovery_light, R.mipmap.my_light};

    private List<Fragment> fragments = new ArrayList<>();
    private Handler mHandler = new Handler();
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = findViewById(R.id.bar);

        fragments.add(new NewsFragment());
        fragments.add(new Tweetragment());
        fragments.add(new DiscoveryFragment());
        fragments.add(new MyFragment());


        View view = LayoutInflater.from(this).inflate(R.layout.custom_add_view, null);

        bar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .canScroll(true)
                .addAsFragment(false)
                .mode(EasyNavigationBar.MODE_ADD_VIEW)
                .addCustomView(view)
                .fragmentManager(getSupportFragmentManager())
                .onTabClickListener(new EasyNavigationBar.OnTabClickListener() {
                    @Override
                    public boolean onTabClickEvent(View view, int position) {
                        Log.e("Tap->Position", position + "");
                        if (position == 2) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    //＋ 旋转动画
                                    if (flag) {
                                        bar.getCustomAddView().animate().rotation(180).setDuration(400);
                                    } else {
                                        bar.getCustomAddView().animate().rotation(0).setDuration(400);
                                    }
                                    flag = !flag;
                                }
                            });
                        }
                        return false;
                    }
                }).build();
    }
    //jaskdfjadks
}
