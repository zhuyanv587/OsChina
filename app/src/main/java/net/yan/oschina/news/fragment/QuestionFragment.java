package net.yan.oschina.news.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.callback.Callback;

import net.yan.oschina.R;
import net.yan.oschina.net.QuestionResult;
import net.yan.oschina.net.URLList;
import net.yan.oschina.news.adapter.QuestionAdapter;
import net.yan.oschina.news.entity.Question;
import net.yan.oschina.util.ACache;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class QuestionFragment extends Fragment {
    private Unbinder binder;
    private static final int DELAY_MILLIS = 1500;//延迟时间
    private int mShowType=1;
    private MyHandler myHandler = new MyHandler(this);

    private List<Question> lists = new ArrayList<>();

    @BindView(R.id.recycler_question)
    RecyclerView recyclerView;
    @BindView(R.id.swipefresh_quest)
    SwipeRefreshLayout swipeRefreshLayout;
    QuestionAdapter questionAdapter;
    private int pageNum=1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question,container,false);
        binder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fresh();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //                        放入要刷新的数据
                        fresh();
//                        刷新的小圈圈是否显示
                        swipeRefreshLayout.setRefreshing(false);
                    }


                },0);
            }
        });
        Log.i("result", "onViewCreated: "+URLList.GET_QUESTION + ACache.get(getActivity()).getAsString("token"));

    }

    private void fresh() {
        OkHttpUtil.getDefault(this)
                //接口请求参数加上页数，（页数是应该加的）
                .doGetAsync(HttpInfo.Builder().setUrl(URLList.GET_QUESTION + ACache.get(getActivity()).getAsString("token")+"&page="+pageNum).build(), new Callback() {
                    @Override
                    public void onSuccess(HttpInfo info) throws IOException {
                        QuestionResult result = info.getRetDetail(QuestionResult.class);
                        //将数据加入到questionAdapter,但是因为里面原先是空的，所以加载一张页面的时候是要替代空的post_list的
//                        questionAdapter.replaceData(result.getPost_list());
                        //页数加加之后就变成了2，那么应该重新请求一下参数，将后来获取到的数据加上到原来的List里面
                        questionAdapter.addData(result.getPost_list());
                    }

                    @Override
                    public void onFailure(HttpInfo info) throws IOException {
                        Toast.makeText(getActivity(),"网络请求失败",Toast.LENGTH_LONG).show();
                    }
                });
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        questionAdapter = new QuestionAdapter(R.layout.item_question,lists);
        recyclerView.setAdapter(questionAdapter);
        //下拉框添加数据
        questionAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //滑动最后一个item的时候回调onLoadMoreListener方法
        questionAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mShowType++;
                swipeRefreshLayout.setEnabled(false);  //上拉加载的时候关闭下拉刷新 之后再打开
                if (mShowType ==3) {
                    myHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //获取数据失败
                            questionAdapter.loadMoreFail();
                        }
                    }, DELAY_MILLIS);
                } else if (mShowType>=6) {
                    myHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //数据全部加载完毕
                            questionAdapter.loadMoreEnd();
                            Toast.makeText(getActivity(),"全部加载完毕",Toast.LENGTH_LONG).show();
                        }
                    }, DELAY_MILLIS);
                } else {
                    myHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //成功获取更多数据
//                            questionAdapter.addData(addDatas());
                            //页数加载之后，执行fresh()方法，将数据加载到List后面
                            pageNum++;
                            fresh();
                            //获得的数据条数赋给mshowType;
                            mShowType=questionAdapter.getData().size();
                            //加载完成不是加载结束，而是本次数据加载结束并且还有下页数据
                            questionAdapter.loadMoreComplete();
                            Toast.makeText(getActivity(),"本页加载结束",Toast.LENGTH_LONG).show();
                        }
                    }, DELAY_MILLIS);
                }
            }
        },recyclerView);

    }
//刷新列表

    private static class MyHandler extends Handler {

        private WeakReference<QuestionFragment> fragmentWeakReference;

        public MyHandler(QuestionFragment fragment) {
            fragmentWeakReference = new WeakReference<QuestionFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            QuestionFragment fragment = fragmentWeakReference.get();
            if (fragment == null) {
                return;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binder.unbind();
    }
}
