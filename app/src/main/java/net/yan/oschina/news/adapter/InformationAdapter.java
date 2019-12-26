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
import net.yan.oschina.news.entity.BlogInformation;
import net.yan.oschina.news.entity.Information;
import net.yan.oschina.news.entity.News;
import net.yan.oschina.news.entity.NewsInformation;
import net.yan.oschina.util.ACache;
import net.yan.oschina.util.WebViewURL;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class InformationAdapter extends BaseQuickAdapter<Information, BaseViewHolder> {

    public InformationAdapter(int layoutResId, @Nullable List<Information> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Information item) {
        helper.setText(R.id.text1,item.getTitle());
        helper.setText(R.id.text4,item.getAuthor());
        helper.setText(R.id.text5,item  .getPubDate());
        helper.setText(R.id.text6,item.getCommentCount()+"");

        OkHttpUtil.getDefault(this)
                .doGetAsync(HttpInfo.Builder().setUrl(URLList.GET_INFORMATION_DETAIL + "?id=" + item.getId() + "&access_token=" + ACache.get(mContext).getAsString("token")).build(), new Callback() {
                    @Override
                    public void onSuccess(HttpInfo info) throws IOException {
                        NewsInformation information = info.getRetDetail(NewsInformation.class);
                        WebViewURL.URL_NEWS = information.getUrl();
                    }

                    @Override
                    public void onFailure(HttpInfo info) throws IOException {
                        Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_LONG).show();
                    }
                });
    }


    private InformationAdapter.OnItemClickListener listener;

    public void setOnItemClickListener(InformationAdapter.OnItemClickListener listener) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_information, parent, false);
        return new BaseViewHolder(view);
        //把Item布局拿来
    }
}
