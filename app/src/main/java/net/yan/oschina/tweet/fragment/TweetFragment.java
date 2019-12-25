package net.yan.oschina.tweet.fragment;

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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TweetFragment extends Fragment {
    @BindView(R.id.viewPagerTab)
    SlidingTabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager pager;
    private Unbinder binder;

    private ArrayList<Fragment> mFragment = new ArrayList<>();
    private final String[] mTitles = {"最新", "热门", "话题", "乱弹", "我的"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tweet, container, false);
        binder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragment.add(new LatestFragment());
        mFragment.add(new HotFragment());
        mFragment.add(new SubjectFragment());
        mFragment.add(new ThrumFragment());
        mFragment.add(new MeFragment());
        tab.setViewPager(pager, mTitles, getActivity(), mFragment);
        pager.setAdapter(new MyAdapter(getChildFragmentManager()));
        tab.setViewPager(pager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binder.unbind();
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragment.get(position);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }
}
