package net.yan.oschina.my.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;

import net.yan.oschina.R;
import net.yan.oschina.my.fragment.CommentFragment;
import net.yan.oschina.my.fragment.IFragment;
import net.yan.oschina.my.fragment.PersonalFragment;

import java.util.ArrayList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageActivity extends AppCompatActivity {
    private Context mContext;
   @BindView(R.id.tl_1)
   SlidingTabLayout tabLayout;
   @BindView(R.id.vp)
    ViewPager vp;
    private Toolbar toolbar;
    private ArrayList<Fragment> fragments=new ArrayList<>();
    private final String[] mtitles={"@我","评论","私信"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);

        //设置标题栏回退按钮，及点击事件
        toolbar=findViewById(R.id.MessTool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageActivity.this.finish();
            }
        });
        fragments.add(new IFragment());
        fragments.add(new CommentFragment());
        fragments.add(new PersonalFragment());
        tabLayout.setViewPager(vp,mtitles,this,fragments);
    }
}
