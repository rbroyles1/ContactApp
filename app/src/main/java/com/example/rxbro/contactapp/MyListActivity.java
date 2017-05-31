package com.example.rxbro.contactapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MyListActivity extends AppCompatActivity {
    TextView fname;
    TextView lname;
    TextView path;
    DataManager dm;
    private static final String TAG = "MyListActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        dm = new DataManager(this);
        fname = (TextView)findViewById(R.id.Fname);
        lname = (TextView)findViewById(R.id.Lname);
        path = (TextView)findViewById(R.id.imgPath);
        Populate();

    }
    protected void Populate() {
        Log.d(TAG, "Populating...");
        Cursor data = dm.selectAll();
        while (data.moveToNext()) {
            fname.append("\n" + data.getString(1));
            lname.append("\n" + data.getString(2));
            path.append("\n" + data.getString(3));
        }

    }

}
