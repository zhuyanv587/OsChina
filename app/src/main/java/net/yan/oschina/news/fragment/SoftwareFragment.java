package net.yan.oschina.news.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.yan.oschina.R;
import net.yan.oschina.news.entity.Software;
import net.yan.oschina.news.adapter.SoftwareAdapter;

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
        Software software = new Software();
        for (int i=0;i<30;i++){
            lists.add(software);
        }

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
