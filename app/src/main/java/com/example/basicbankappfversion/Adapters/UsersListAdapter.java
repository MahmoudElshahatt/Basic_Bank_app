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

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.MyViewHolder> {

    private List<User> userList;

    public UsersListAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UsersListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersListAdapter.MyViewHolder holder, int position) {
        holder.BindUsers(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView userName, userEmail, userBalance;

        public MyViewHolder(@NonNull View itemView) {
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
                Intent intent = new Intent(v.getContext(), UserDataActivity.class);
                intent.putExtra("ACCOUNT_NO", user.getAccountNumber());
                intent.putExtra("NAME", user.getName());
                intent.putExtra("EMAIL", user.getE_mail());
                intent.putExtra("IFSC_CODE", user.getIfscCode());
                intent.putExtra("PHONE_NO", user.getPhoneNumber());
                intent.putExtra("BALANCE", user.getBalance());
                v.getContext().startActivity(intent);
            });
        }
    }
}
