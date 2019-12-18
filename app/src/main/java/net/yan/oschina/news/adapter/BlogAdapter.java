package net.yan.oschina.news.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.yan.oschina.news.entity.Blog;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BlogAdapter extends BaseQuickAdapter<Blog, BaseViewHolder> {

    public BlogAdapter(int layoutResId, @Nullable List<Blog> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Blog item) {

    }
}
