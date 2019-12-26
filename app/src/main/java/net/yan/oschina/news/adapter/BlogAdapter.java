package net.yan.oschina.news.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.yan.oschina.R;
import net.yan.oschina.news.entity.Blog;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BlogAdapter extends BaseQuickAdapter<Blog, BaseViewHolder> {
    private List<Blog> blogList;

    public BlogAdapter(int layoutResId, @Nullable List<Blog> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Blog item) {
        helper.itemView.setTag(item);
        helper.setText(R.id.blog_title,item.getTitle());
        helper.setText(R.id.blog_author,item.getAuthor());
        helper.setText(R.id.blog_time,item.getPubDate());
        helper.setText(R.id.blog_num,item.getCommentCount()+"");
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.OnItemClick(v, (Integer) v.getTag());
                }
            }
        });
    }

    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    public interface OnItemClickListener{
        void OnItemClick(View view, int position);
    }
}
