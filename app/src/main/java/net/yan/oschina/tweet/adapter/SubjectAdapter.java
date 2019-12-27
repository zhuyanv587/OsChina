package net.yan.oschina.tweet.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.yan.oschina.R;
import net.yan.oschina.tweet.entity.Subject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public  class SubjectAdapter extends BaseQuickAdapter<Subject, BaseViewHolder> {

    public SubjectAdapter(int layoutResId, @Nullable List<Subject> data) {
        super(layoutResId,data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Subject item) {
        helper.setText(R.id.subject_title,"#"+item.getAuthor());
        ImageView imageView = helper.getView(R.id.subject_img1);
        Glide.with(mContext).load(item.getPortrait())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView);
        helper.setText(R.id.subject_text1,item.getBody());
        ImageView imageView1 = helper.getView(R.id.subject_img2);
        Glide.with(mContext).load(item.getPortrait())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView1);
        helper.setText(R.id.subject_text2,item.getBody());
        ImageView imageView2 = helper.getView(R.id.subject_img3);
        Glide.with(mContext).load(item.getPortrait())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView2);
        helper.setText(R.id.subject_text3,item.getBody());
    }
}
