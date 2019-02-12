package com.app.sms.thatsmsapp.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

public class SmsListItem extends BaseObservable {
    public ObservableBoolean fromNotification = new ObservableBoolean();
    public ObservableField<String> smsBody = new ObservableField<>();
    public ObservableField<String> smsSenderNumber = new ObservableField<>();
    public ObservableField<String> smsDate = new ObservableField<>();
}
