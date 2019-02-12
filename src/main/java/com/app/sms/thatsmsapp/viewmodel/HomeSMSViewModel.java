package com.app.sms.thatsmsapp.viewmodel;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.BaseObservable;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.app.sms.thatsmsapp.R;
import com.app.sms.thatsmsapp.adapter.SmsListAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeSMSViewModel extends BaseObservable {
    public static final int MY_PERMISSIONS_REQUEST_READ_SMS = 100;
    private final String TAG = "SMS_DEBUG";
    public List<SmsListItem> smsList = new ArrayList<>();
    private Activity mContext;
    private boolean mFromNotification;

    public HomeSMSViewModel(Activity context) {
        mContext = context;
    }

    public void getMessages() {
        Uri smsUri = Uri.parse("content://sms/inbox");
        final Cursor cursor = mContext.getContentResolver().query(smsUri, new String[]{"_id", "address", "date", "body"}, null, null, null);
        while (cursor.moveToNext()) {
            String address = cursor.getString(1);
            String date = cursor.getString(2);
            String smsBody = cursor.getString(3);
            Log.e(TAG, "Number: " + address + " - Date: " + date + " - Body: " + smsBody);
            SmsListItem item = new SmsListItem();
            item.smsSenderNumber.set(address);
            item.smsDate.set(date);
            item.smsBody.set(smsBody);
            smsList.add(item);
        }
        setRecycler();
    }

    private void setRecycler() {
        if (smsList != null) {
            SmsListAdapter adapter = new SmsListAdapter(smsList, mFromNotification);
            RecyclerView recyclerView = mContext.findViewById(R.id.recyclerViewId);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }

    public void checkSmsPermission(boolean fromNotification) {
        mFromNotification = fromNotification;
        if (ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mContext,
                    new String[]{android.Manifest.permission.READ_SMS},
                    MY_PERMISSIONS_REQUEST_READ_SMS);
        } else {
            getMessages();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.
                        PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(mContext,
                            android.Manifest.permission.READ_SMS)
                            == PackageManager.PERMISSION_GRANTED) {
                        getMessages();
                    }
                } else {
                    Toast.makeText(mContext.getApplicationContext(),
                            "SMS Permission denied!",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
