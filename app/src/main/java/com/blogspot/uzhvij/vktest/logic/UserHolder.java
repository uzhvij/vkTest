package com.blogspot.uzhvij.vktest.logic;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.uzhvij.vktest.R;
import com.blogspot.uzhvij.vktest.models.VkUser;
import com.squareup.picasso.Picasso;

class UserHolder extends RecyclerView.ViewHolder{
    private final ImageView avatarIV;
    private final TextView nameTV;

    public UserHolder(@NonNull View itemView) {
        super(itemView);
        avatarIV  = itemView.findViewById(R.id.avatarIV);
        nameTV = itemView.findViewById(R.id.nameTV);
    }

    public void bind(VkUser user) {
        nameTV.setText(String.format("%s %s", user.firstName, user.lastName));
        if (!TextUtils.isEmpty(user.photo)) {
            Picasso.get().load(user.photo).error(R.drawable.user_placeholder).into(avatarIV);
        } else {
            avatarIV.setImageResource(R.drawable.user_placeholder);
        }
    }
}