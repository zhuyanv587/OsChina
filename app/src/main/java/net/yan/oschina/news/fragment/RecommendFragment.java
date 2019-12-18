package net.yan.oschina.news.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.yan.oschina.R;
import net.yan.oschina.news.entity.Recommend;
import net.yan.oschina.news.adapter.RecommendAdapter;

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

public class RecommendFragment extends Fragment {
    private Unbinder binder;
    private List<Recommend> lists = new ArrayList<>();

    @BindView(R.id.recycler_recommend)
    RecyclerView recyclerView;

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
        Recommend recommend = new Recommend();
        for (int i=0;i<30;i++){
            lists.add(recommend);
        }

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
