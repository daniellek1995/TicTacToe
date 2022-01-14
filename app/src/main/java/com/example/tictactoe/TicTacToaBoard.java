package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TicTacToaBoard extends View {

    private final int colorBoard;

    private final int playerXColor;

    private final int player0Color;

    private final int winningLineColor;

    private boolean winningLine = false;

    private final Paint paint = new Paint();

    private final GameLogic game;

    private int cellSize= getWidth()/3;

//constructor for the board
    public TicTacToaBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        game= new GameLogic();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.TicTacToaBoard,0,0);
//from attrs .xml file and gameboard.xml
        try{
            colorBoard = a.getInteger(R.styleable.TicTacToaBoard_boardColor,0);
            playerXColor = a.getInteger(R.styleable.TicTacToaBoard_playerXColor,0);
            player0Color = a.getInteger(R.styleable.TicTacToaBoard_player0Color,0);
            winningLineColor = a.getInteger(R.styleable.TicTacToaBoard_winningLineColor,0);
        }finally {
            a.recycle();
        }
    }
//set the dimension of the game
    @Override
    protected void onMeasure(int boardHeight , int boardWidth){

        super.onMeasure(boardWidth, boardHeight);

        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        //3 on 3
        cellSize = dimension/3;
        setMeasuredDimension(dimension, dimension);
    }
//canvas - the board
    @Override
    protected void onDraw(Canvas canvas) {
        //draws the lines
       paint.setStyle(Paint.Style.STROKE);
       paint.setAntiAlias(true);
//create the board
       drawBoard(canvas);

       drawMarkers(canvas);

       if(winningLine){
           paint.setColor(winningLineColor);
           drawWinningLine(canvas);
       }

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();

        if(action == MotionEvent.ACTION_DOWN) {
            int row = (int) Math.ceil(y/cellSize);
            int col = (int) Math.ceil(x/cellSize);

            if(!winningLine){
                if (game.boardUpdate(row, col)){
                    invalidate();

                    if(game.checkTheWinner()){
                        winningLine = true;
                        invalidate();
                    }

                    if (game.getPlayer() % 2 == 0){
                        game.setPlayer(game.getPlayer()-1);
                    }
                    else{
                        game.setPlayer(game.getPlayer()+1);
                    }
                }
            }

            invalidate();

            return true;
        }

        return false;
    }

    private void drawBoard(Canvas canvas){
        paint.setColor(colorBoard);
        paint.setStrokeWidth(16);
        for (int c=1; c<3; c++) {
            canvas.drawLine(cellSize*c, 0 ,cellSize*c, canvas.getWidth(), paint);
        }

        for (int r=1; r<3; r++) {
            canvas.drawLine(0, cellSize*r, canvas.getWidth(),cellSize*r,  paint);

        }
    }

    private void drawMarkers(Canvas canvas){
        for(int r=0; r<3; r++){
            for(int c=0; c<3; c++){
                if (game.getGameBoard()[r][c] != 0){
                    if (game.getGameBoard()[r][c] == 1){
                        drawX(canvas, r, c);
                    }
                    else{
                        drawO(canvas, r, c);
                    }
                }
            }
        }

    }

    private void drawX(Canvas canvas, int row, int col){
        paint.setColor(playerXColor);

        canvas.drawLine((float) ((col+1)*cellSize - cellSize * 0.2),
                (float) (row*cellSize + cellSize * 0.2),
                (float) (col*cellSize + cellSize * 0.2),
                (float) ((row+1)*cellSize - cellSize * 0.2),
                paint);

        canvas.drawLine((float) (col*cellSize + cellSize * 0.2),
                (float)(row*cellSize + cellSize * 0.2),
                (float) ((col+1)*cellSize - cellSize * 0.2),
                (float) ((row+1)*cellSize - cellSize * 0.2),
                paint);
    }

    private void drawO(Canvas canvas, int row, int col){
        paint.setColor(player0Color);

        canvas.drawOval((float) (col*cellSize + cellSize*0.2),
                (float) (row*cellSize + cellSize*0.2),
                (float) ((col*cellSize + cellSize) - cellSize*0.2),
                (float) ((row*cellSize + cellSize) - cellSize*0.2),
                paint);
    }

    private void drawHorizontalLine(Canvas canvas, int row, int col){
        canvas.drawLine(col,row*cellSize + (float)cellSize/2,
             cellSize*3, row*cellSize + (float)cellSize/2,
                paint);
    }

    private void drawVerticalLine(Canvas canvas, int row, int col){
        canvas.drawLine(col*cellSize + (float)cellSize/2, row,
                col*cellSize + (float)cellSize/2, cellSize*3,
                paint);
    }

    private void drawDiagonalLinePos(Canvas canvas){
        canvas.drawLine(0,cellSize*3, cellSize*3, 0,
                paint);
    }

    private void drawDiagonalLineNeg(Canvas canvas){
        canvas.drawLine(0, 0, cellSize*3, cellSize*3,
                paint);
    }

    private void drawWinningLine(Canvas canvas){
        int row = game.getWinType()[0];
        int col = game.getWinType()[1];

        switch (game.getWinType()[2]){
            case 1:
                drawHorizontalLine(canvas, row, col);
                break;
            case 2:
                drawVerticalLine(canvas, row, col);
                break;
            case 3:
                drawDiagonalLineNeg(canvas);
                break;
            case 4:
                drawDiagonalLinePos(canvas);
                break;
        }

    }

    public void setUpGame(Button playAgain, Button home, TextView playerDisplay, String[] names){
        game.setPlayAgainBTN(playAgain);
        game.setHomeBTN(home);
        game.setPlayerTurn(playerDisplay);
        game.setPlayersNames(names);
    }

    public void resetGame(){
        game.resetGame();
        winningLine = false;
    }

}
