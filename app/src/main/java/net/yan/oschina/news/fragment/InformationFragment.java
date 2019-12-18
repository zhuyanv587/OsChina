package net.yan.oschina.news.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.callback.Callback;
import com.youth.banner.Banner;

import net.yan.oschina.R;
import net.yan.oschina.net.URLList;
import net.yan.oschina.news.entity.Information;
import net.yan.oschina.news.adapter.InformationAdapter;
import net.yan.oschina.net.InformationResult;
import net.yan.oschina.util.ACache;
import net.yan.oschina.util.GlideImageLoader;

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

public class InformationFragment extends Fragment {
    private Unbinder binder;

    @BindView(R.id.recyclerView_information)
    RecyclerView recyclerView;

    private List<Information> lists = new ArrayList<>();

    private InformationAdapter mInformationAdapter;

    private List<Integer> images = new ArrayList<>();

    public InformationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information,container,false);
        binder= ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        OkHttpUtil.getDefault(this)
                .doGetAsync(HttpInfo.Builder().setUrl(URLList.GET_INFORMATION + ACache.get(getActivity()).getAsString("token")).build(), new Callback() {
                    @Override
                    public void onSuccess(HttpInfo info) throws IOException {
                        InformationResult result = info.getRetDetail(InformationResult.class);
                        mInformationAdapter.replaceData(result.getNewsList());

                    }

                    @Override
                    public void onFailure(HttpInfo info) throws IOException {
                        System.out.println(info);
                    }
                });
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        mInformationAdapter = new InformationAdapter(R.layout.item_information,lists);
        recyclerView.setAdapter(mInformationAdapter);

        //处理Header
        View view1 = View.inflate(getActivity(),R.layout.xbanner,null);
        mInformationAdapter.addHeaderView(view1);

        Banner banner = view1.findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());
        images.add(R.mipmap.first);
        images.add(R.mipmap.five);
        images.add(R.mipmap.third);
        images.add(R.mipmap.four);

        banner.setImages(images);
        banner.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binder.unbind();
    }
}
