package net.yan.oschina.my.activity;

import android.os.Bundle;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;

import net.yan.oschina.R;
import net.yan.oschina.my.fragment.ActivityFragment;
import net.yan.oschina.my.fragment.DevoteFragment;
import net.yan.oschina.my.fragment.QuestFragment;
import net.yan.oschina.my.fragment.StatusFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MedalActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @BindView(R.id.tablayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private MyPagerAdapter adapter;

    private ArrayList<Fragment> mFagments=new ArrayList<>();
    private final String[] mTitles={"身份勋章","问答勋章","活动勋章","贡献勋章"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medal);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        initView();

        toolbar=findViewById(R.id.medalTool_bar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        //设置标题栏回退按钮，及点击事件
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedalActivity.this.finish();
            }
        });

    }

    private void initView() {
        mFagments.add(new StatusFragment());
        mFagments.add(new QuestFragment());
        mFagments.add(new ActivityFragment());
        mFagments.add(new DevoteFragment());

        adapter=new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager,mTitles);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFagments.get(position);
        }


        @Override
        public int getCount() {
            return mFagments.size();
        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }

}
