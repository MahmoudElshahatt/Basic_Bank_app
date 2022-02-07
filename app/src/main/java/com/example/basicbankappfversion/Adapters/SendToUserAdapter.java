package com.example.basicbankappfversion.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicbankappfversion.Data.User;
import com.example.basicbankappfversion.R;
import com.example.basicbankappfversion.UI.UserDataActivity;

import java.util.List;

public class SendToUserAdapter extends RecyclerView.Adapter<SendToUserAdapter.ViewHolder> {
    private List<User> userList;
    private OnUserListener onUserListener;

    public SendToUserAdapter(List<User> userList, OnUserListener onUserListener) {
        this.userList = userList;
        this.onUserListener = onUserListener;
    }

    @NonNull
    @Override
    public SendToUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        return new SendToUserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SendToUserAdapter.ViewHolder holder, int position) {
        holder.BindUsers(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName, userEmail, userBalance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            userEmail = itemView.findViewById(R.id.userEmail);
            userBalance = itemView.findViewById(R.id.userBalance);
        }

        public void BindUsers(User user) {
            userName.setText(user.getName());
            userEmail.setText(user.getE_mail());
            userBalance.setText(String.format("%d", user.getBalance()));
            itemView.setOnClickListener(v -> {
                onUserListener.onUserClick(getAdapterPosition());
            });
        }
    }

    public interface OnUserListener {
        void onUserClick(int position);
    }
}
