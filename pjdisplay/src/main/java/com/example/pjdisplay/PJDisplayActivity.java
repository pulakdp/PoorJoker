package com.example.pjdisplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class PJDisplayActivity extends AppCompatActivity {

    public static final String JOKE_TAG = "PoorJoke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pjdisplay);

        displayPJ();
    }

    public void displayPJ() {
        String joke = getIntent().getStringExtra(JOKE_TAG);
        ((TextView) findViewById(R.id.pj_textview))
                .setText(joke != null && joke.length() != 0 ? joke : "Oops! Something went wrong");
    }
}
