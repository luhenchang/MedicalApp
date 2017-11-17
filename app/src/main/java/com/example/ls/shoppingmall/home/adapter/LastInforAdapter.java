package com.example.ls.shoppingmall.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.home.bean.LastInformationBean;
import com.example.ls.shoppingmall.utils.layoututils.ArcProgressbar;
import com.example.ls.shoppingmall.utils.layoututils.CircleProgressBar;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ls on 2017/11/14.
 */

public class LastInforAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private LastInformationBean mData;


    //五种类型
    private final int FISTS = -1;
    private final int CIRCLE = 0;//百分比
    private final int SUND_DOCTOR = 1;//周边医生
    private final int HEALTH_INFORMATION = 2;//健康咨询
    private final int RECOMEDATION = 3;//商品推荐

    public LastInforAdapter(Context mContext, LastInformationBean mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FISTS) {
            return new SearFistViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_lastinfor_item0, null), mContext);
        }
        if (viewType == CIRCLE) {
            return new FirstViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_lastinfor_item1, null), mContext);
        }
        if (viewType == SUND_DOCTOR) {
            return new SecondViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_lastinfor_item2, null), mContext);

        }
        if (viewType == HEALTH_INFORMATION) {
            return new ThreedViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_lastinfor_item3, null), mContext);

        }
        if (viewType == RECOMEDATION) {
            return new FourViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_lastinfor_item4, null), mContext);

        }
        return null;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.mList1.size() + mData.mList2.size()
                + mData.mList3.size() + mData.mList4.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return FISTS;
        } else if (position < mData.mList1.size() + 1) {
            return CIRCLE;
        } else if (position < mData.mList1.size() + mData.mList2.size() + 1) {
            return SUND_DOCTOR;
        } else if (position < mData.mList1.size() + mData.mList2.size() + mData.mList3.size() + 1) {
            return HEALTH_INFORMATION;
        } else {
            return RECOMEDATION;
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == FISTS) {
            SearFistViewHolder holder0= (SearFistViewHolder) holder;
        } else if (getItemViewType(position) == CIRCLE) {
            FirstViewHolder holder1 = (FirstViewHolder) holder;
            holder1.setData(mData.mList1.get(position-1), position);
        } else if (getItemViewType(position) == SUND_DOCTOR) {
            SecondViewHolder holder2 = (SecondViewHolder) holder;
            holder2.setData(mData.mList2.get(position - mData.mList1.size()-1), position);
        } else if (getItemViewType(position) == HEALTH_INFORMATION) {
            ThreedViewHolder holder2 = (ThreedViewHolder) holder;
            holder2.setData(mData.mList3.get(position - mData.mList1.size() - mData.mList2.size()-1), position);
        } else if (getItemViewType(position) == RECOMEDATION) {
            FourViewHolder holder2 = (FourViewHolder) holder;
            holder2.setData(mData.mList4.get(position - mData.mList1.size() - mData.mList2.size() - mData.mList3.size()-1), position);
        }
    }

    class FirstViewHolder extends RecyclerView.ViewHolder {
        private Context mContex;
        private String mList1;
        private TextView mTv;
        private View first_view;
        private CircleProgressBar arcProgressbar;
        private TextView hellowrod_tv;
        private int mProgress;

        public FirstViewHolder(View itemView, Context mContex) {
            super(itemView);
            this.mContex = mContex;
            mTv = itemView.findViewById(R.id.adapter_last_left_tv_one);
            hellowrod_tv = itemView.findViewById(R.id.hellowrod_tv);
            first_view = itemView.findViewById(R.id.first_view);
            arcProgressbar = itemView.findViewById(R.id.arcProgressbar);
        }

        public void setData(String mList1, final int position) {
            arcProgressbar.setProgress(70);
            mTv.setText(mList1);
            if (position == mData.mList1.size()+1) {
                first_view.setVisibility(View.GONE);
            } else {
                first_view.setVisibility(View.VISIBLE);

            }


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContex, "first=" + mData.mList1.get(position-1).toString(), Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

    class SearFistViewHolder extends RecyclerView.ViewHolder {
        private Context mContex;
        private String mList1;


        public SearFistViewHolder(View itemView, Context mContex) {
            super(itemView);
            this.mContex = mContex;
        }
    }

    class SecondViewHolder extends RecyclerView.ViewHolder {
        private Context mContex;
        private String str2;
        private TextView mTv;
        private View view, view1;
        private RelativeLayout mRlayout;
        private View itemview;

        public SecondViewHolder(View itemView, Context mContex) {
            super(itemView);
            this.mContex = mContex;
            this.itemview = itemView;
            mTv = itemView.findViewById(R.id.adapter_last_header_tv1_second);
            view = itemView.findViewById(R.id.item2_view);
            view1 = itemView.findViewById(R.id.item2_view1);
            mRlayout = itemView.findViewById(R.id.last_item2_rl);

        }

        public void setData(String mList1, final int position) {
            this.str2 = mList1;
            mTv.setText(str2);
            if (position == mData.mList1.size()+1) {
                view.setVisibility(View.VISIBLE);
                mRlayout.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
                mRlayout.setVisibility(View.GONE);
            }
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContex, "sec=" + mData.mList1.get(position - mData.mList1.size()-1).toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class ThreedViewHolder extends RecyclerView.ViewHolder {
        private Context mContex;
        private String str2;
        private TextView mTv;
        private RelativeLayout adapter_last_rl_tem3;
        private View view;
        private View itemView;

        public ThreedViewHolder(View itemView, Context mContex) {
            super(itemView);
            this.itemView = itemView;
            this.mContex = mContex;
            mTv = itemView.findViewById(R.id.adapter_last_header_tv1_threed);
            adapter_last_rl_tem3 = itemView.findViewById(R.id.adapter_last_rl_tem3);
            view = itemView.findViewById(R.id.item3_view);
        }

        public void setData(String mList1, final int position) {
            this.str2 = mList1;
            mTv.setText(str2);
            if (position == mData.mList1.size() + mData.mList2.size()+1) {
                adapter_last_rl_tem3.setVisibility(View.VISIBLE);
                view.setVisibility(View.VISIBLE);
            } else {
                adapter_last_rl_tem3.setVisibility(View.GONE);
                view.setVisibility(View.GONE);

            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContex, "sec=" + mData.mList1.get(position - mData.mList1.size() - mData.mList2.size()-1).toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class FourViewHolder extends RecyclerView.ViewHolder {
        private Context mContex;
        private String str2;
        private TextView mTv;
        private RelativeLayout item4_relati;
        private View itemvew4;

        public FourViewHolder(View itemView, Context mContex) {
            super(itemView);
            this.mContex = mContex;
            mTv = itemView.findViewById(R.id.adapter_last_header_tv4_four);
            item4_relati = itemView.findViewById(R.id.item4_relati);
            itemvew4 = itemView.findViewById(R.id.item4_view);


        }

        public void setData(String mList1, final int position) {
            // mTv.setText(mList1);
            if (position == mData.mList1.size() + mData.mList2.size() + mData.mList3.size()+1) {
                item4_relati.setVisibility(View.VISIBLE);
                itemvew4.setVisibility(View.VISIBLE);
            } else {
                item4_relati.setVisibility(View.GONE);
                itemvew4.setVisibility(View.GONE);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContex, "sec=" + mData.mList1.get(position - mData.mList1.size() - mData.mList2.size() - mData.mList3.size()-1), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
