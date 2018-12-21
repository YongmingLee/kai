package com.example.yongming.activity.UITest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.widget.ImageView;

import com.example.yongming.activity.BaseActivity;
import com.example.yongming.activity.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AlbumTestActivity extends BaseActivity {

    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;

    private ImageView imageView;

    private Uri imageUri;
    private String mCurrentPhotoPath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_test);

        imageView = findViewById(R.id.image_test_image);

        Intent intent = getIntent();
        int actionType = intent.getIntExtra("actionType", 0);

        switch (actionType) {
            case 0: // camera

                File outputImage = new File(getExternalCacheDir(), "tempImage.jpg");

                try {
                    if (outputImage.exists())
                    {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //如果android7.0以上的系统，需要做个判断

                if (Build.VERSION.SDK_INT >= 24) {
                    imageUri = FileProvider.getUriForFile(AlbumTestActivity.this, "com.example.yongming.provider", outputImage);//7.0
                } else {
                    imageUri = Uri.fromFile(outputImage); //7.0以下
                }


                try
                {
                    //利用隐式Intent 打开系统相机
                    Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(cameraIntent, TAKE_PHOTO);//这里的TAKE_PHOTO是定义的一个静态常数变量
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case 1: // album
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
//            case TAKE_PHOTO:
//
//                Intent cropIntent = new Intent("com.android.camera.action.CROP");
//                cropIntent.setDataAndType(imageUri, "image/*");
//                cropIntent.putExtra("scale", true);
//                cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                startActivityForResult(cropIntent, CROP_PHOTO);
//
//                break;
            case TAKE_PHOTO:

                if (resultCode == RESULT_OK) {
                    imageView.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath));
                }

                break;
        }
    }
}
