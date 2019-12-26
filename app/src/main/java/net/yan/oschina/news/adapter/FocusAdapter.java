package net.yan.oschina.news.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.yan.oschina.R;
import net.yan.oschina.news.entity.Focus;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FocusAdapter extends BaseQuickAdapter<Focus, BaseViewHolder> {

    public FocusAdapter(int layoutResId, @Nullable List<Focus> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Focus item) {

        ImageView imageView = helper.getView(R.id.foucs2_img1);
        Glide.with(mContext).load(item.getPortrait()).into(imageView);
        helper.setText(R.id.foucs2_author,item.getAuthor());
        helper.setText(R.id.foucs2_time1,item.getPubDate());
        helper.setText(R.id.foucs2_text1,item.getBody());
        helper.setText(R.id.foucs2_num,item.getCommentCount()+"");
    }
}
