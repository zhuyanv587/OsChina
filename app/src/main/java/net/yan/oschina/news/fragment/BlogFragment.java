package net.yan.oschina.news.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.callback.Callback;

import net.yan.oschina.R;
import net.yan.oschina.my.fragment.MyFragment;
import net.yan.oschina.net.BlogResult;
import net.yan.oschina.net.URLList;
import net.yan.oschina.news.activity.BlogDetailActivity;
import net.yan.oschina.news.adapter.BlogAdapter;
import net.yan.oschina.news.entity.Blog;
import net.yan.oschina.news.entity.BlogInformation;
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

public class BlogFragment extends Fragment {
    private Unbinder binder;

    private List<Blog> lists=new ArrayList<>();
    @BindView(R.id.recyclerView_blog)
    RecyclerView recyclerView;
//数据处理的对象
    BlogAdapter blogAdapter;

    public BlogFragment(){

    }
    public static MyFragment myInstance(){
        return new MyFragment();
    }

    private String url;
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
        OkHttpUtil.getDefault(this)
                .doGetAsync(HttpInfo.Builder().setUrl(URLList.GET_BLOG + ACache.get(getActivity()).getAsString("token")).build(), new Callback() {
                    @Override
                    public void onSuccess(HttpInfo info) throws IOException {
                        BlogResult result = info.getRetDetail(BlogResult.class);
                        blogAdapter.replaceData(result.getBloglist());
                        BlogInformation information = info.getRetDetail(BlogInformation.class);
                        url = information.getUrl();
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("url",url);
                        intent.putExtras(bundle);
                    }

                    @Override
                    public void onFailure(HttpInfo info) throws IOException {
                        Toast.makeText(getActivity(),"网络请求失败",Toast.LENGTH_LONG).show();
                    }
                });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        blogAdapter = new BlogAdapter(R.layout.item_blog,lists);
        recyclerView.setAdapter(blogAdapter);

        View view1 = View.inflate(getActivity(),R.layout.blog_picture,null);
        blogAdapter.addHeaderView(view1);
        //设置adapter的点击事件，得到item的点击位置，将blog传值过去
        blogAdapter.setOnItemClickListener(new BlogAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent=new Intent(getContext(), BlogDetailActivity.class);
//                Bundle bundle=new Bundle();
//                bundle.putSerializable("blog", (Serializable) lists.get(position));
//                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binder.unbind();
    }
}
