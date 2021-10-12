package com.example.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.gridlayout.widget.GridLayout;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    boolean gameActive = true;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        Log.i("Tag", counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[1]] != 2) {
                    String ganador = "";
                    if (activePlayer == 1) {
                        ganador = "Amarillo";
                    } else {
                        ganador = "Rojo";
                    }
                    //Toast.makeText(this, "Has Ganado " + ganador + "!!!!", Toast.LENGTH_LONG).show();

                    gameActive = false;

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);


                    winnerTextView.setText(ganador + " ha ganado!!!");

                    winnerTextView.setVisibility(view.VISIBLE);
                    playAgainButton.setVisibility(view.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view){
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        winnerTextView.setVisibility(view.INVISIBLE);
        playAgainButton.setVisibility(view.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridlayout);

        for (int i=0; i < gridLayout.getChildCount();i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        gameActive = true;

        Arrays.fill(gameState, 2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}