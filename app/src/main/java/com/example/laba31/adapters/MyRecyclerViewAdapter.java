package com.example.laba31.adapters;

import static com.example.laba31.utils.Utils.moveToServiceDescriptionActivity;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laba31.databinding.RecyclerViewItemBinding;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private RecyclerViewItemBinding binding;

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private RecyclerViewItemBinding itemBinding;
        public ItemViewHolder(RecyclerViewItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        @SuppressLint("SetTextI18n")
        public void setItem(ActivityManager.RunningServiceInfo item) {
            itemBinding.serviceName.setText(item.service.getClassName());
            itemBinding.serviceInfo.setText(item.process);
            itemBinding.getRoot().setOnClickListener(view -> {
                moveToServiceDescriptionActivity(itemBinding.getRoot().getContext(), item);
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder) holder).setItem(((List<ActivityManager.RunningServiceInfo>) differ.getCurrentList()).get(position));
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    private DiffUtil.ItemCallback<ActivityManager.RunningServiceInfo> differCallback = new DiffUtil.ItemCallback<ActivityManager.RunningServiceInfo>() {
        @Override
        public boolean areItemsTheSame(@NonNull ActivityManager.RunningServiceInfo oldItem, @NonNull ActivityManager.RunningServiceInfo newItem) {
            return oldItem.service.getClassName().equals(newItem.service.getClassName()) && oldItem.process.equals(newItem.process);
        }

        @Override
        public boolean areContentsTheSame(@NonNull ActivityManager.RunningServiceInfo oldItem, @NonNull ActivityManager.RunningServiceInfo newItem) {
            return oldItem.activeSince == newItem.activeSince && oldItem.lastActivityTime == newItem.lastActivityTime &&
                    oldItem.crashCount == newItem.crashCount && oldItem.clientCount == newItem.clientCount && oldItem.clientLabel == newItem.clientLabel;
        }
    };
    public AsyncListDiffer<ActivityManager.RunningServiceInfo> differ = new AsyncListDiffer<>(this, differCallback);
}