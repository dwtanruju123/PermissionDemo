package com.sky.permissiondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.sky.permission.util.PermissionUtil;

public class MainActivity extends BaseActivity {
    public class PermissionCamera implements PermissionUtil.PermissionCallBack {

        @Override
        public int onRequestCode() {
            return PermissionUtil.requestCode;
        }

        @Override
        public String[] onPermissions() {
            return new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };
        }

        @Override
        public String onError() {
            return "请开启相机权限";
        }

        @Override
        public void onSuccess() {
            Toast.makeText(MainActivity.this, "打开相机", Toast.LENGTH_SHORT).show();
        }
    }

    public class PermissionFile implements PermissionUtil.PermissionCallBack {

        @Override
        public int onRequestCode() {
            return 0;
        }

        @Override
        public String[] onPermissions() {
            return new String[]{
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS
            };
        }

        @Override
        public String onError() {
            return "请开启写入权限";
        }

        @Override
        public void onSuccess() {
            Toast.makeText(MainActivity.this, "写入文件", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.bt_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permission = new PermissionCamera();
                PermissionUtil.checkPermissionAllGranted(MainActivity.this, permission);
            }
        });
        findViewById(R.id.bt_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permission = new PermissionFile();
                PermissionUtil.checkPermissionAllGranted(MainActivity.this, permission);
            }
        });
    }
}
