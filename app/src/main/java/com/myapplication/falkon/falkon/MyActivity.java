package com.myapplication.falkon.falkon;

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
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_my);

        myButton = (Button)findViewById(R.id.myButton);
        scoreText = (TextView)findViewById(R.id.myTextView);

        //---set on click listeners on the buttons-----
        myButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == myButton){
            counter++;
            scoreText.setText(Integer.toString(counter));
        }

    }

}