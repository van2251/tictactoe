package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {
    EditText p1,p2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        p1=(EditText)findViewById(R.id.play1);
        p2=(EditText) findViewById(R.id.play2);
    }
    public void normal(View v)
    {
        Intent i = new Intent(this,MainActivity.class);
        Bundle b =new Bundle();
        b.putString("play1",p1.getText().toString());
        Log.i("play1",p1.getText().toString());
        b.putString("play2",p2.getText().toString());
        i.putExtras(b);
        startActivity(i);
    }
    public void emoji(View v)
    {
        Intent i = new Intent(this,Emoji.class);
        Bundle b =new Bundle();
        b.putString("play1",p1.getText().toString());
        b.putString("play2",p2.getText().toString());
        i.putExtras(b);
        startActivity(i);
    }
}