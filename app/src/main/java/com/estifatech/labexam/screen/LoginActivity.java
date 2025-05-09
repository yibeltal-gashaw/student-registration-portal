package com.estifatech.labexam.screen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.estifatech.labexam.MainActivity;
import com.estifatech.labexam.R;
import com.estifatech.labexam.utils.DbHelper;
import com.estifatech.labexam.utils.Prefs;

public class LoginActivity extends AppCompatActivity {
    EditText uid, password;
    TextView txt_create_account;
    AppCompatButton login;
    Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        uid = findViewById(R.id.uid);
        password = findViewById(R.id.password);
        txt_create_account = findViewById(R.id.txt_create_account);
        login = findViewById(R.id.btn_login);
        prefs = new Prefs(this);
        DbHelper dbhelper = new DbHelper(this);

        login.setOnClickListener(v -> {
            String id = uid.getText().toString();
            String pass = password.getText().toString();

            if (!id.isEmpty() && !pass.isEmpty()){
                String user_password = dbhelper.getUserPassword(id);
                if(user_password == null){
                    Toast.makeText(this, "User with this id not found", Toast.LENGTH_SHORT).show();
                }else {
                    if (pass.equals(user_password)){
                        prefs.saveUid(id);
                        startActivity(new Intent(this,HomeActivity.class));
                    }
                    else {
                        Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

        txt_create_account.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}