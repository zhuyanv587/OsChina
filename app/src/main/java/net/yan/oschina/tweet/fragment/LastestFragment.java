package net.yan.oschina.tweet.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.yan.oschina.R;
import net.yan.oschina.tweet.entity.Lastest;
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

public class LastestFragment extends Fragment {

    private Unbinder unbinder;
    private LastestAdapter lastestAdapter;
    private List<Lastest> list=new ArrayList<>();

    @BindView(R.id.recyclerView_lastest)

    RecyclerView recyclerView;

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
        Lastest lastest=new Lastest();
        for(int i=0;i<30;i++){
            list.add(lastest);
        }


        RecyclerView.LayoutManager manager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        lastestAdapter=new LastestAdapter(R.layout.item_lastest,list);
        recyclerView.setAdapter(lastestAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
