package com.myapplication.falkon.falkon;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
    TextView scoreText;
    TextView highScoreText;
    int counter = 0;
    int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_my);

        myButton = (Button)findViewById(R.id.myButton);
        scoreText = (TextView)findViewById(R.id.myTextView);
        highScoreText = (TextView)findViewById(R.id.highScoreID);

        //---set on click listeners on the buttons-----
        myButton.setOnClickListener(this);



        //getting preferences
        SharedPreferences prefs = this.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        highScore = prefs.getInt("highScore", 0); //0 is the default value


        scoreText.setText(Integer.toString(counter));
        highScoreText.setText(Integer.toString(highScore));


    }

    @Override
    protected void onStop() {
        super.onStop();  // Always call the superclass method first

        //get shared preferences
        //setting preferences
        SharedPreferences prefs = this.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("highScore", highScore);
        editor.commit();

    }

    @Override
    public void onClick(View v) {
        if (v == myButton){
            counter++;
            scoreText.setText(Integer.toString(counter));
            if (counter > highScore) {
                highScore = counter;
                highScoreText.setText(Integer.toString(highScore));
            }
        }

    }

}