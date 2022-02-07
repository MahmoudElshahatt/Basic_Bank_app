package com.example.basicbankappfversion.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.basicbankappfversion.Adapters.TransfersAdapter;
import com.example.basicbankappfversion.Adapters.UsersListAdapter;
import com.example.basicbankappfversion.Data.Transfer;
import com.example.basicbankappfversion.Data.User;
import com.example.basicbankappfversion.DataBase.TransferContract;
import com.example.basicbankappfversion.DataBase.TransferHelper;
import com.example.basicbankappfversion.DataBase.UserContract;
import com.example.basicbankappfversion.R;

import java.util.ArrayList;
import java.util.List;

public class TransfersActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TransfersAdapter adapter;
    List<Transfer> transfers;

    private TransferHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfers);

        transfers = new ArrayList<>();
        dbhelper = new TransferHelper(this);

        ReadfromDataBase();

        recyclerView = findViewById(R.id.transfersRV);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TransfersAdapter(transfers);
        recyclerView.setAdapter(adapter);

    }

    private void ReadfromDataBase() {

        SQLiteDatabase db = dbhelper.getReadableDatabase();


        String[] projection = {
                TransferContract.TransferEntry.FROM_NAME_COL,
                TransferContract.TransferEntry.TO_NAME_COL,
                TransferContract.TransferEntry.AMOUNT_COL,
                TransferContract.TransferEntry.STATUS_COL
        };


        Cursor cursor = db.query(
                TransferContract.TransferEntry.TABLE_NAME,   // The table to query
                projection,                          // The columns to return
                null,
                null,
                null,
                null,
                null);

        try {
            // Figure out the index of each column
            int fromNameColumnIndex = cursor.getColumnIndex(TransferContract.TransferEntry.FROM_NAME_COL);
            int ToNameColumnIndex = cursor.getColumnIndex(TransferContract.TransferEntry.TO_NAME_COL);
            int amountColumnIndex = cursor.getColumnIndex(TransferContract.TransferEntry.AMOUNT_COL);
            int statusColumnIndex = cursor.getColumnIndex(TransferContract.TransferEntry.STATUS_COL);

            while (cursor.moveToNext()) {

                String fromName = cursor.getString(fromNameColumnIndex);
                String ToName = cursor.getString(ToNameColumnIndex);
                int accountBalance = cursor.getInt(amountColumnIndex);
                int status = cursor.getInt(statusColumnIndex);

                transfers.add(new Transfer(fromName, ToName, accountBalance, status));
            }
        } finally {
            cursor.close();
        }


    }
}
