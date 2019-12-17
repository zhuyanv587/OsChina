package net.yan.oschina.tweet.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.yan.oschina.R;
import net.yan.oschina.entity.English;
import net.yan.oschina.entity.Hot;
import net.yan.oschina.entity.Lastest;
import net.yan.oschina.news.adapter.EnglishAdapter;
import net.yan.oschina.tweet.fragment.adapter.HotAdapter;
import net.yan.oschina.tweet.fragment.adapter.LastestAdapter;

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
        Hot hot=new Hot();
        for(int i=0;i<30;i++){
            list.add(hot);
        }


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
