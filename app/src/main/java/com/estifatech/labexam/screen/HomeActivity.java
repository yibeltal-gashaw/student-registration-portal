package com.estifatech.labexam.screen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.estifatech.labexam.R;
import com.estifatech.labexam.model.User;
import com.estifatech.labexam.utils.DbHelper;
import com.estifatech.labexam.utils.Prefs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {
    TextView txt_full_name,txt_email,txt_phone_number,txt_address,txt_portfolio_website,txt_university,txt_campus_name,txt_year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("User Profile");
        setSupportActionBar(toolbar);
        txt_full_name = findViewById(R.id.name);
        txt_email = findViewById(R.id.email);
        txt_phone_number = findViewById(R.id.phone);
        txt_address = findViewById(R.id.address);
        txt_portfolio_website = findViewById(R.id.website);
        txt_university = findViewById(R.id.university);
        txt_year = findViewById(R.id.year);
        txt_campus_name = findViewById(R.id.campus_name);

        DbHelper dbHelper = new DbHelper(this);
        Prefs prefs = new Prefs(this);
        String id = prefs.getUid();
        if(id == null){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }{
            User user = dbHelper.getUser(id);
            if(user != null){
                txt_full_name.setText(user.getFillName());
                txt_email.setText(String.format("Email: %s",user.getEmail()));
                txt_phone_number.setText(String.format("Phone: %s",user.getPhoneNumber()));
                txt_address.setText(user.getAddress());
                txt_portfolio_website.setText(String.format("Portfolio Website: %s",user.getEmail()));
                txt_university.setText(String.format("🏢 %s",user.getUniversity()));
                txt_campus_name.setText(String.format("Campus: %s", user.getCampusName()));
                txt_year.setText(String.format("Year: %s", user.getYear()));
            }
        }
        FloatingActionButton fab = findViewById(R.id.btn_change_password);
        fab.setOnClickListener(v->startActivity(new Intent(this, ChangeActivity.class)));
    }
}