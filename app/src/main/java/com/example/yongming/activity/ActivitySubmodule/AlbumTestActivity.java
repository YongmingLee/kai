package com.example.yongming.activity.ActivitySubmodule;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
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

                // 创建File对象，用于存储拍照后的图片
                File outputImage = new File(Environment.getExternalStorageDirectory(), "tempImage.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(outputImage);
                Intent captureIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(captureIntent, TAKE_PHOTO); // 启动相机程序

                break;
            case 1: // album

                // 创建File对象，用于存储选择的照片
                File outputAlbumImage = new File(Environment.getExternalStorageDirectory(), "out_image.jpg");
                try {
                    if (outputAlbumImage.exists()) {
                        outputAlbumImage.delete();
                    }
                    outputAlbumImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(outputAlbumImage);
                Intent albumIntent = new Intent("android.intent.action.GET_CONTENT");
                albumIntent.setType("image/*");
                albumIntent.putExtra("crop", true);
                albumIntent.putExtra("scale", true);
                albumIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(albumIntent, CROP_PHOTO);

                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CROP_PHOTO); // 启动裁剪程序
                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        imageView.setImageBitmap(bitmap); // 将裁剪后的照片显示出来
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
