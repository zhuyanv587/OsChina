package net.yan.oschina.news.adapter;

import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.yan.oschina.entity.English;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class EnglishAdapter extends BaseQuickAdapter<English, BaseViewHolder> {

    public EnglishAdapter(int layoutResId, @Nullable List<English> data) {
        super(layoutResId,data);
    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, English item) {

    }

}
