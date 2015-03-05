package com.myapplication.falkon.falkon;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Color;

import com.example.falkon.projectfalkon.R;

import static com.example.falkon.projectfalkon.R.layout.activity_my;

public class MyActivity extends Activity implements OnClickListener {

    Button myButton;
    TextView levelText;
    TextView highLevelText;
    TextView scoreValue;
    int counter = 1;
    int highLevel;

    Handler customHandler = new Handler();
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    long previousTimeinMilliseconds = 0L;
    long currentScore = 1L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_my);

        myButton = (Button)findViewById(R.id.myButton);
        levelText = (TextView)findViewById(R.id.myLevelText);
        highLevelText = (TextView)findViewById(R.id.highLevelID);
        scoreValue = (TextView) findViewById(R.id.scoreValue);

        //---set on click listeners on the buttons-----
        myButton.setOnClickListener(this);

        //getting preferences
        SharedPreferences prefs = this.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        highLevel = prefs.getInt("highScore", 0); //0 is the default value


        levelText.setText(Integer.toString(counter));
        highLevelText.setText(Integer.toString(highLevel));
        previousTimeinMilliseconds = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);

    }

    @Override
    protected void onStop() {
        super.onStop();  // Always call the superclass method first

        //get shared preferences
        //setting preferences
        SharedPreferences prefs = this.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("highScore", highLevel);
        editor.commit();

    }

    @Override
    public void onClick(View v) {

        if (v == myButton){
            counter++;
            levelText.setText("Level: " + Integer.toString(counter));
            if (counter > highLevel) {
                highLevel = counter;
                highLevelText.setText(Integer.toString(highLevel));
            }
        }

    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {


            timeInMilliseconds = SystemClock.uptimeMillis() - previousTimeinMilliseconds;
            previousTimeinMilliseconds = SystemClock.uptimeMillis();
            updatedTime = ((timeSwapBuff + timeInMilliseconds) / (1 + (counter / 100))) * 100;

            //long secs = (updatedTime / (1 + (counter/10))) * 100;
            currentScore = currentScore + (updatedTime)/ 1000;


            scoreValue.setText("Score: " + currentScore);

            customHandler.postDelayed(this, 10);
            /*if (timeInMilliseconds <= 0) {
                customHandler.removeCallbacks(updateTimerThread);
                //myButton.setEnabled(true);
                scoreValue.setText("" + 0 + ":"
                        + String.format("%02d", 0));
            }
            else{
                customHandler.postDelayed(this, 0);
            }*/
        }
    };


}