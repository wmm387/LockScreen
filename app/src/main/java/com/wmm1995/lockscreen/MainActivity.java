package com.wmm1995.lockscreen;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

    private DevicePolicyManager dpm;
    private ComponentName mAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dpm = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        mAdmin = new ComponentName(this,MyAdmin.class);
        if (dpm.isAdminActive(mAdmin)) {
            dpm.lockNow();// 锁屏
            finish();
        } else {
            // 激活设备管理器获取权限
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mAdmin);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "开启我可以一键锁屏哦");
            startActivity(intent);
        }
    }
}