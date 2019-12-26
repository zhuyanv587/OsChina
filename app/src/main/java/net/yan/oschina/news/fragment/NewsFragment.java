package net.yan.oschina.news.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;

import net.yan.oschina.R;
import net.yan.oschina.news.activity.SearchActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NewsFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.viewPagerTab)
    SlidingTabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager pager;
    private Unbinder binder;
    @BindView(R.id.search)
    ImageView imageView;

    private ArrayList<Fragment> mFragment=new ArrayList<>();
    private final String[] mTitles={"关注","软件","资讯","推荐","问答","博客","英文"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_news,container,false);
        binder=ButterKnife.bind(this,view);
        imageView.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragment.add(new FocusFragment2());
        mFragment.add(new SoftwareFragment());
        mFragment.add(new InformationFragment());
        mFragment.add(new RecommendFragment());
        mFragment.add(new QuestionFragment());
        mFragment.add(new BlogFragment());
        mFragment.add(new EnglishFragment());
        tab.setViewPager(pager,mTitles,getActivity(),mFragment);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binder.unbind();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        startActivity(intent);
    }
}
