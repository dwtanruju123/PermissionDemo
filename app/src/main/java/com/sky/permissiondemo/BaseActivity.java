package com.sky.permissiondemo;

import android.app.Activity;
import android.os.Bundle;

import com.sky.permission.util.PermissionUtil;

public class BaseActivity extends Activity {
    protected PermissionUtil.PermissionCallBack permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtil.onRequestPermissionsResult(this,permission, requestCode, grantResults);
    }
}
