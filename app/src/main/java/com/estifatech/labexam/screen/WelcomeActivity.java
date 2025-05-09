package com.estifatech.labexam.screen;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.estifatech.labexam.MainActivity;
import com.estifatech.labexam.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);
        AppCompatButton startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(v ->
                startActivity(new Intent(this, MainActivity.class)));
    }
}