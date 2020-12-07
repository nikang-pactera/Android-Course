package com.dream.mvpdemo.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.dream.mvpdemo.R;
import com.dream.mvpdemo.base.BaseActivity;
import com.dream.mvpdemo.contract.CameraContract;
import com.dream.mvpdemo.presenter.CameraPresenter;

public class CameraActivity extends BaseActivity<CameraPresenter> implements CameraContract.View {
    private ImageView ivPhoto;

    // 拍照的requestCode
    private static final int CAMERA_REQUEST_CODE = 0x00000010;
    // 申请相机权限的requestCode
    private static final int PERMISSION_CAMERA_REQUEST_CODE = 0x00000012;

    @Override
    protected void initPresenter() {
        mPresenter = new CameraPresenter();
    }

    @Override
    protected void initView() {
        ImageView ivCamera = findViewById(R.id.ivCamera);
        ivPhoto = findViewById(R.id.ivPhoto);

        ivCamera.setOnClickListener(view -> checkPermissionAndCamera());

        mPresenter.getMpresenter();
        mPresenter.requestNetwork();
        mPresenter.preference();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_camera;
    }

    @Override
    public void getView() {
    }

    /**
     * 检查权限并拍照。
     * 调用相机前先检查权限。
     */
    private void checkPermissionAndCamera() {
        int hasCameraPermission = ContextCompat.checkSelfPermission(getApplication(),
                Manifest.permission.CAMERA);
        if (hasCameraPermission == PackageManager.PERMISSION_GRANTED) {
            //有权限，调起相机拍照。
            openCamera();
        } else {
            //没有权限，申请权限。
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    PERMISSION_CAMERA_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                Bitmap bitmap = data.getParcelableExtra("data");
                ivPhoto.setImageBitmap(bitmap);
            } else {
                Toast.makeText(this, "取消", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * 处理权限申请的回调。
     *
     * @param requestCode  requestCode
     * @param permissions  permissions
     * @param grantResults grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //允许权限，有调起相机拍照。
                openCamera();
            } else {
                //拒绝权限，弹出提示框。
                Toast.makeText(this, "拍照权限被拒绝", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * 调起相机拍照
     */
    private void openCamera() {
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断是否有相机
        if (captureIntent.resolveActivity(getPackageManager()) != null) {
            captureIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.addCategory(Intent.CATEGORY_DEFAULT);
            captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            startActivityForResult(captureIntent, CAMERA_REQUEST_CODE);
        }
    }
}