package net.yan.oschina.news.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        this.blogList=data;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Blog item) {
        helper.setText(R.id.blog_title,item.getTitle());
        helper.setText(R.id.blog_author,item.getAuthor());
        helper.setText(R.id.blog_time,item.getPubDate());
        helper.setText(R.id.blog_num,item.getCommentCount()+"");
    }

    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    public interface OnItemClickListener{
        void OnItemClick(View view, int position);
    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.OnItemClick(v, (Integer) v.getTag());
                }
            }
        });
        super.onBindViewHolder(holder, position);
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blog,parent,false);
        return new BaseViewHolder(view);
        //把Item布局拿来
    }
}
