package com.blogspot.uzhvij.vktest.logic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.uzhvij.vktest.R;
import com.blogspot.uzhvij.vktest.UserActivity;
import com.blogspot.uzhvij.vktest.models.VkUser;

import java.util.ArrayList;
import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<VkUser> friends = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((UserHolder)holder).bind(friends.get(position));
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public void setData(List<VkUser> friends) {
        this.friends.clear();
        this.friends.addAll(friends);
        notifyDataSetChanged();
    }
}