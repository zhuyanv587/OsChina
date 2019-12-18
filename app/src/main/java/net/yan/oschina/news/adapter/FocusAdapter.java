package net.yan.oschina.news.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.yan.oschina.news.entity.Focus;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FocusAdapter extends BaseQuickAdapter<Focus, BaseViewHolder> {

    public FocusAdapter(int layoutResId, @Nullable List<Focus> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Focus item) {

    }
}
