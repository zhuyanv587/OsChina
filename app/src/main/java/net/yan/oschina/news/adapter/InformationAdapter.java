package net.yan.oschina.news.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.yan.oschina.entity.Information;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class InformationAdapter extends BaseQuickAdapter<Information, BaseViewHolder> {

    public InformationAdapter(int layoutResId, @Nullable List<Information> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Information item) {

    }
}
