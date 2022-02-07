package com.example.basicbankappfversion.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProviderClient;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basicbankappfversion.R;

public class UserDataActivity extends AppCompatActivity {
    TextView name, email, accountNo, balance, ifscCode, phoneNo;
    Button transferMoney;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email_id);
        accountNo = findViewById(R.id.account_no);
        balance = findViewById(R.id.avail_balance);
        ifscCode = findViewById(R.id.ifsc_id);
        phoneNo = findViewById(R.id.phone_no);
        transferMoney = findViewById(R.id.transfer_money);

        Intent intent = getIntent();

        name.setText(intent.getStringExtra("NAME"));
        accountNo.setText(String.valueOf(intent.getIntExtra(("ACCOUNT_NO"), 0)));
        email.setText(intent.getStringExtra("EMAIL"));
        phoneNo.setText(intent.getStringExtra("PHONE_NO"));
        ifscCode.setText(intent.getStringExtra("IFSC_CODE"));
        balance.setText(String.valueOf(intent.getIntExtra(("BALANCE"), 0)));

        transferMoney = findViewById(R.id.transfer_money);
        transferMoney.setOnClickListener(v -> {
            detectingAmount();
        });
    }

    private void detectingAmount() {
        final AlertDialog.Builder mbuilder = new AlertDialog.Builder(UserDataActivity.this);
        View mview = getLayoutInflater().inflate(R.layout.dialog_box, null);
        mbuilder.setTitle("Enter Amount").setView(mview).setCancelable(false);

        final EditText amount = mview.findViewById(R.id.amount_money);
        mbuilder.setPositiveButton("SEND", (dialog, which) -> {
        })
                .setNegativeButton("CANCEL", (dialog, which) -> {
                    dialog.dismiss();
                });

        dialog = mbuilder.create();
        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(v -> {
            int currentBalance = Integer.parseInt(balance.getText().toString());

            if (amount.getText().toString().equals("0")) {
                amount.setError("0 Value cannot be transferred ");
            } else if (amount.getText().toString().isEmpty()) {
                amount.setError("Amount can't be empty");
            } else if (Integer.parseInt(amount.getText().toString()) > currentBalance) {
                amount.setError("Your account don't have enough balance");
            } else {
                Intent intent = new Intent(UserDataActivity.this, SendToUserActivity.class);
                intent.putExtra("FROM_USER_ACCOUNT_NO", Integer.parseInt(accountNo.getText().toString()));    // PRIMARY_KEY
                intent.putExtra("FROM_USER_NAME", name.getText());
                intent.putExtra("FROM_USER_ACCOUNT_BALANCE", balance.getText());
                intent.putExtra("TRANSFER_AMOUNT", amount.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }
}