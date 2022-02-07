package com.example.basicbankappfversion.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicbankappfversion.Data.Transfer;
import com.example.basicbankappfversion.R;

import java.util.List;

public class TransfersAdapter extends RecyclerView.Adapter<TransfersAdapter.ViewHolder> {
    List<Transfer> transfers;

    public TransfersAdapter(List<Transfer> transfers) {
        this.transfers = transfers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transfer_item, parent, false);
        return new TransfersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(transfers.get(position));
    }

    @Override
    public int getItemCount() {
        return transfers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fromName, toName, Amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fromName = itemView.findViewById(R.id.FromName);
            toName = itemView.findViewById(R.id.ToName);
            Amount = itemView.findViewById(R.id.amount_of_transfer);
        }

        public void onBind(Transfer transfer) {
            fromName.setText(transfer.getFromUser());
            toName.setText(transfer.getToUser());
            Amount.setText(String.valueOf(transfer.getAmountTransferred()));
        }
    }
}
