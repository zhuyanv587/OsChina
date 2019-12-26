package net.yan.oschina.news.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.yan.oschina.R;
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
        helper.setText(R.id.blog_title,item.getTitle());
        helper.setText(R.id.blog_author,item.getAuthor());
        helper.setText(R.id.blog_time,item.getPubDate());
        helper.setText(R.id.blog_num,item.getCommentCount()+"");
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }
}
