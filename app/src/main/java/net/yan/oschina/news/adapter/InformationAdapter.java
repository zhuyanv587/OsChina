package net.yan.oschina.news.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.yan.oschina.R;
import net.yan.oschina.news.entity.Information;

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
        helper.setText(R.id.text5,item.getPubDate());
        helper.setText(R.id.text6,item.getCommentCount()+"");
    }
}
