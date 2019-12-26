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
import net.yan.oschina.net.SoftwareResult;
import net.yan.oschina.net.URLList;
import net.yan.oschina.news.entity.Software;
import net.yan.oschina.news.adapter.SoftwareAdapter;
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

public class SoftwareFragment extends Fragment {
    private Unbinder binder;

    private List<Software> lists = new ArrayList<>();

    @BindView(R.id.recycler_software)
    RecyclerView recyclerView;

    SoftwareAdapter softwareAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_software,container,false);
        binder= ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        OkHttpUtil.getDefault(this)
                .doGetAsync(HttpInfo.Builder().setUrl(URLList.GET_SOFTWARE + ACache.get(getActivity()).getAsString("token")).build(), new Callback() {
                    @Override
                    public void onSuccess(HttpInfo info) throws IOException {
                        SoftwareResult result = info.getRetDetail(SoftwareResult.class);
                        softwareAdapter.replaceData(result.getProjectlist());

                    }

                    @Override
                    public void onFailure(HttpInfo info) throws IOException {
                        Toast.makeText(getActivity(),"网络请求失败",Toast.LENGTH_LONG).show();
                    }
                });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        softwareAdapter = new SoftwareAdapter(R.layout.item_software,lists);
        recyclerView.setAdapter(softwareAdapter);
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.header_software,null);
        softwareAdapter.setHeaderView(view1);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binder.unbind();
    }
}
