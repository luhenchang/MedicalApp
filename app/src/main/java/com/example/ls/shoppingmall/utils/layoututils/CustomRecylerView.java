package com.example.ls.shoppingmall.utils.layoututils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by ls on 2017/11/16.
 */

public class CustomRecylerView extends RecyclerView {
    public CustomRecylerView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public CustomRecylerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public CustomRecylerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }


}
