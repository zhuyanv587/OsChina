package net.yan.oschina.news.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;

import net.yan.oschina.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NewsFragment extends Fragment {

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.viewPagerTab)
    SlidingTabLayout viewPagerTab;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"关注", "软件", "资讯", "推荐", "问答", "博客", "英文"};

    private Unbinder binder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        binder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragments.add(new FollowFragment());
        mFragments.add(new SoftwareFragment());
        mFragments.add(new InformationFragment());
        mFragments.add(new RecommendFragment());
        mFragments.add(new QuestionFragment());
        mFragments.add(new BlogFragment());
        mFragments.add(new EnglishFragment());

        viewPagerTab.setViewPager(viewPager,mTitles,getActivity(),mFragments);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binder.unbind();
    }
}
