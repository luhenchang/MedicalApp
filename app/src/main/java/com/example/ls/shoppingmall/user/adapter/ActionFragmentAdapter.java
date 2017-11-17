package com.example.ls.shoppingmall.user.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.user.bean.MyLiveList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ls on 2017/11/16.
 */

public class ActionFragmentAdapter extends RecyclerView.Adapter<ActionFragmentAdapter.ViewHolder> {

    private static final int MYLIVE_MODE_CHECK = 0;
    int mEditMode = MYLIVE_MODE_CHECK;

    private int secret = 0;
    private String title = "";
    private Context context;
    private List<MyLiveList> mMyLiveList;
    private OnItemClickListener mOnItemClickListener;

    public ActionFragmentAdapter(Context context) {
        this.context = context;
    }

    //这里来进行初始化数据和判断是否已经存在集合。并刷新适配器自己
    public void notifyAdapter(List<MyLiveList> myLiveList, boolean isAdd) {
        if (!isAdd) {
            this.mMyLiveList = myLiveList;
        } else {
            this.mMyLiveList.addAll(myLiveList);
        }
        notifyDataSetChanged();
    }
    //提供给外界一个方法来获取目前集合数据
    public List<MyLiveList> getMyLiveList() {
        if (mMyLiveList == null) {
            mMyLiveList = new ArrayList<>();
        }
        return mMyLiveList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_action_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mMyLiveList.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //这里来赋值控件
        final MyLiveList myLive = mMyLiveList.get(holder.getAdapterPosition());
        holder.mTvTitle.setText(myLive.getTitle());
        holder.mTvSource.setText(myLive.getSource());
        //如果是取消状态下那么就不显示可点击图片
        if (mEditMode == MYLIVE_MODE_CHECK) {
            holder.mCheckBox.setVisibility(View.GONE);
        } else {
            holder.mCheckBox.setVisibility(View.VISIBLE);
            //判断是否被选中如果选中那么这里来赋值为选中的图片
            if (myLive.isSelect()) {
                holder.mCheckBox.setImageResource(R.mipmap.ic_checked);
            } else {
                holder.mCheckBox.setImageResource(R.mipmap.ic_uncheck);
            }
        }
        //给整个item来设置监听事件。如果点击了去回调我们activity里面的。
        holder.mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("hole_posiotn",holder.getAdapterPosition()+"");
                Log.e("hole_posiotn",mMyLiveList.toString()+"");

                mOnItemClickListener.onItemClickListener(holder.getAdapterPosition(), mMyLiveList);
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClickListener(int pos, List<MyLiveList> myLiveList);
    }
    public void setEditMode(int editMode) {
        mEditMode = editMode;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvTitle;
        TextView mTvSource;
        LinearLayout mRootView;
        ImageView mCheckBox;

//<!--ft_action_item_tv_title ft_action_item_content_tv ft_message_term_li check_box-->


        public ViewHolder(View itemView) {
            super(itemView);
            mTvTitle=itemView.findViewById(R.id.ft_action_item_tv_title);
            mTvSource=itemView.findViewById(R.id.ft_action_item_content_tv);
            mRootView=itemView.findViewById(R.id.ft_message_term_li);
            mCheckBox=itemView.findViewById(R.id.check_box);

        }
    }
}