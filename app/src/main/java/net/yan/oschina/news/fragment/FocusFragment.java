package net.yan.oschina.news.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.yan.oschina.R;
import net.yan.oschina.my.activity.LoginActivity;
import net.yan.oschina.news.adapter.FocusAdapter;
import net.yan.oschina.news.entity.Focus;

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

public class FocusFragment extends Fragment implements View.OnClickListener {
    private Unbinder binder;
    @BindView(R.id.recyclerView_focus)
    RecyclerView recyclerView_focus;
//    @BindView(R.id.log)
    Button log;

    private List<Focus> lists = new ArrayList<>();

    private FocusAdapter focusAdapter;


    public FocusFragment() {
    }

    @Nullable
     @Override
     public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view=inflater.inflate(R.layout.fragment_focus,container,false);
         binder= ButterKnife.bind(this,view);
         return view;

     }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


        Focus focus=new Focus();
        for (int i=0;i<9;i++){
            lists.add(focus);
        }
        RecyclerView.LayoutManager manager= new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView_focus.setLayoutManager(manager);
        focusAdapter=new FocusAdapter(R.layout.item_focus,lists);
        recyclerView_focus.setAdapter(focusAdapter);

        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.header_focus,null);
        log = view1.findViewById(R.id.log);
        log.setOnClickListener(this);
        focusAdapter.setHeaderView(view1);



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.log:
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
