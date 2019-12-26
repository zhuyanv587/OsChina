package net.yan.oschina.news.fragment;

import android.graphics.Color;
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

public class QuestionFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private Unbinder binder;
    private int mShowType=0;
    private MyHandler myHandler=new MyHandler(this);

    private List<Question> lists = new ArrayList<>();

    @BindView(R.id.recycler_question)
    RecyclerView recyclerView;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    QuestionAdapter questionAdapter;
    //延迟事间
    private static final int DELAY_MILLIS=1500;
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
        Log.i("result", "onViewCreated: "+URLList.GET_QUESTION + ACache.get(getActivity()).getAsString("token"));
        OkHttpUtil.getDefault(this)
                .doGetAsync(HttpInfo.Builder().setUrl(URLList.GET_QUESTION + ACache.get(getActivity()).getAsString("token")).build(), new Callback() {
                    @Override
                    public void onSuccess(HttpInfo info) throws IOException {
                        QuestionResult result = info.getRetDetail(QuestionResult.class);
                        questionAdapter.replaceData(result.getPost_list());
                    }

                    @Override
                    public void onFailure(HttpInfo info) throws IOException {
                        Toast.makeText(getActivity(),"网络请求失败",Toast.LENGTH_LONG).show();
                    }
                });
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        questionAdapter = new QuestionAdapter(R.layout.item_question,lists);
        recyclerView.setAdapter(questionAdapter);
        //
        questionAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        questionAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mShowType++;
                if (mShowType == 2) {
                    myHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            questionAdapter.loadMoreFail();
                        }
                    }, DELAY_MILLIS);
                } else if (mShowType >= 4) {
                    myHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            questionAdapter.loadMoreEnd();
                        }
                    }, DELAY_MILLIS);
                } else {
                    myHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            questionAdapter.addData(addDatas());
                            questionAdapter.loadMoreComplete();
                        }
                    }, DELAY_MILLIS);
                }
            }
        });
//        addHeadView();
    }

//    private void addHeadView() {
//        View headerView = getLayoutInflater().inflate(R.layout.rv_header, null);
//        headerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        questionAdapter.addHeaderView(headerView);
//        headerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "your click headerView", Snackbar.LENGTH_SHORT).show();
//            }
//        });
//    }
//刷新列表

    //增加信息,这里应该是新增加的信息
    public static List<Question> addDatas() {
        List<Question> mList = new ArrayList<>();
        return mList;
    }

    @Override
    public void onRefresh() {
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mShowType = 0;
                questionAdapter.setNewData(lists);
                swipeRefreshLayout.setRefreshing(false);
            }
        }, DELAY_MILLIS);
    }

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
