package net.yan.oschina.my.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.yan.oschina.R;
import net.yan.oschina.my.entity.My;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    //数据源
    private List<My> mList;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder {
        //用于子项中图片控件的缓存
        ImageView MyImage;
        TextView MyIntro;
        ImageView Bracket;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            MyImage=itemView.findViewById(R.id.my_image);
            MyIntro=itemView.findViewById(R.id.my_intro);
            Bracket=itemView.findViewById(R.id.my_bracket);
        }
    }

    //声明自定义的接口
    OnItemClickListener listener;

    //点击事件的步骤,自定义一个回调接口来实现click事件
    public interface OnItemClickListener{
        //view和位置
        void onItemClick(View view,int position);
    }
    //定义方法并暴露给外面的调用者
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MyAdapter( Context mContext,List<My> myList) {
        //上下文
        this.mContext=mContext;
        //实体类数据
        this.mList = myList;
    }

    //onCreatViewHolder():创建并返回ViewHolder的实例，并且参数是动态加载的子项布局
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        //动态加载布局
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my,parent,false);
        //创建ViewHolder实例，参数为刚加载进来的子项布局
        ViewHolder viewHolder=new ViewHolder(view);
//        控制RecyclerView里面item在屏幕里的数量
        int parentHeight=parent.getHeight();
        parent.getWidth();
        ViewGroup.LayoutParams layoutParams=viewHolder.itemView.getLayoutParams();
        layoutParams.height=(parentHeight/6);

        return viewHolder;
    }

    //onBindViewHolder:对Recycler的子项的数据进行赋值
    //int,position，position是滚入到屏幕内子项的编号
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        根据点击位置绑定数据
        My my=mList.get(position);
        holder.itemView.setTag(position);
        holder.MyImage.setImageResource(my.getImage());
        holder.MyIntro.setText(my.getIntro());
        holder.Bracket.setImageResource(my.getBracket());
        //设置itemView的点击事件,整个的Item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onItemClick(v, (Integer) v.getTag());
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }
}
