package com.example.himanshu.trial1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PostSignUp extends AppCompatActivity {

    TextView displaydb;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_sign_up);
        displaydb = (TextView)findViewById(R.id.displaydb);
        dbHelper = new DBHelper(this);
        printDatabse();
    }

    public void printDatabse()
    {
        String dbString=dbHelper.getData();
        displaydb.setText(dbString);
    }

}
