package net.yan.oschina.tweet.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.callback.Callback;

import net.yan.oschina.R;
import net.yan.oschina.net.InformationResult;
import net.yan.oschina.net.LatestResult;
import net.yan.oschina.net.URLList;
import net.yan.oschina.tweet.adapter.LatestAdapter;
import net.yan.oschina.tweet.entity.Latest;
import net.yan.oschina.util.ACache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LatestFragment extends Fragment {

    private Unbinder unbinder;
    private LatestAdapter latestAdapter;
    private List<Latest> list=new ArrayList<>();
    @BindView(R.id.swipefresh_lastest)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView_lastest)
    RecyclerView recyclerView;
    public LatestFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lastest, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refurbish();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refurbish();
                        //下拉刷新的圆圈是否显示
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },0);
            }

        });

    }
    private void refurbish() {
        OkHttpUtil.getDefault(this)
                .doGetAsync(HttpInfo.Builder().setUrl(URLList.GET_LATEST + ACache.get(getActivity()).getAsString("token")).build(), new Callback() {
                    @Override
                    public void onSuccess(HttpInfo info) throws IOException {
                        LatestResult result = info.getRetDetail(LatestResult.class);
                        latestAdapter.replaceData(result.getTweetlist());

                    }

                    @Override
                    public void onFailure(HttpInfo info) throws IOException {
                        Toast.makeText(getActivity(),"网络请求失败",Toast.LENGTH_LONG).show();
                    }
                });
        RecyclerView.LayoutManager manager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        latestAdapter=new LatestAdapter(R.layout.item_latest,list);
        recyclerView.setAdapter(latestAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
