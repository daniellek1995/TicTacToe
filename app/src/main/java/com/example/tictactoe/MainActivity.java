package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//when the play button is pressed this function takes us to the other page - the name page


    public void clickPlayButton(View view)
    {
        Intent intent = new Intent(this, Setup.class);
        startActivity(intent);
    }
}