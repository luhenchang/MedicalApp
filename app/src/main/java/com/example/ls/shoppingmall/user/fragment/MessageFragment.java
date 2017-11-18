package com.example.ls.shoppingmall.user.fragment;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.base.BaseFragment;
import com.example.ls.shoppingmall.user.activity.MessageActivity;
import com.example.ls.shoppingmall.user.adapter.MessageFragmentAdapter;
import com.example.ls.shoppingmall.user.bean.MyLiveList;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ls on 2017/11/16.
 */

public class MessageFragment extends BaseFragment implements MessageFragmentAdapter.OnItemClickListener, View.OnClickListener, MessageActivity.EditMyFragmentItem {
    private static final int MYLIVE_MODE_CHECK = 0;
    private static final int MYLIVE_MODE_EDIT = 1;
    private RecyclerView mRecyclerView;
    public MessageFragmentAdapter adapter;
    private List<MyLiveList> mList;
    private TwinklingRefreshLayout swipterfreshlayout;
    private Button mBtnDelete;
    private TextView mSelectAll;
    private TextView mTvSelectNum;
    private LinearLayout mLIMycollectionBottomDialog;
    private TextView mBtnEditor;
    public  int mEditMode = MYLIVE_MODE_CHECK;
    private boolean isSelectAll = false;
    private boolean editorStatus = false;
    private int index = 0;

    UpdateTextResult updateTextresutl;

    @Override
    public View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_message_layout, null);
        initViews(view);
        return view;
    }

    private void initListenner() {
        adapter.setOnItemClickListener(this);
        mBtnDelete.setOnClickListener(this);
        mSelectAll.setOnClickListener(this);
        mBtnEditor.setOnClickListener(this);
    }

    private void initViews(View view) {
        adapter = new MessageFragmentAdapter(mContext);
        mTvSelectNum = view.findViewById(R.id.tv_select_num);
        mBtnDelete = view.findViewById(R.id.btn_delete);
        mSelectAll = view.findViewById(R.id.select_all);
        mBtnEditor = view.findViewById(R.id.btn_editor);
        mLIMycollectionBottomDialog = view.findViewById(R.id.ll_mycollection_bottom_dialog);

        swipterfreshlayout = view.findViewById(R.id.swipterfreshlayout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.ft_message_rw);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mList = new ArrayList<>();
        //下拉刷新
        swipterfreshlayout.setOnRefreshListener(new TwinklingRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefreshing();
                    }
                }, 1000);
            }

            //上啦加载
            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadmore();
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void initData() {
        mRecyclerView.setAdapter(adapter);
        for (int i = 0; i < 30; i++) {
            MyLiveList myLiveList = new MyLiveList();
            myLiveList.setTitle("这是第" + i + "个条目");
            myLiveList.setSource("来源" + i);
            mList.add(myLiveList);

        }
        //这里设置数据
        adapter.notifyAdapter(mList, false);
        initListenner();
    }

    /**
     * 根据选择的数量是否为0来判断按钮的是否可点击.
     *
     * @param size
     */
    private void setBtnBackground(int size) {
        if (size != 0) {
            mBtnDelete.setBackgroundResource(R.drawable.button_shape);
            mBtnDelete.setEnabled(true);
            mBtnDelete.setTextColor(Color.WHITE);
        } else {
            mBtnDelete.setBackgroundResource(R.drawable.button_noclickable_shape);
            mBtnDelete.setEnabled(false);
            mBtnDelete.setTextColor(ContextCompat.getColor(mContext, R.color.color_b7b8bd));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_delete:
                deleteVideo();
                break;
            case R.id.select_all:
                selectAllMain();
                break;
            case R.id.btn_editor:
                //点击编辑和取消按钮同一个按钮
                updataEditMode();
                break;
            default:
                break;
        }
    }

    /**
     * 全选和反选
     */
    private void selectAllMain() {
        if (adapter == null) return;
        if (!isSelectAll) {
            for (int i = 0, j = adapter.getMyLiveList().size(); i < j; i++) {
                adapter.getMyLiveList().get(i).setSelect(true);
            }
            index = adapter.getMyLiveList().size();
            mBtnDelete.setEnabled(true);
            mSelectAll.setText("取消全选");
            isSelectAll = true;
        } else {
            for (int i = 0, j = adapter.getMyLiveList().size(); i < j; i++) {
                adapter.getMyLiveList().get(i).setSelect(false);
            }
            index = 0;
            mBtnDelete.setEnabled(false);
            mSelectAll.setText("全选");
            isSelectAll = false;
        }
        adapter.notifyDataSetChanged();
        setBtnBackground(index);
        mTvSelectNum.setText(String.valueOf(index));
    }

    /**
     * 删除逻辑
     */
    private void deleteVideo() {
        if (index == 0) {
            mBtnDelete.setEnabled(false);
            return;
        }
        final AlertDialog builder = new AlertDialog.Builder(mContext)
                .create();
        builder.show();
        if (builder.getWindow() == null) return;
        builder.getWindow().setContentView(R.layout.pop_user);//设置弹出框加载的布局
        TextView msg = (TextView) builder.findViewById(R.id.tv_msg);
        Button cancle = (Button) builder.findViewById(R.id.btn_cancle);
        Button sure = (Button) builder.findViewById(R.id.btn_sure);
        if (msg == null || cancle == null || sure == null) return;

        if (index == 1) {
            msg.setText("删除后不可恢复，是否删除该条目？");
        } else {
            msg.setText("删除后不可恢复，是否删除这" + index + "个条目？");
        }
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });
        //弹窗确定删除
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = adapter.getMyLiveList().size(), j = 0; i > j; i--) {
                    MyLiveList myLive = adapter.getMyLiveList().get(i - 1);
                    if (myLive.isSelect()) {

                        adapter.getMyLiveList().remove(myLive);
                        index--;
                    }
                }
                index = 0;
                mTvSelectNum.setText(String.valueOf(0));
                //判断是否可以删除操作：这里是全选删除所以调用之后不能点击删除了
                setBtnBackground(index);
                if (adapter.getMyLiveList().size() == 0) {
                    mLIMycollectionBottomDialog.setVisibility(View.GONE);
                }
                adapter.notifyDataSetChanged();
                builder.dismiss();
            }
        });
    }

    @Override
    public void EditeItem() {
        updataEditMode();
    }


    public void updataEditMode() {
        //这里是判断是编辑模式还是取消模式：默认是编辑模式,点击了所以是取消模式，否则是编辑模式：1代表的是编辑模式，0代表的是取消模式。
        mEditMode = mEditMode == MYLIVE_MODE_CHECK ? MYLIVE_MODE_EDIT : MYLIVE_MODE_CHECK;
        if (mEditMode == MYLIVE_MODE_EDIT) {
            mBtnEditor.setText("取消");
            updateTextresutl.updateText("message","取消");
            //取消模式所以这时候下面全选删除的提示控件布局是显示的哦！
            mLIMycollectionBottomDialog.setVisibility(View.VISIBLE);
            //记录是否可以编辑。
            editorStatus = true;


        } else {
            updateTextresutl.updateText("message","编辑");
            mBtnEditor.setText("编辑");
            mLIMycollectionBottomDialog.setVisibility(View.GONE);
            //标记不能编辑：例如删除等
            editorStatus = false;
            //清除所有的选择按钮的背景。看起来没有选择图标
            clearAll();
        }
        adapter.setEditMode(mEditMode);
    }

    //这里是字体为编辑状态所以一起都消失：哪个原点和下面的布局
    public void clearAll() {
        mTvSelectNum.setText(String.valueOf(0));
        isSelectAll = false;
        mSelectAll.setText("全选");
        setBtnBackground(0);
    }

    @Override
    public void onItemClickListener(int pos, List<MyLiveList> myLiveList) {
        if (editorStatus) {
            MyLiveList myLive = myLiveList.get(pos);
            boolean isSelect = myLive.isSelect();
            if (!isSelect) {
                index++;
                myLive.setSelect(true);
                if (index == myLiveList.size()) {
                    isSelectAll = true;
                    mSelectAll.setText("取消全选");
                   // updateTextresutl.updateText("message","取消全选");

                }

            } else {
                myLive.setSelect(false);
                index--;
                isSelectAll = false;
                mSelectAll.setText("全选");
               // updateTextresutl.updateText("message","全选");

            }
            setBtnBackground(index);
            mTvSelectNum.setText(String.valueOf(index));
            adapter.notifyDataSetChanged();
        }
    }

    public void setEdeterInterface(MessageActivity messageActivity) {
        updateTextresutl = messageActivity;
    }
}
