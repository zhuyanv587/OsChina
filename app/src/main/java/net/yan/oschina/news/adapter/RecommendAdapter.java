package net.yan.oschina.news.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.yan.oschina.R;
import net.yan.oschina.news.entity.Recommend;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RecommendAdapter extends BaseQuickAdapter<Recommend, BaseViewHolder> {
    public RecommendAdapter(int layoutResId, @Nullable List<Recommend> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Recommend item) {
        helper.setText(R.id.recommend_title,item.getTitle());
        helper.setText(R.id.recommend_author,item.getAuthor());
        helper.setText(R.id.recommend_time,item.getPubDate());
        helper.setText(R.id.recommend_num,item.getCommentCount()+"");
    }
}
