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
import net.yan.oschina.net.FousResult;
import net.yan.oschina.net.URLList;
import net.yan.oschina.news.adapter.FocusAdapter;
import net.yan.oschina.news.entity.Focus;
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

public class FocusFragment2 extends Fragment {
    private Unbinder binder;
    @BindView(R.id.recyclerView_focus)
    RecyclerView recyclerView_focus;

    private List<Focus> lists = new ArrayList<>();

    private FocusAdapter focusAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_focus, container, false);
        binder = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        OkHttpUtil.getDefault(this)
                .doGetAsync(HttpInfo.Builder().setUrl(URLList.GET_HOT + ACache.get(getActivity()).getAsString("token") + "&user=-1").build(), new Callback() {
                    @Override
                    public void onSuccess(HttpInfo info) throws IOException {
                        FousResult result = info.getRetDetail(FousResult.class);
                        focusAdapter.replaceData(result.getTweetlist());
                    }

                    @Override
                    public void onFailure(HttpInfo info) throws IOException {
                        Toast.makeText(getActivity(), "网络请求失败", Toast.LENGTH_LONG).show();
                    }
                });
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView_focus.setLayoutManager(manager);
        focusAdapter = new FocusAdapter(R.layout.item_foucs2, lists);
        recyclerView_focus.setAdapter(focusAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binder.unbind();
    }
}
