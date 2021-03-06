package net.yan.oschina.my.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.callback.Callback;

import net.yan.oschina.R;
import net.yan.oschina.my.Adapter.MyAdapter;
import net.yan.oschina.my.NetView.netView;
import net.yan.oschina.my.activity.AttentionActivity;
import net.yan.oschina.my.activity.CollectNumberActivity;
import net.yan.oschina.my.activity.FansNumberActivity;
import net.yan.oschina.my.activity.FocusNumberActivity;
import net.yan.oschina.my.activity.LoginActivity;
import net.yan.oschina.my.activity.MedalActivity;
import net.yan.oschina.my.activity.MessageActivity;
import net.yan.oschina.my.activity.MySetActivity;
import net.yan.oschina.my.activity.MyblogActivity;
import net.yan.oschina.my.activity.MydeliverActivity;
import net.yan.oschina.my.activity.MyquestionActivity;
import net.yan.oschina.my.activity.ReadActivity;
import net.yan.oschina.my.activity.RosterActivity;
import net.yan.oschina.my.activity.ShakyActivity;
import net.yan.oschina.my.activity.TweetNumberActivity;
import net.yan.oschina.my.entity.My;
import net.yan.oschina.my.entity.MyDetail;
import net.yan.oschina.my.entity.MyInformation;
import net.yan.oschina.net.URLList;
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
import me.curzbin.library.BottomDialog;
import me.curzbin.library.Item;
import me.curzbin.library.OnItemClickListener;

public class MyFragment extends Fragment implements View.OnClickListener {
    private String titles[] = {"社区活跃度", "社区影响力", "技术贡献度", "活动活跃性", "开源贡献度", "学习积极性"};
    private double[] percent = {1, 1, 1, 1, 1, 1};
    //定义recyclerView的适配器
    private MyAdapter myadapter;

    //用于子项数据的数组
    private List<My> myList = new ArrayList<>();

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
    @BindView(R.id.my_name)
    TextView myName;
    @BindView(R.id.tv_tweet_number)
    TextView tweet_number;
    @BindView(R.id.tv_collect_number)
    TextView collect_number;
    @BindView(R.id.tv_focus_number)
    TextView focus_number;
    @BindView(R.id.tv_fans_number)
    TextView fans_number;

    private LinearLayout tweetNumber,collectNumber,focusNumber,fansNumber;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        tweetNumber = view.findViewById(R.id.layout_tweet_number);
        collectNumber = view.findViewById(R.id.layout_collect_number);
        focusNumber = view.findViewById(R.id.layout_focus_number);
        fansNumber = view.findViewById(R.id.layout_fans_number);

        tweetNumber.setOnClickListener(this);
        collectNumber.setOnClickListener(this);
        focusNumber.setOnClickListener(this);
        fansNumber.setOnClickListener(this);

        ButterKnife.bind(this, view);
        netView.setTitles(titles);
        netView.setPercent(percent);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        head.setOnClickListener(this);
        set.setOnClickListener(this);

        OkHttpUtil.getDefault(this)
                .doGetAsync(HttpInfo.Builder().setUrl(URLList.GET_MY_INFORMATION + ACache.get(getActivity()).getAsString("token")).build(), new Callback() {
                    @Override
                    public void onSuccess(HttpInfo info) throws IOException {
                        MyInformation result = info.getRetDetail(MyInformation.class);
                        Glide.with(getContext()).load(result.getAvatar())
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(head);
                        myName.setText(result.getName());
                    }

                    @Override
                    public void onFailure(HttpInfo info) throws IOException {
                        Toast.makeText(getActivity(), "网络请求失败", Toast.LENGTH_LONG).show();
                    }
                });

        initMy();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        myRecycler.setLayoutManager(manager);
        myadapter = new MyAdapter(getActivity(), myList);
        myRecycler.setAdapter(myadapter);
        //调用了adapter里面的方法
        myadapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                My data = myList.get(position);
                String name = data.getIntro();
                switch (name) {
                    case "我的消息":
                        startActivity(new Intent(getActivity(), MessageActivity.class));
                        break;
                    case "我的勋章":
                        startActivity(new Intent(getActivity(), MedalActivity.class));
                        break;
                    case "我的博客":
                        startActivity(new Intent(getActivity(), MyblogActivity.class));
                        break;
                    case "阅读记录":
                        startActivity(new Intent(getActivity(), ReadActivity.class));
                        break;
                    case "我的灰名单":
                        startActivity(new Intent(getActivity(), RosterActivity.class));
                        break;
                    case "我的问答":
                        startActivity(new Intent(getActivity(), MyquestionActivity.class));
                        break;
                    case "我的投递":
                        startActivity(new Intent(getActivity(), MydeliverActivity.class));
                        break;
                    case "我的活动":
                        startActivity(new Intent(getActivity(), ShakyActivity.class));
                        break;
                    case "关注标签":
                        startActivity(new Intent(getActivity(), AttentionActivity.class));
                        break;
                    case "邀请好友":
                        new BottomDialog(getActivity())
                                .title(R.string.share_titles)//设置标题
                                .layout(BottomDialog.HORIZONTAL)//设置内容layout,默认为线性
                                .inflateMenu(R.menu.menu)//传入菜单内容
                                .itemClick(new OnItemClickListener() {
                                    @Override
                                    public void click(Item item) {
                                        Toast.makeText(getActivity(), getString(R.string.share_titles) + item.getTitle(), Toast.LENGTH_LONG).show();
                                    }
                                })
                                .show();
                        break;
                }
            }
        });

    }

    private void initMy() {
        My message = new My(R.mipmap.message1, "我的消息", R.mipmap.bracket);
        myList.add(message);
        My medal = new My(R.mipmap.dema, "我的勋章", R.mipmap.bracket);
        myList.add(medal);
        My read = new My(R.mipmap.read1, "阅读记录", R.mipmap.bracket);
        myList.add(read);
        My blog = new My(R.mipmap.test1, "我的博客", R.mipmap.bracket);
        myList.add(blog);
        My grey_name = new My(R.mipmap.name1, "我的灰名单", R.mipmap.bracket);
        myList.add(grey_name);
        My question = new My(R.mipmap.question1, "我的问答", R.mipmap.bracket);
        myList.add(question);
        My thrown = new My(R.mipmap.thrown1, "我的投递", R.mipmap.bracket);
        myList.add(thrown);
        My activity = new My(R.mipmap.activity1, "我的活动", R.mipmap.bracket);
        myList.add(activity);
        My sign = new My(R.mipmap.guanzhu, "关注标签", R.mipmap.bracket);
        myList.add(sign);
        My share = new My(R.mipmap.share1, "邀请好友", R.mipmap.bracket);
        myList.add(share);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_title:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.my_set:
                intent = new Intent(getActivity(), MySetActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_tweet_number:
                OkHttpUtil.getDefault(this)
                        .doGetAsync(HttpInfo.Builder().setUrl(URLList.GET_MY_DETAIL + ACache.get(getActivity()).getAsString("token")).build(), new Callback() {
                            @Override
                            public void onSuccess(HttpInfo info) throws IOException {
                                Toast.makeText(getActivity(), "网络请求成功", Toast.LENGTH_LONG).show();
                                MyDetail detail = info.getRetDetail(MyDetail.class);
                                tweet_number.setText(detail.getFollowersCount());
                            }

                            @Override
                            public void onFailure(HttpInfo info) throws IOException {
                                Toast.makeText(getActivity(), "网络请求失败", Toast.LENGTH_LONG).show();
                            }
                        });
                intent = new Intent(getActivity(), TweetNumberActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_collect_number:
                intent = new Intent(getActivity(), CollectNumberActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_focus_number:
                intent = new Intent(getActivity(), FocusNumberActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_fans_number:
                intent = new Intent(getActivity(), FansNumberActivity.class);
                startActivity(intent);
                break;
        }

    }
}
