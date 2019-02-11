package com.app.sms.thatsmsapp.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.sms.thatsmsapp.R;
import com.app.sms.thatsmsapp.databinding.SmsItemBinding;
import com.app.sms.thatsmsapp.viewmodel.SmsListItem;

import java.util.List;

public class SmsListAdapter extends RecyclerView.Adapter<SmsListAdapter.MyViewHolder> {

    public List<SmsListItem> mSmsList;
    private LayoutInflater layoutInflater;

    public SmsListAdapter(List<SmsListItem> smsList) {
        mSmsList = smsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        SmsItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.sms_item, viewGroup, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.binding.setItemViewModel(mSmsList.get(i));
    }

    @Override
    public int getItemCount() {
        return mSmsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final SmsItemBinding binding;

        public MyViewHolder(@NonNull SmsItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}
