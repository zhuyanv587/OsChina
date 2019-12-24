package net.yan.oschina.tweet.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.yan.oschina.R;
import net.yan.oschina.tweet.entity.Thrum;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ThrumAdapter extends BaseQuickAdapter<Thrum, BaseViewHolder> {
    public ThrumAdapter(int layoutResId, @Nullable List<Thrum> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Thrum item) {
        helper.setText(R.id.thrum_title, item.getId() + "");
        helper.setText(R.id.thrum_content, item.getBody());
        helper.setText(R.id.thrum_author, item.getAuthor());
        helper.setText(R.id.thrum_time, item.getPubDate());
        helper.setText(R.id.thrum_num, item.getCommentCount() + "");
    }
}
