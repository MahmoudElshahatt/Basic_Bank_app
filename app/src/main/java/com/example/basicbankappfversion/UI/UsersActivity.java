package com.example.basicbankappfversion.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.example.basicbankappfversion.Adapters.UsersListAdapter;
import com.example.basicbankappfversion.Data.User;
import com.example.basicbankappfversion.DataBase.UserContract;
import com.example.basicbankappfversion.DataBase.UserHelper;
import com.example.basicbankappfversion.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    UsersListAdapter adapter;
    List<User> userList;

    private UserHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        userList = new ArrayList<>();
        dbhelper = new UserHelper(this);

        ReadDatafromDataBase();

        recyclerView = findViewById(R.id.allUsersRV);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UsersListAdapter(userList);
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
}