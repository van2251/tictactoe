package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView playerOneScore, playerTwoScore, playerStatus;
    private TextView playerOne,playerTwo;
    private Button[] buttons = new Button[9];
    private Button resetGame;
    private String play1,play2;
    private int playerOneScoreCount, playerTwoScoreCount, rountCount;
    boolean activePlayer;

    //p1=>0
    //p2=>1
    //empty=>2

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, //rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, //columns
            {0, 4, 8}, {2, 4, 6} //cross
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);
        playerStatus = (TextView) findViewById(R.id.playerStatus);
        playerOne = (TextView) findViewById(R.id.playerOne);
        playerTwo = (TextView) findViewById(R.id.playerTwo);
        resetGame = (Button) findViewById(R.id.resetGame);

        for (int i = 0; i < buttons.length; i++) {
            String buttonID = "btn_" + i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = (Button) findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }

        rountCount = 0;
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        activePlayer = true;
        Bundle b= getIntent().getExtras();
        play1 = b.getString("play1","Player 1");
        play2 = b.getString("play2","Player 2");
        playerOne.setText(play1);
        playerOne.invalidate();
        playerTwo.setText(play2);
        playerTwo.invalidate();
    }

    @Override
    public void onClick(View v) {
        // Log.i("test","button is clicked");

        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        String buttonID = v.getResources().getResourceEntryName(v.getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length() - 1, buttonID.length()));

        if (activePlayer) {
            ((Button) v).setText("X");
            ((Button) v).setTextColor(Color.parseColor("#000000"));
            gameState[gameStatePointer] = 0;
        } else {
            ((Button) v).setText("O");
            ((Button) v).setTextColor(Color.parseColor("#FFFFFF"));
            gameState[gameStatePointer] = 1;
        }
        rountCount++;

        if(checkWInner())
        {
            if(activePlayer){
                playerOneScoreCount++;
                updatePlayerScore();
                Toast.makeText(this, playerOne.getText().toString()+" has won", Toast.LENGTH_LONG).show();
                playAgain();
            }
            else
            {
                playerTwoScoreCount++;
                updatePlayerScore();
                Toast.makeText(this, playerTwo.getText().toString()+" has won", Toast.LENGTH_LONG).show();
                playAgain();

            }

        }
        else if(rountCount == 9)
        {
            playAgain();
            Toast.makeText(this, "No Winner!", Toast.LENGTH_LONG).show();

        }
        else
        {
            activePlayer = !activePlayer;
        }
        if(playerOneScoreCount > playerTwoScoreCount){
            playerStatus.setText(playerOne.getText().toString()+" is Winning!");
        }
        else if(playerTwoScoreCount > playerOneScoreCount){
            playerStatus.setText(playerTwo.getText().toString()+" is Winning!");
        }
        else{
            playerStatus.setText("");
        }
        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                playerStatus.setText("");
                updatePlayerScore();
            }
        });
    }


    public boolean checkWInner(){
        boolean winnerResult = false;

        for(int[] winninngPosition : winningPositions)
        {
            if(gameState[winninngPosition[0]] == gameState[winninngPosition[1]]
                    && gameState[winninngPosition[1]] == gameState[winninngPosition[2]]
                    && gameState[winninngPosition[0]] != 2)
            {
                winnerResult = true;
            }
        }
        return  winnerResult;
    }

    public void updatePlayerScore()
    {
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }

    public void playAgain()
    {
        rountCount = 0;
        activePlayer = true;

        for(int i = 0; i< buttons.length; i++ ){
            gameState[i] = 2;
            buttons[i].setText("");
        }
    }
}