package net.yan.oschina.news.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.yan.oschina.R;
import net.yan.oschina.entity.English;
import net.yan.oschina.news.adapter.EnglishAdapter;

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

public class EnglishFragment extends Fragment {

    private Unbinder binder;

    private List<English> lists=new ArrayList<>();
    @BindView(R.id.recycler_english)
    RecyclerView recyclerView;

    EnglishAdapter englishAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_english,container,false);
        binder=ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        English english=new English();
        for(int i=0;i<30;i++){
            lists.add(english);
        }


        RecyclerView.LayoutManager manager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        englishAdapter=new EnglishAdapter(R.layout.item_english,lists);
        recyclerView.setAdapter(englishAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binder.unbind();
    }
}
