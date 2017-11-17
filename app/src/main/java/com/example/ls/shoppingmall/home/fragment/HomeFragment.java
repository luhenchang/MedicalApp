package com.example.ls.shoppingmall.home.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.base.BaseFragment;
import com.example.ls.shoppingmall.home.activity.BodySelectActivity;
import com.example.ls.shoppingmall.home.activity.SearchToServeActivity;
import com.example.ls.shoppingmall.home.adapter.BodySearchListAdapter;
import com.example.ls.shoppingmall.home.bean.ManBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ls on 2017/11/8.
 * <p>
 * 主页面的Fragment
 */

public class HomeFragment extends BaseFragment {
    TextView tvSearchHome;
    ImageButton ibTop;
    private TextView tv_search_home;
    private RecyclerView rvHome;
    private ImageView home_man_iv;
    private boolean befor = true;
    private int count = 0;
    private boolean endOther = true;
    private FrameLayout mFralayout_header;
    private FrameLayout mFralayout_bootom;
    private FrameLayout mFralayout_foot;
    //这下面数据集合用来显示用户病情的
    private ArrayList<ManBean> header_afer;
    private AlertDialog alertDialog;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        tv_search_home=view.findViewById(R.id.tv_search_home);
        ibTop = (ImageButton) view.findViewById(R.id.ib_top);
        home_man_iv = view.findViewById(R.id.home_man_iv);
        mFralayout_header = view.findViewById(R.id.fl_top);
        mFralayout_bootom = view.findViewById(R.id.fl_bottom);
        mFralayout_foot = view.findViewById(R.id.fl_foot);
        initListener();
        return view;
    }

    //获取数据
    @Override
    public void initData() {
        header_afer = new ArrayList<>();
        ManBean manbean = new ManBean();
        manbean.str_number1 = "眼";
        manbean.str_number2 = "嘴巴";
        manbean.str_number3 = "鼻子";


        ManBean manbean1 = new ManBean();
        manbean1.str_number1 = "后脑";
        manbean1.str_number2 = "耳朵";
        manbean1.str_number3 = "脖子";

        ManBean manbean2 = new ManBean();
        manbean2.str_number1 = "心";
        manbean2.str_number2 = "干";
        manbean2.str_number3 = "胃";

        ManBean manbean3 = new ManBean();
        manbean3.str_number1 = "脊椎";
        manbean3.str_number2 = "肩膀";
        manbean3.str_number3 = "后背";

        ManBean manbean4 = new ManBean();
        manbean4.str_number1 = "膝盖";
        manbean4.str_number2 = "生殖器";
        manbean4.str_number3 = "脚";

        ManBean manbean5 = new ManBean();
        manbean5.str_number1 = "大腿";
        manbean5.str_number2 = "屁股";
        manbean5.str_number3 = "脚踝";

        header_afer.add(manbean);
        header_afer.add(manbean1);
        header_afer.add(manbean2);
        header_afer.add(manbean3);
        header_afer.add(manbean4);
        header_afer.add(manbean5);

    }

    private void initListener() {
        //如果点击头部：
        mFralayout_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count % 2 == 0 && endOther) {
                    showPopwindow(0);
                } else if (count % 2 != 0 && endOther) {
                    showPopwindow(1);
                }
            }
        });
        //中部位：
        mFralayout_bootom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count % 2 == 0 && endOther) {
                    showPopwindow(2);
                } else if (count % 2 != 0 && endOther) {
                    showPopwindow(3);
                }

            }
        });
        //尾部：
        mFralayout_foot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count % 2 == 0 && endOther) {
                    showPopwindow(4);
                } else if (count % 2 != 0 && endOther) {
                    showPopwindow(5);
                }

            }
        });
        //这里跳转到
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BodySelectActivity.class));
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });

        //2017年11月10日：实现点击旋转动画这个有点小复杂
        ibTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count % 2 == 0 && endOther) {
                    endOther = false;
                    ObjectAnimator oa = ObjectAnimator.ofFloat(home_man_iv, "rotationY",
                            new float[]{0f, 60f, 120f, 180f});
                    oa.setDuration(3000);
                    oa.setRepeatCount(0);
                    oa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            float playTime = (float) animation.getAnimatedValue();
                            if (playTime >= 90) {

                                home_man_iv.setBackgroundResource(R.mipmap.home_man_back);
                            }
                            if (playTime == 180f) {
                                count++;
                                endOther = true;
                            }
                        }
                    });
                    oa.start();
                } else if (endOther) {
                    endOther = false;
                    ObjectAnimator oa = ObjectAnimator.ofFloat(home_man_iv, "rotationY",
                            new float[]{0f, 60f, 120f, 180f});
                    oa.setDuration(3000);
                    oa.setRepeatCount(0);
                    oa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            float playTime = (float) animation.getAnimatedValue();
                            if (playTime >= 90) {

                                home_man_iv.setBackgroundResource(R.mipmap.home_man_befor);
                            }
                            if (playTime == 180f) {
                                count++;
                                endOther = true;
                            }
                        }
                    });
                    oa.start();
                }

            }
        });


    }

    //人体部位
    private void showPopwindow(int number) {
        final Intent intent = new Intent(getActivity(), BodySelectActivity.class);
        final ManBean manbean = header_afer.get(number);
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getActivity());
        builder.create();
        LayoutInflater mInflater = (LayoutInflater) getContext()
                .getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        View viewcontent = mInflater.inflate(R.layout.home_aler_dialog, null);
        TextView tv1 = viewcontent.findViewById(R.id.tv_item1);
        TextView tv2 = viewcontent.findViewById(R.id.tv_item2);
        TextView tv3 = viewcontent.findViewById(R.id.tv_item3);
        tv1.setText(manbean.str_number1);
        tv2.setText(manbean.str_number2);
        tv3.setText(manbean.str_number3);
        builder.setView(viewcontent);
        alertDialog = builder.show();
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("name", manbean.str_number1);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("name", manbean.str_number2);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("name", manbean.str_number3);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });

    }
    @Override
    public void onPause() {
        super.onPause();
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }
}
