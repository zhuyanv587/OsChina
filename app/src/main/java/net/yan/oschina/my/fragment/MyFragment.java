package net.yan.oschina.my.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.yan.oschina.R;
import net.yan.oschina.my.Adapter.MyAdapter;
import net.yan.oschina.my.NetView.netView;
import net.yan.oschina.my.activity.LoginActivity;
import net.yan.oschina.my.entity.My;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFragment extends Fragment implements View.OnClickListener {
    private String titles[]={"社区活跃度","社区影响力","技术贡献度","活动活跃性","开源贡献度","学习积极性"};
    private double[] percent = {1, 0.4, 0.6, 0.5, 0.8, 0.3};

//用于子项数据的数组
    private List<My> myList=new ArrayList<>();

    @BindView(R.id.my_title)
    ImageView head;
    @BindView(R.id.my_set)
    ImageView set;
    @BindView(R.id.my_scan)
    ImageView scan;
    @BindView(R.id.netView)
    netView netView;
    @BindView(R.id.my_Recycler)
    RecyclerView myRecycler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_me,container,false);
        ButterKnife.bind(this,view);
        netView.setTitles(titles);
        netView.setPercent(percent);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        head.setOnClickListener(this);
        initMy();
        RecyclerView.LayoutManager manager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        myRecycler.setLayoutManager(manager);
        MyAdapter myAdapter=new MyAdapter(myList);
        myRecycler.setAdapter(myAdapter);
    }

    private void initMy() {
//        for (int i=0;i<10;i++){
            My message=new My(R.mipmap.message,"我的消息",R.mipmap.bracket);
            myList.add(message);
            My medal=new My(R.mipmap.demal,"我的勋章",R.mipmap.bracket);
            myList.add(medal);
            My read=new My(R.mipmap.read,"阅读记录",R.mipmap.bracket);
            myList.add(read);
            My blog=new My(R.mipmap.test,"我的博客",R.mipmap.bracket);
            myList.add(blog);
            My grey_name=new My(R.mipmap.name,"我的灰名单",R.mipmap.bracket);
            myList.add(grey_name);
            My question=new My(R.mipmap.my_question,"我的博客",R.mipmap.bracket);
            myList.add(question);
            My thrown=new My(R.mipmap.thrown,"我的投递",R.mipmap.bracket);
            myList.add(thrown);
            My activity=new My(R.mipmap.activity,"我的活动",R.mipmap.bracket);
            myList.add(activity);
            My sign=new My(R.mipmap.lable,"关注便签",R.mipmap.bracket);
            myList.add(sign);
            My share=new My(R.mipmap.share,"邀请好友",R.mipmap.bracket);
            myList.add(share);
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_title:
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }

    }
}
