package com.example.tictactoe;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameLogic {
    private int[][] gameBoard;

    private String[] playersNames = {"Player 1", "Player2"};
    private int[] winType = {-1, -1, -1};
    private Button playAgainBTN;
    private Button homeBTN;
    private TextView playerTurn;


    private int player = 1; //player one is always start first

    GameLogic(){
        gameBoard = new int[3][3];
        for(int r=0; r<3; r++){
            for(int c=0; c<3; c++){
                gameBoard[r][c] = 0;
            }
        }
    }
    //this function update the board

    public boolean boardUpdate(int row, int col){
        if(gameBoard[row-1][col-1] == 0) {
            gameBoard[row-1][col-1] = player;

            if(player == 1){
                playerTurn.setText((playersNames[1]+ "'s Turn"));
            }
            else{
                playerTurn.setText((playersNames[0]+ "'s Turn"));
            }

            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkTheWinner(){
        boolean isWinner = false;

        for (int r=0; r<3; r++){
            if (gameBoard[r][0] == gameBoard[r][1] && gameBoard[r][0] == gameBoard[r][2] && gameBoard[r][0] != 0){
                winType = new int[] {r, 0, 1};
                isWinner = true;
            }
        }

        for (int c=0; c<3; c++){
            if (gameBoard[0][c] == gameBoard[1][c] && gameBoard[0][c] == gameBoard[2][c] && gameBoard[0][c] != 0){
                winType = new int[] {0, c,  2};
                isWinner = true;
            }
        }

        if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2] && gameBoard[0][0] != 0){
            winType = new int[] {0, 2,  3};
            isWinner = true;
        }

        if (gameBoard[2][0] == gameBoard[1][1] && gameBoard[2][0] == gameBoard[0][2] && gameBoard[0][2] != 0){
            winType = new int[] {2, 2,  4};
            isWinner = true;
        }

        int boardFiled = 0;

        for (int r=0; r<3; r++){
            for (int c=0; c<3; c++){
                if(gameBoard[r][c] != 0){
                    ++boardFiled;
                }
            }
        }

        if(isWinner){
            playAgainBTN.setVisibility(View.VISIBLE);
            homeBTN.setVisibility(View.VISIBLE);
            playerTurn.setText((playersNames[player-1] + " Won!!!!"));
            return true;
        }
        else if(boardFiled == 9){
            playAgainBTN.setVisibility(View.VISIBLE);
            homeBTN.setVisibility(View.VISIBLE);
            playerTurn.setText(("Tie Game"));
            return false;
        }
        else{
            return false;
        }

    }

    public void resetGame(){
        for(int r=0; r<3; r++){
            for(int c=0; c<3; c++){
                gameBoard[r][c] = 0;
            }
        }

        player = 1;

        playAgainBTN.setVisibility(View.GONE);
        homeBTN.setVisibility(View.GONE);

        playerTurn.setText(playersNames[0] + "'s Turn");
    }

    public void setPlayersNames(String[] playersNames) {
        this.playersNames = playersNames;
    }

    public void setPlayAgainBTN(Button playAgainBTN) {
        this.playAgainBTN = playAgainBTN;
    }

    public void setHomeBTN(Button homeBTN) {
        this.homeBTN = homeBTN;
    }

    public void setPlayerTurn(TextView playerTurn) {
        this.playerTurn = playerTurn;
    }

    public int[][] getGameBoard(){
        return gameBoard;
    }

    public void setPlayer(int player){
        this.player = player;
    }

    public int getPlayer(){
        return player;
    }

    public int[] getWinType() {
        return winType;
    }
}
