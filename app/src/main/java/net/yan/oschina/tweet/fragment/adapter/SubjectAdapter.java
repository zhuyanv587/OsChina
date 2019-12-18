package net.yan.oschina.tweet.fragment.adapter;

import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


import net.yan.oschina.tweet.fragment.entity.Subject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public  class SubjectAdapter extends BaseQuickAdapter<Subject, BaseViewHolder> {

    public SubjectAdapter(int layoutResId, @Nullable List<Subject> data) {
        super(layoutResId,data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Subject item) {

    }
}
