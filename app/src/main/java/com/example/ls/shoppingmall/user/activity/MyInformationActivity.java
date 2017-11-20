package com.example.ls.shoppingmall.user.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.user.utils.DialogUtils;
import com.example.ls.shoppingmall.user.utils.FileCache;
import com.example.ls.shoppingmall.user.utils.ImageBig;
import com.example.ls.shoppingmall.user.utils.PickPhoto;
import com.example.ls.shoppingmall.utils.layoututils.CircleImageView;
import com.example.ls.shoppingmall.utils.layoututils.OverScrollView;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyInformationActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.ivMineLogo)
    CircleImageView ivMineLogo;
    @Bind(R.id.myscroll_myslf_os)
    OverScrollView myscrollMyslfOs;
    private Dialog photoDialog;
    private String OUTPUTFILEPATH;
    private FileCache fileCache;
    public static final int UPDATE_PASSWORD = 2;

    //剩下的钱
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_information);
        ButterKnife.bind(this);
        fileCache = new FileCache(this);

        initView();
        initListener();
        setData();
    }

    private void setData() {

    }

    private void initListener() {
        ivMineLogo.setOnClickListener(this);
    }

    private void initView() {
        ivMineLogo = (CircleImageView) findViewById(R.id.ivMineLogo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivMineLogo:
                photoDialog();
                break;
            case R.id.tvPhotograph:// 拍照
                photoDialog.dismiss();
                OUTPUTFILEPATH = fileCache.getImageCacheDir().getAbsolutePath() + File.separator + System.currentTimeMillis() + ".jpg";
                PickPhoto.takePhoto(MyInformationActivity.this, OUTPUTFILEPATH);
                break;
            case R.id.tvAlbum:// 相册
                photoDialog.dismiss();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(intent, PickPhoto.PICK_PHOTO);
                break;
            default:
                break;
        }
    }

    // 修改头像
    public void photoDialog() {
        photoDialog = new Dialog(this, R.style.dialog);
        photoDialog.setCancelable(true);
        photoDialog.setCanceledOnTouchOutside(true);
        photoDialog.setContentView(R.layout.dialog_take_photo);
        TextView tvPhotograph = (TextView) photoDialog.findViewById(R.id.tvPhotograph);
        TextView tvAlbum = (TextView) photoDialog.findViewById(R.id.tvAlbum);
        tvPhotograph.setOnClickListener(this);
        tvAlbum.setOnClickListener(this);
        DialogUtils.dialogSet(photoDialog, this, Gravity.BOTTOM, 1, 1, true, false, true);
        photoDialog.show();
    }

    //这里设置头像
    private void setBitmap(String path) {
        Bitmap bitmap = ImageBig.getimage(path);
        ivMineLogo.setImageBitmap(bitmap);
        byte[] mContent = ImageBig.Bitmap2Bytes(bitmap);
        //这里去上传到服务端
        uploadHead(mContent);
    }

    //这里上传到后台图片就行了
    private void uploadHead(byte[] mContent) {
        String phto_content_str = Base64.encodeToString(mContent, Base64.DEFAULT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //这里到时候判断是否登录了。没有登录跳转到登录界面
       /* if (resultCode == Activity.RESULT_OK && requestCode == UPDATE_PASSWORD) {
            toActivity(LoginActivityNew.class);
            finish();
            return;
        }*/
        if (resultCode == Activity.RESULT_OK) {
            String path = null;
            switch (requestCode) {
                case PickPhoto.TAKE_PHOTO:
                    path = ImageBig.scalePicture(this, OUTPUTFILEPATH, 600, 600);
                    if (path != null) {
                        setBitmap(path);
                    }
                    break;
                case PickPhoto.PICK_PHOTO://去自定义的界面选择图片：
                    try {
                        Cursor cursor = this.getContentResolver().query(data.getData(), null, null, null, null);
                        cursor.moveToFirst();
                        path = ImageBig.scalePicture(this, cursor.getString(1), 600, 600);
                        Intent intent = new Intent(this, ChoosePhotoActivity.class);
                        intent.putExtra("imagePath", path);
                        startActivityForResult(intent, PickPhoto.CROP_PHOTO);
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        cursor.close();
                    } catch (Exception e) {
                        Toast.makeText(this, "请选择相册中的照片", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case PickPhoto.CROP_PHOTO://这个是自定义的activity里面获取之后的最后裁剪的东西：
                    if (data != null) {
                        String temppath = data.getStringExtra("path");
                        if (temppath != null) {
                            setBitmap(temppath);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
