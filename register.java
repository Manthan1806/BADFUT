package com.example.himanshu.trial1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class register extends AppCompatActivity {

    Button signup;
    EditText email,mobno,pwd,cpwd;
    DBHelper dbHelper ;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        System.out.println("in oncrete1");
        email = (EditText)findViewById(R.id.email);
        mobno = (EditText)findViewById(R.id.mobno);
        pwd = (EditText)findViewById(R.id.pwd);
        cpwd = (EditText)findViewById(R.id.cpwd);
        dbHelper = new DBHelper(this);
        signupClicked();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(register.this);
        builder.setTitle("Really Exit?")
                .setMessage("Are you sure?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        register.super.onBackPressed();
                    }
                })
                .setNegativeButton("Cancel",null);

        AlertDialog alert  = builder.create();
        alert.show();

    }

    public void signupClicked()
    {
        signup = (Button)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pwd.getText().toString().compareTo(cpwd.getText().toString())!=0)
                {
                    AlertDialog.Builder builder= new AlertDialog.Builder(register.this);
                    builder.setMessage("Password and Confirm Password fields are different!!");
                    AlertDialog alert  = builder.create();
                    alert.show();
                }
                else {
                    dbHelper.insertEntry(email.getText().toString(), mobno.getText().toString(), pwd.getText().toString());
                    Intent intent = new Intent(register.this, PostSignUp.class);
                    startActivity(intent);
                }
                }
        });
    }



}
