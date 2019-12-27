package net.yan.oschina.news.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.callback.Callback;

import net.yan.oschina.R;
import net.yan.oschina.net.RecommendResult;
import net.yan.oschina.net.URLList;
import net.yan.oschina.news.adapter.RecommendAdapter;
import net.yan.oschina.news.entity.Recommend;
import net.yan.oschina.util.ACache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecommendFragment extends Fragment {
    private Unbinder binder;
    private List<Recommend> lists = new ArrayList<>();

    @BindView(R.id.recycler_recommend)
    RecyclerView recyclerView;
@BindView(R.id.swipefresh_remm)
    SwipeRefreshLayout swipeRefreshLayout;
    RecommendAdapter recommendAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_recommend,container,false);
        binder= ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fresh();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        放入要刷新的数据
                        fresh();
//                        刷新的小圈圈是否显示
                        swipeRefreshLayout.setRefreshing(false);
                    }


                },0);
            }
        });

    }

    private void fresh() {
        OkHttpUtil.getDefault(this)
                .doGetAsync(HttpInfo.Builder().setUrl(URLList.GET_RECOMMEND + ACache.get(getActivity()).getAsString("token")).build(), new Callback() {
                    @Override
                    public void onSuccess(HttpInfo info) throws IOException {
                        RecommendResult result = info.getRetDetail(RecommendResult.class);
                        recommendAdapter.replaceData(result.getBloglist());

                    }

                    @Override
                    public void onFailure(HttpInfo info) throws IOException {
                        Toast.makeText(getActivity(),"网络请求失败",Toast.LENGTH_LONG).show();
                    }
                });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recommendAdapter = new RecommendAdapter(R.layout.item_recommend,lists);
        recyclerView.setAdapter(recommendAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binder.unbind();
    }
}
