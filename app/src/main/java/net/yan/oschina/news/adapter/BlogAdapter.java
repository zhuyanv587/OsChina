package net.yan.oschina.news.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.callback.Callback;

import net.yan.oschina.R;
import net.yan.oschina.net.URLList;
import net.yan.oschina.news.entity.Blog;
import net.yan.oschina.news.entity.BlogInformation;
import net.yan.oschina.util.ACache;
import net.yan.oschina.util.WebViewURL;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BlogAdapter extends BaseQuickAdapter<Blog, BaseViewHolder> {

    public BlogAdapter(int layoutResId, @Nullable List<Blog> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Blog item) {
        helper.setText(R.id.blog_title, item.getTitle());
        helper.setText(R.id.blog_author, item.getAuthor());
        helper.setText(R.id.blog_time, item.getPubDate());
        helper.setText(R.id.blog_id, item.getId() + "");
        helper.setText(R.id.blog_num, item.getCommentCount() + "");

        OkHttpUtil.getDefault(this)
                .doGetAsync(HttpInfo.Builder().setUrl(URLList.GET_BLOG_INFORMATION + "?id=" + item.getId() + "&access_token=" + ACache.get(mContext).getAsString("token")).build(), new Callback() {
                    @Override
                    public void onSuccess(HttpInfo info) throws IOException {
                        BlogInformation information = info.getRetDetail(BlogInformation.class);
                        WebViewURL.url = information.getUrl();
                    }

                    @Override
                    public void onFailure(HttpInfo info) throws IOException {
                        Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.OnItemClick(v, (Integer) v.getTag());
                }
            }
        });

        super.onBindViewHolder(holder, position);
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blog, parent, false);
        return new BaseViewHolder(view);
        //把Item布局拿来
    }
}
