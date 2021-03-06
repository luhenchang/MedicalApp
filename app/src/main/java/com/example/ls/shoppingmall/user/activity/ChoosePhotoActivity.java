package com.example.ls.shoppingmall.user.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.user.utils.FileCache;
import com.example.ls.shoppingmall.user.utils.ImageBig;
import com.example.ls.shoppingmall.user.utils.ImageTools;
import com.example.ls.shoppingmall.utils.layoututils.ClipImageLayout;

import java.io.File;
//对照片进行处理
public class ChoosePhotoActivity extends AppCompatActivity implements OnClickListener {

    private ClipImageLayout clipView;
    private Button btClip;
    private FileCache fileCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_photo);

        fileCache = new FileCache(this);

        initView();

        String path = getIntent().getStringExtra("imagePath");
    	//Bitmap bitmap = ImageBig.getimage(path);
        if (TextUtils.isEmpty(path) || !(new File(path).exists())) {
            Toast.makeText(this, "图片加载失败", Toast.LENGTH_SHORT).show();
            return;
        }
        Bitmap bitmap = ImageTools.convertToBitmap(path, 600, 600);
        if (bitmap == null) {
            Toast.makeText(this, "图片加载失败", Toast.LENGTH_SHORT).show();
            return;
        }
        clipView.setBitmap(bitmap);
    }

    private void initView() {
        btClip = (Button) findViewById(R.id.btClip);
        clipView = (ClipImageLayout) findViewById(R.id.clip);
        btClip.setOnClickListener(this);

        findViewById(R.id.ivBack).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btClip:
                saveBitmap();
                break;

            default:
                break;
        }
    }

    public void saveBitmap() {
        Bitmap bitmap = clipView.clip();
        String path = fileCache.getImageCacheDir().getAbsolutePath() + File.separator + System.currentTimeMillis() + ".png";
        ImageTools.savePhotoToSDCard(bitmap, path);
        Intent intent = new Intent();
        intent.putExtra("path", path);
        setResult(RESULT_OK, intent);
        finish();
    }
}
