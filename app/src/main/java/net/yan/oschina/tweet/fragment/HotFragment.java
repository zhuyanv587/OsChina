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
import net.yan.oschina.net.HotResult;
import net.yan.oschina.net.LatestResult;
import net.yan.oschina.net.URLList;
import net.yan.oschina.tweet.entity.Hot;
import net.yan.oschina.tweet.adapter.HotAdapter;
import net.yan.oschina.util.ACache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HotFragment extends Fragment {
    private Unbinder binder;
    private HotAdapter hotAdapter;
    private List<Hot> list=new ArrayList<>();
    @BindView(R.id.recyclerView_hot)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        binder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        OkHttpUtil.getDefault(this)
                .doGetAsync(HttpInfo.Builder().setUrl(URLList.GET_HOT + ACache.get(getActivity()).getAsString("token")+"&user=-1").build(), new Callback() {
                    @Override
                    public void onSuccess(HttpInfo info) throws IOException {
                        HotResult result = info.getRetDetail(HotResult.class);
                        hotAdapter.replaceData(result.getTweetlist());

                    }

                    @Override
                    public void onFailure(HttpInfo info) throws IOException {
                        Toast.makeText(getActivity(),"网络请求失败",Toast.LENGTH_LONG).show();
                    }
                });

        RecyclerView.LayoutManager manager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        hotAdapter=new HotAdapter(R.layout.item_hot,list);
        recyclerView.setAdapter(hotAdapter);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binder.unbind();
    }
}
