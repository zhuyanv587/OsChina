package net.yan.oschina.tweet.fragment.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.yan.oschina.entity.Hot;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class HotAdapter extends BaseQuickAdapter<Hot, BaseViewHolder> {

    public HotAdapter(int layoutResId, @Nullable List<Hot> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Hot item) {

    }
}
