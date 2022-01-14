package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Setup extends AppCompatActivity {
    private EditText OPlayer;
    private EditText XPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.setup);

        XPlayer = findViewById(R.id.player1Name);
        OPlayer = findViewById(R.id.player2Name);
    }
//the function deal with the "play" button on the second screen

    public void playButtonClick(View view) {
        String player1Name =  XPlayer.getText().toString();
        //if no name was entered
        if(player1Name.isEmpty())
        {
            player1Name= "X Player";
        }
        String player2Name =  OPlayer.getText().toString();
        //if no name was entered

        if(player2Name.isEmpty())
        {
            player2Name= "0 Player";
        }
        Intent intent = new Intent(this, Display.class);
        intent.putExtra("PLAYER_NAMES", new String[] {player1Name, player2Name});
        startActivity(intent);
    }
}