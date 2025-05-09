package com.estifatech.labexam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.estifatech.labexam.model.User;
import com.estifatech.labexam.screen.LoginActivity;
import com.estifatech.labexam.utils.DbHelper;

public class MainActivity extends AppCompatActivity {
    EditText uid, full_name, email, phone_number, address, portfolio_website,campus_name, password;
    Spinner university, year;
    AppCompatButton register;
    TextView txt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initViews();
        showSpinner();

        register.setOnClickListener(v -> registerUser());
        txt_login.setOnClickListener(v -> {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        });
    }

    private void registerUser() {
        String uid_text = uid.getText().toString();
        String full_name_text = full_name.getText().toString();
        String email_text = email.getText().toString();
        String phone_number_text = phone_number.getText().toString();
        String address_text = address.getText().toString();
        String portfolio_website_text = portfolio_website.getText().toString();
        String campus_name_text = campus_name.getText().toString();
        String password_text = password.getText().toString();
        String university_text = university.getSelectedItem().toString();
        String year_text = year.getSelectedItem().toString();

        if(uid_text.isEmpty() || full_name_text.isEmpty() || email_text.isEmpty() ||
                phone_number_text.isEmpty() || address_text.isEmpty() ||
                portfolio_website_text.isEmpty() || campus_name_text.isEmpty() ||
                password_text.isEmpty() || university_text.isEmpty() || year_text.isEmpty()){
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }
        else{
            User user = new User(uid_text,full_name_text,email_text,
                    phone_number_text,address_text,portfolio_website_text,university_text,campus_name_text,year_text,password_text);
            DbHelper dbHelper = new DbHelper(this);
            boolean registered = dbHelper.registerUser(user);
            if(registered){
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        }
    }

    private void initViews() {
        university = findViewById(R.id.university);
        year = findViewById(R.id.year);
        register = findViewById(R.id.btn_register);
        uid = findViewById(R.id.uid);
        full_name = findViewById(R.id.full_name);
        email = findViewById(R.id.email);
        phone_number = findViewById(R.id.phone_number);
        address = findViewById(R.id.address);
        portfolio_website = findViewById(R.id.portfolio_website);
        campus_name = findViewById(R.id.campus_name);
        password = findViewById(R.id.password);
        txt_login = findViewById(R.id.txt_login);
    }

    void showSpinner(){
        String [] university_list = {"Adama Science and Technology University","Addis Ababa Science and Technology University","Addis Ababa University","Debre Berhan University","Debre Markos University","Dire Dawa University","Gondar University","Harar University","Mekele University","Medela Amba University"};
        ArrayAdapter<String> universityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,university_list);
        university.setAdapter(universityAdapter);
        String [] year_list = {"1st Year","2nd Year","3rd Year","4th Year","5th Year"};
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,year_list);
        year.setAdapter(yearAdapter);
    }
}