package net.yan.oschina.my.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.yan.oschina.R;
import net.yan.oschina.my.NetView.netView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFragment extends Fragment {
    private String titles[]={"社区活跃度","社区影响力","技术贡献度","活动活跃性","开源贡献度","学习积极性"};
    private double[] percent = {1, 0.4, 0.6, 0.5, 0.8, 0.3};

    @BindView(R.id.my_title)
    ImageView head;
    @BindView(R.id.my_set)
    ImageView set;
    @BindView(R.id.my_scan)
    ImageView scan;
    @BindView(R.id.netView)
    netView netView;
    @BindView(R.id.myList)
    RecyclerView myRecycler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_me,container,false);
        ButterKnife.bind(this,view);
        netView.setTitles(titles);
        netView.setPercent(percent);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
