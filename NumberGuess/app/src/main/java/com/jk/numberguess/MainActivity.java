package com.jk.numberguess;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int randomNumber;
    private EditText editText;
    private TextView resultTextView;
    private TextView score_txt;
    private TextView high_score_txt;
    private int score = 0;
    private int HighScore = 0;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        resultTextView = findViewById(R.id.ResulttextView);
        score_txt = findViewById(R.id.score);
        high_score_txt = findViewById(R.id.high_score);

        // Load high score from SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        HighScore = sharedPreferences.getInt("highScore", 0);
        high_score_txt.setText("Highscore - " + HighScore);

        randomNumber();

        Button submitButton = findViewById(R.id.button);
        submitButton.setOnClickListener(v -> checkGuess());
    }

    @Override
    protected void onDestroy() {
        // Save high score to SharedPreferences when app ends
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("highScore", HighScore);
        editor.apply();
        super.onDestroy();
    }

    private void randomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        Log.d(String.valueOf(randomNumber), "randomNumber: ");
    }

    private void checkGuess() {
        String guessText = editText.getText().toString();

        if (!guessText.isEmpty()) {
            int userGuess = Integer.parseInt(guessText);

            if (userGuess == randomNumber) {
                resultTextView.setText("Congratulations! You guessed it correctly. Try again");
                randomNumber();
                score++;
                score_txt.setText("Score - " + score);

                if (score > HighScore) {
                    HighScore = score;
                    high_score_txt.setText("Highscore - " + HighScore);
                }

            } else if (userGuess < randomNumber) {
                resultTextView.setText("Try a higher number");
            } else if (userGuess > 100) {
                Toast.makeText(MainActivity.this, "Enter number between 1 to 100", Toast.LENGTH_SHORT).show();
                resultTextView.setText("Enter number between 1 to 100");
            } else {
                resultTextView.setText("Try a lower number");
            }
        } else {
            resultTextView.setText("Please enter a valid number");
        }
    }
}
