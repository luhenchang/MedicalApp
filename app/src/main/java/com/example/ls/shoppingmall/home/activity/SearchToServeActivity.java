package com.example.ls.shoppingmall.home.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Window;
import android.widget.EditText;

import com.example.ls.shoppingmall.R;

public class SearchToServeActivity extends AppCompatActivity {
    private String mSearch_str;
    private EditText mSearchView_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search_to_serve);
        Intent intent = getIntent();
        if (intent != null) {
            mSearch_str = intent.getStringExtra("search_name");
            Log.e("search_name",mSearch_str );
        }
        initView();
    }

    private void initView() {
        mSearchView_et = (EditText) findViewById(R.id.searchView_ets);
        mSearchView_et.setText(mSearch_str);
        mSearchView_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //TODO:
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //TODO:
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }
}
