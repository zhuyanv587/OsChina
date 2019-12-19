package net.yan.oschina.news.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class QuestionFragment extends Fragment {
    private Unbinder binder;

    private List<Question> lists = new ArrayList<>();

    @BindView(R.id.recycler_question)
    RecyclerView recyclerView;

    QuestionAdapter questionAdapter;
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
                        System.out.println(info);
                    }
                });
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        questionAdapter = new QuestionAdapter(R.layout.item_question,lists);
        recyclerView.setAdapter(questionAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binder.unbind();
    }
}
