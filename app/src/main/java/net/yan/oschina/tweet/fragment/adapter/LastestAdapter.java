package net.yan.oschina.tweet.fragment.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.yan.oschina.tweet.entity.Lastest;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LastestAdapter extends BaseQuickAdapter<Lastest, BaseViewHolder> {

    public LastestAdapter(int layoutResId, @Nullable List<Lastest> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Lastest item) {

    }
}
