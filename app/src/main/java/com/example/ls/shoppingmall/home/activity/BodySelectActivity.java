package com.example.ls.shoppingmall.home.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.home.adapter.BodySearchListAdapter;
import com.example.ls.shoppingmall.home.bean.SelectAdapterBean;
import com.example.ls.shoppingmall.home.home_db.HistoryDb;
import com.example.ls.shoppingmall.utils.layoututils.MyFlowLayout;

import org.xutils.DbManager;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class BodySelectActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private EditText searchView;
    private ArrayList<SelectAdapterBean> mData;
    private MyFlowLayout myFlowLayout_header, myFlowLayout_middle, myFlowLayout_footer, myFlowLayout_mowei;
    private String[] mHoritorData;
    private TextView mTvSearch,mSearch_db_delect;
    private ListView mAt_bady_lv;
    private ScrollView ac_body_scroll;
    private List<String> mSearchData;
    private BodySearchListAdapter searAdapte;
    private ImageView mSearch_dele_iv;
    private String search_name;
    private DbManager db;

    //历史
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_body_select);
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig();
        daoConfig.setDbName("home_db.db");
        db = x.getDb(daoConfig);
        initView();
        initData();
        setData();
    }

    private void setData() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 5, 10, 5);
        for (int i = 0; i < mData.get(0).getStarry().length; i++) {
            TextView view = new TextView(this);
            view.setText(mData.get(0).getStarry()[i]);
            view.setTextSize(12);
            final int finalI = i;
            view.setTextColor(Color.parseColor("#555555"));
            view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.circle_text_shaper));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchView.setText(mData.get(0).getStarry()[finalI]);
                }
            });
            myFlowLayout_header.addView(view, params);
        }
        for (int i = 0; i < mData.get(1).getStarry().length; i++) {
            TextView view = new TextView(this);
            view.setText(mData.get(1).getStarry()[i]);
            view.setTextSize(12);
            final int finalI = i;
            view.setTextColor(Color.parseColor("#555555"));
            view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.circle_text_shaper));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchView.setText(mData.get(1).getStarry()[finalI]);
                }
            });
            myFlowLayout_middle.addView(view, params);

        }
        for (int i = 0; i < mData.get(2).getStarry().length; i++) {
            TextView view = new TextView(this);
            view.setText(mData.get(2).getStarry()[i]);
            view.setTextSize(12);
            final int finalI = i;
            view.setTextColor(Color.parseColor("#555555"));

            view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.circle_text_shaper));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchView.setText(mData.get(2).getStarry()[finalI]);
                }
            });
            myFlowLayout_footer.addView(view, params);
        }
        LinearLayout.LayoutParams paramss = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramss.setMargins(0, 5, 16, 11);
        if (mHoritorData!=null) {
            for (int i = mHoritorData.length-1; i>=0; i--) {
                TextView view = new TextView(this);
                view.setText(mHoritorData[i]);
                view.setTextSize(12);
                final int finalI = i;
                view.setTextColor(Color.parseColor("#555555"));
                view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.circle_text_shaper_nored));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchView.setText(mHoritorData[finalI]);
                    }
                });
                myFlowLayout_mowei.addView(view, paramss);
            }
        }
    }


    private void initData() {
        mSearchData = new ArrayList<>();
        mData = new ArrayList<>();


        SelectAdapterBean bean = new SelectAdapterBean();
        String[] starry = new String[7];
        bean.BodyName = "头部";
        starry[0] = "嘴巴";
        starry[1] = "鼻子";
        starry[2] = "鼻子";
        starry[3] = "太阳穴";
        starry[4] = "脑神经凌波节";
        starry[5] = "扁桃体";
        starry[6] = "食道";
        bean.setStarry(starry);


        SelectAdapterBean bean1 = new SelectAdapterBean();
        String[] starry1 = new String[7];
        bean1.BodyName = "身体";
        starry1[0] = "心脏";
        starry1[1] = "胃";
        starry1[2] = "肝掌";
        starry1[3] = "太阳穴";
        starry1[4] = "脑神经凌波节";
        starry1[5] = "手指";
        starry1[6] = "手指闻风丧胆发斯蒂芬";

        bean1.setStarry(starry1);


        SelectAdapterBean bean2 = new SelectAdapterBean();
        String[] string2 = new String[7];
        bean2.BodyName = "下体";
        string2[0] = "爆破了个";
        string2[1] = "生理功能";
        string2[2] = "给我";
        string2[3] = "施工费1";
        string2[4] = "按规定";
        string2[5] = "噶";
        string2[6] = "嘎嘎嘎";
        bean2.setStarry(string2);

        mData.add(bean);
        mData.add(bean1);
        mData.add(bean2);

        Log.e("string", mData.toString());

        //查找数据库里面的历史数据
        try {

            List<HistoryDb> mHori_arry = new ArrayList<>();
            mHori_arry = db.findAll(HistoryDb.class);
            if (mHori_arry != null) {
                if (mHori_arry.size() > 0) {
                    mHoritorData = new String[mHori_arry.size()];
                    for (int i = 0; i < mHori_arry.size(); i++) {
                        mHoritorData[i] = mHori_arry.get(i).getName();
                        Log.e("datai=", mHori_arry.get(i).getName());
                    }
                }
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        //1.头部搜索框部分
        searchView = (EditText) findViewById(R.id.searchView);
        mTvSearch = (TextView) findViewById(R.id.tv_search_header);
        mTvSearch.setOnClickListener(this);
        //2.中间ListView部分
        myFlowLayout_header = (MyFlowLayout) findViewById(R.id.select_adapter_xcflow_header);
        myFlowLayout_middle = (MyFlowLayout) findViewById(R.id.select_adapter_xcflow_body);
        myFlowLayout_footer = (MyFlowLayout) findViewById(R.id.select_adapter_xcflow_footer);
        myFlowLayout_mowei = (MyFlowLayout) findViewById(R.id.select_adapter_xcflow_mowei);

        //第三部分
        mAt_bady_lv = (ListView) findViewById(R.id.at_bady_lv);
        mAt_bady_lv.setOnItemClickListener(this);
        mSearch_dele_iv = (ImageView) findViewById(R.id.search_dele_iv);
        mSearch_dele_iv.setOnClickListener(this);
        ac_body_scroll = (ScrollView) findViewById(R.id.ac_body_scroll);
        mSearch_db_delect= (TextView) findViewById(R.id.search_db_delect);
        mSearch_db_delect.setOnClickListener(this);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //TODO:
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //TODO:
                ac_body_scroll.setVisibility(View.GONE);
                mAt_bady_lv.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    //这里去获取数据然后给泪飙设置适配器:
                    ac_body_scroll.setVisibility(View.GONE);
                    mAt_bady_lv.setVisibility(View.VISIBLE);
                } else {
                    ac_body_scroll.setVisibility(View.VISIBLE);
                    mAt_bady_lv.setVisibility(View.GONE);
                }
                //关闭键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                }
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
            }


        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("按下了back键   onBackPressed()");
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onClick(View v) {
        Intent toSearchIntent = new Intent(this, SymptomSelectActivity.class);
        switch (v.getId()) {
            case R.id.tv_search_header:
                if (!searchView.getText().toString().equals("")) {
                    search_name = searchView.getText().toString();
                    //这里面去获取数据给listView赋值
                    initDataForAdapter(search_name);
                    //储存到数据库里面
                    setStringToDb(search_name);

                    //  setAdaterTolist();
                } else {
                    Toast.makeText(this, "搜索内容不能为空哦！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.search_dele_iv:
                if (searchView.getText().toString().length() > 0) {
                    searchView.setText("");
                }
                break;
            case R.id.search_db_delect:
                Delect_DB();
                break;
            default:
                break;
        }
    }

    private void Delect_DB() {
        try {
            db.delete(HistoryDb.class, WhereBuilder.b("id",">","0"));
            myFlowLayout_mowei.setVisibility(View.GONE );
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private void initDataForAdapter(String string) {
        mSearchData.add("我啊你");
        mSearchData.add("我啊你");
        mSearchData.add("weaff");
        mSearchData.add("我啊你");
        mSearchData.add("wefadfa");
        mSearchData.add("是打发打发");
        mSearchData.add("我啊你");
        mSearchData.add("我啊啊打的费你");
        mSearchData.add("我啊你");
        mSearchData.add("啊打的费");
        mSearchData.add("大份");
        mSearchData.add("我啊你");
        mSearchData.add("搭噶上单给发噶");
        mSearchData.add("我啊你");
        mSearchData.add("我啊你阿打算的");
        mSearchData.add("大嘎达");
        mSearchData.add("ad规范化");
        mSearchData.add("我啊沙发上你");
        mSearchData.add("师范");
        mSearchData.add("我啊顺风使帆你");
        mSearchData.add("是福还是祸");//病症symptoms
        searAdapte = new BodySearchListAdapter(mSearchData, this);
        mAt_bady_lv.setAdapter(searAdapte);

    }

    //更新数据库里面的数据
    public void setStringToDb(String stringToDb) {
        List<HistoryDb> historyList = new ArrayList<>();//获取所有数据这里
        try {
            historyList = db.selector(HistoryDb.class).where("id", ">", "0").findAll();
            if (historyList != null) {
                //更新
                if (historyList.size() < 10) {
                    HistoryDb histry0bj = new HistoryDb();
                    //去重复的
                    int j=0;
                    for (int i = 0; i <historyList.size() ; i++) {
                        Log.e("result",historyList.get(i).name);
                        if(historyList.get(i).name.toString().equals(stringToDb)){
                           j++;
                        }
                    }
                    if(j==0) {
                        histry0bj.name = stringToDb;
                        db.save(histry0bj);
                    }
                } else {
                    //增加
                    HistoryDb his = db.findById(HistoryDb.class,10);
                    his.setName(stringToDb);
                    db.update(his, "name");
                }
            } else {
                //创建增加
                HistoryDb data = new HistoryDb();
                data.name = stringToDb;
                db.save(data);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }

    }
    //完善perfect
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intents=new Intent(this,SymptomSelectActivity.class);
        intents.putExtra("name",mSearchData.get(position));
        startActivity(intents);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }
}
