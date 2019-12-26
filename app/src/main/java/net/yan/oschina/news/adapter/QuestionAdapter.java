package net.yan.oschina.news.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.yan.oschina.R;
import net.yan.oschina.news.entity.Question;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class QuestionAdapter extends BaseQuickAdapter<Question, BaseViewHolder> {

    public QuestionAdapter(int layoutResId, @Nullable List<Question> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Question item) {
        ImageView imageView = helper.getView(R.id.question_image);
        Glide.with(mContext).load(item.getPortrait()).into(imageView);
        helper.setText(R.id.question_title,item.getTitle());
        helper.setText(R.id.question_author,item.getAuthor());
        helper.setText(R.id.question_time,item.getPubDate());
        helper.setText(R.id.question_num,item.getAnswerCount()+"");
    }

}
