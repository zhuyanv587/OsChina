package net.yan.oschina.tweet.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.yan.oschina.R;
import net.yan.oschina.tweet.entity.Latest;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LatestAdapter extends BaseQuickAdapter<Latest, BaseViewHolder> {

    public LatestAdapter(int layoutResId, @Nullable List<Latest> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Latest item) {
        ImageView imageView = helper.getView(R.id.latest_image);
        Glide.with(mContext).load(item.getPortrait())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView);
        helper.setText(R.id.latest_author,item.getAuthor());
        helper.setText(R.id.latest_title,item.getBody());
        helper.setText(R.id.latest_time,item.getPubDate());
        helper.setText(R.id.latest_discuss,item.getCommentCount()+"");
    }
}
