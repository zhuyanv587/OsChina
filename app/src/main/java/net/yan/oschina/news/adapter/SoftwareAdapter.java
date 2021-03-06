package net.yan.oschina.news.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.yan.oschina.R;
import net.yan.oschina.news.entity.Software;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SoftwareAdapter extends BaseQuickAdapter<Software, BaseViewHolder> {
    public SoftwareAdapter(int layoutResId, @Nullable List<Software> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Software item) {
        helper.setText(R.id.title,item.getName());
    }
}
