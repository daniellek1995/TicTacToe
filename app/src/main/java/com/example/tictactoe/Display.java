package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Display extends AppCompatActivity {

    private TicTacToaBoard ticTacToaBoard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board);

        Button playAgainBTN = findViewById(R.id.play_again);
        Button homeBTN = findViewById(R.id.home_button);
        TextView playerTurn = findViewById(R.id.player_display);

        playAgainBTN.setVisibility(View.GONE);
        homeBTN.setVisibility(View.GONE);

        String[] playerNames = getIntent().getStringArrayExtra("PLAYER_NAMES");

        if(playerNames != null){
            playerTurn.setText((playerNames[0] + "'s Turn"));
        }
        ticTacToaBoard = findViewById(R.id.ticTacToaBoard);
        ticTacToaBoard.setUpGame(playAgainBTN, homeBTN, playerTurn, playerNames);

    }
    //if the user wants to go back to home screen
    public void clickHomeButton(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


//if play again button was clicked
    public void clickPlayAgainButton(View view){
        ticTacToaBoard.resetGame();
        ticTacToaBoard.invalidate();
    }

}