package net.yan.oschina.tweet.fragment.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.yan.oschina.entity.Thrum;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ThrumAdapter extends BaseQuickAdapter<Thrum, BaseViewHolder> {
    public ThrumAdapter(int layoutResId, @Nullable List<Thrum> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Thrum item) {

    }
}
