package net.yan.oschina.tweet.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.callback.Callback;

import net.yan.oschina.R;
import net.yan.oschina.net.MyResult;
import net.yan.oschina.net.SubjectResult;
import net.yan.oschina.net.URLList;
import net.yan.oschina.tweet.adapter.SubjectAdapter;
import net.yan.oschina.tweet.entity.Subject;
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

public class SubjectFragment extends Fragment {
     private Unbinder binder;
    private SubjectAdapter subjectAdapter;
    private List<Subject> list=new ArrayList<>();
    @BindView(R.id.recycler_subject)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh_subject)
    SwipeRefreshLayout swipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subject, container, false);
        binder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        resh();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //放入要刷新的内容
                        resh();
                        //下拉刷新的圆圈是否显示
                        swipeRefreshLayout.setRefreshing(false);
                    }

                }, 0);
            }
        });
    }

    private void resh() {
        OkHttpUtil.getDefault(this)
                .doGetAsync(HttpInfo.Builder().setUrl(URLList.GET_MY_TWEET + ACache.get(getActivity()).getAsString("token")).build(), new Callback() {
                    @Override
                    public void onSuccess(HttpInfo info) throws IOException {
                        SubjectResult result = info.getRetDetail(SubjectResult.class);
                        subjectAdapter.replaceData(result.getTweetlist());
                    }

                    @Override
                    public void onFailure(HttpInfo info) throws IOException {
                        Toast.makeText(getActivity(), "网络请求失败", Toast.LENGTH_LONG).show();
                    }
                });
        RecyclerView.LayoutManager manager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        subjectAdapter=new SubjectAdapter(R.layout.item_subject,list);
        recyclerView.setAdapter(subjectAdapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binder.unbind();
    }

}
