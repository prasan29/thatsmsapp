package com.app.sms.thatsmsapp.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.app.sms.thatsmsapp.R;
import com.app.sms.thatsmsapp.viewmodel.HomeSMSViewModel;

public class HomeActivity extends AppCompatActivity {

    HomeSMSViewModel mHomeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mHomeViewModel = new HomeSMSViewModel(this);
        mHomeViewModel.checkSmsPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mHomeViewModel.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
