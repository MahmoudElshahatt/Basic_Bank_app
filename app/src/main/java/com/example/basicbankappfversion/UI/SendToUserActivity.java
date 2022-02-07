package com.example.basicbankappfversion.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.basicbankappfversion.Adapters.SendToUserAdapter;
import com.example.basicbankappfversion.Adapters.UsersListAdapter;
import com.example.basicbankappfversion.Data.User;
import com.example.basicbankappfversion.DataBase.TransferHelper;
import com.example.basicbankappfversion.DataBase.UserContract;
import com.example.basicbankappfversion.DataBase.UserHelper;
import com.example.basicbankappfversion.R;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SendToUserActivity extends AppCompatActivity implements SendToUserAdapter.OnUserListener {
    RecyclerView recyclerView;
    SendToUserAdapter adapter;
    List<User> userList;
    int fromUserAccountNo, toUserAccountNo, toUserAccountBalance;
    String fromUserAccountName, fromUserAccountBalance, transferAmount, toUserAccountName;

    private UserHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_to_user);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fromUserAccountName = bundle.getString("FROM_USER_NAME");
            fromUserAccountNo = bundle.getInt("FROM_USER_ACCOUNT_NO");
            fromUserAccountBalance = bundle.getString("FROM_USER_ACCOUNT_BALANCE");
            transferAmount = bundle.getString("TRANSFER_AMOUNT");
        }
        userList = new ArrayList<>();
        dbHelper = new UserHelper(this);

        ReadDatafromDataBase();

        recyclerView = findViewById(R.id.send_to_user_list);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SendToUserAdapter(userList, this);
        recyclerView.setAdapter(adapter);
    }

    private void ReadDatafromDataBase() {
        userList.clear();

        Cursor cursor = new UserHelper(this).readAllData();

        int accountNumberIndex = cursor.getColumnIndex(UserContract.UserEntry.USER_ACCOUNT_NUMBER_COL);
        int nameIndex = cursor.getColumnIndex(UserContract.UserEntry.USER_NAME_COL);
        int phoneIndex = cursor.getColumnIndex(UserContract.UserEntry.USER_PHONE_NO_COL);
        int emailIndex = cursor.getColumnIndex(UserContract.UserEntry.USER_EMAIL_COL);
        int ifSecIndex = cursor.getColumnIndex(UserContract.UserEntry.USER_IFSC_CODE_COL);
        int accountBalanceIndex = cursor.getColumnIndex(UserContract.UserEntry.USER_ACCOUNT_BALANCE_COL);

        while (cursor.moveToNext()) {
            String currentName = cursor.getString(nameIndex);
            int accountNumber = cursor.getInt(accountNumberIndex);
            int accountBalance = cursor.getInt(accountBalanceIndex);
            String currentEmail = cursor.getString(emailIndex);
            String currentPhoneNo = cursor.getString(phoneIndex);
            String currentIfSec = cursor.getString(ifSecIndex);

            userList.add(new User(currentName, accountNumber, currentPhoneNo, currentIfSec, accountBalance, currentEmail));
        }
    }

    private void calculateAmount() {
        Integer currentAmount = Integer.parseInt(fromUserAccountBalance);
        Integer transferAmountInt = Integer.parseInt(transferAmount);
        Integer remainingAmount = currentAmount - transferAmountInt;
        Integer increasedAmount = transferAmountInt + toUserAccountBalance;

        new UserHelper(this).updateAmount(fromUserAccountNo, remainingAmount);
        new UserHelper(this).updateAmount(toUserAccountNo, increasedAmount);
    }

    @Override
    public void onUserClick(int position) {
        toUserAccountNo = userList.get(position).getAccountNumber();
        toUserAccountName = userList.get(position).getName();
        toUserAccountBalance = userList.get(position).getBalance();

        calculateAmount();

        new TransferHelper(this).insetTransfer(fromUserAccountName, toUserAccountName, Integer.parseInt(transferAmount), 1);
        Toast.makeText(this, "Transfer is done Successfully!!", Toast.LENGTH_LONG).show();

        startActivity(new Intent(SendToUserActivity.this, UsersActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Transfer is Cancelled!!", Toast.LENGTH_LONG).show();
    }
}