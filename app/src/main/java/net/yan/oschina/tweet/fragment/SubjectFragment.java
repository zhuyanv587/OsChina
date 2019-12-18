package net.yan.oschina.tweet.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.yan.oschina.R;
import net.yan.oschina.tweet.fragment.entity.Subject;
import net.yan.oschina.tweet.fragment.adapter.SubjectAdapter;

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

public class SubjectFragment extends Fragment {
     private Unbinder binder;
    private SubjectAdapter subjectAdapter;
    private List<Subject> list=new ArrayList<>();
    @BindView(R.id.recycler_subject)
    RecyclerView recyclerView;

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
       Subject subject=new Subject();
        for(int i=0;i<30;i++){
            list.add(subject);
        }


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
