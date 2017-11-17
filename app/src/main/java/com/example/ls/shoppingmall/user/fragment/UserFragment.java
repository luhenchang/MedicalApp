package com.example.ls.shoppingmall.user.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.base.BaseFragment;
import com.example.ls.shoppingmall.user.activity.MessageActivity;
import com.example.ls.shoppingmall.user.activity.MyInformationActivity;
import com.example.ls.shoppingmall.user.activity.MyOrderActivity;
import com.example.ls.shoppingmall.user.activity.MyRestMoneyActivity;
import com.example.ls.shoppingmall.user.activity.RechargMoneyActivity;
import com.example.ls.shoppingmall.utils.layoututils.CircleImageView;

import static android.content.ContentValues.TAG;

/**
 * Created by ls on 2017/11/8.
 * <p>
 * 用户页面的Fragment
 */

public class UserFragment extends BaseFragment implements View.OnClickListener {
    private TextView mMessageTv;
    private CircleImageView mHeaderImage;
    private LinearLayout mRestLinelayout, mOrderLinelayout, mRechargLayout;

    //实例化
    @Override
    public View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_mine, null);
        //  ivMineLogo = view.findViewById(R.id.ivMineLogo);
        initViews(view);
        initListener();
        return view;
    }

    private void initViews(View view) {
        mMessageTv = view.findViewById(R.id.ft_message_tv);
        mHeaderImage = view.findViewById(R.id.ft_user_header_iv);
        mRechargLayout = view.findViewById(R.id.ft_chonzhi_lt);
        mOrderLinelayout = view.findViewById(R.id.ft_order_lint);
        mRestLinelayout = view.findViewById(R.id.ft_restmoney_lint);
    }


    private void initListener() {
        mMessageTv.setOnClickListener(this);
        mHeaderImage.setOnClickListener(this);
        mRechargLayout.setOnClickListener(this);
        mRestLinelayout.setOnClickListener(this);
        mOrderLinelayout.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ft_message_tv:
                Intent messageintent = new Intent(mContext, MessageActivity.class);
                startActivity(messageintent);
                break;
            case R.id.ft_user_header_iv:
                Intent headerintent = new Intent(mContext, MyInformationActivity.class);
                startActivity(headerintent);
                break;
            case R.id.ft_chonzhi_lt:
                Intent chonzhiIntent = new Intent(mContext, RechargMoneyActivity.class);
                startActivity(chonzhiIntent);
                break;
            case R.id.ft_order_lint:
                Intent orderintent = new Intent(mContext, MyOrderActivity.class);
                startActivity(orderintent);
                break;
            case R.id.ft_restmoney_lint:
                Intent restmoney = new Intent(mContext, MyRestMoneyActivity.class);
                startActivity(restmoney);
                break;
        }
    }
}
