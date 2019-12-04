package com.sky.permission.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


public class PermissionUtil {

    public static final int requestCode = 43096;

    /**
     * 检查是否拥有指定的所有权限
     */
    public static boolean checkPermissionAllGranted(Activity activity, PermissionCallBack callBack) {
        if (callBack == null) throw new RuntimeException("PermissionCallBack no can null");
        for (String permission : callBack.onPermissions()) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                // 只要有一个权限没有被授予, 则直接返回 false
                ActivityCompat.requestPermissions(activity, callBack.onPermissions(), callBack.onRequestCode());
                return false;
            }
        }
        callBack.onSuccess();
        return true;
    }

    /**
     * 检查是否拥有指定的所有权限
     */
    public static boolean checkPermissionAllGranted(Fragment fragment, PermissionCallBack callBack) {
        if (callBack == null) throw new RuntimeException("PermissionCallBack no can null");
        for (String permission : callBack.onPermissions()) {
            if (ContextCompat.checkSelfPermission(fragment.getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                // 只要有一个权限没有被授予, 则直接返回 false
                fragment.requestPermissions(callBack.onPermissions(), callBack.onRequestCode());
                return false;
            }
        }
        callBack.onSuccess();
        return true;
    }

    public static void onRequestPermissionsResult(Context context, PermissionCallBack callBack, int requestCode, @NonNull int[] grantResults) {
        if (callBack != null) {
            if (requestCode == callBack.onRequestCode()) {
                for (int grant : grantResults) {
                    if (grant != PackageManager.PERMISSION_GRANTED) {
                        onRequestPermissionsResult(context, callBack, false);
                        return;
                    }
                }
                onRequestPermissionsResult(context, callBack, true);
            }
        }
    }

    protected static void onRequestPermissionsResult(Context context, PermissionCallBack callBack, boolean b) {
        if (b)
            callBack.onSuccess();
        else
            Toast.makeText(context, TextUtils.isEmpty(callBack.onError()) ? "请开启相应的权限" : callBack.onError(), Toast.LENGTH_SHORT).show();
    }

    public interface PermissionCallBack {
        int onRequestCode();

        String[] onPermissions();

        String onError();

        void onSuccess();
    }
}
