package net.yan.oschina.news.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.yan.oschina.R;
import net.yan.oschina.news.entity.Blog;
import net.yan.oschina.news.adapter.BlogAdapter;

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

public class BlogFragment extends Fragment {
    private Unbinder binder;


    private List<Blog> lists=new ArrayList<>();
    @BindView(R.id.recyclerView_blog)
    RecyclerView recyclerView;

    BlogAdapter blogAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_blog,container,false);
        binder=ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Blog blog = new Blog();
        for (int i=0;i<30;i++){
            lists.add(blog);
        }

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        blogAdapter = new BlogAdapter(R.layout.item_blog,lists);
        recyclerView.setAdapter(blogAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binder.unbind();
    }
}
