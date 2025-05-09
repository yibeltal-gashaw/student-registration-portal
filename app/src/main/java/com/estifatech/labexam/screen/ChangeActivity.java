package com.estifatech.labexam.screen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import com.estifatech.labexam.R;
import com.estifatech.labexam.utils.DbHelper;
import com.estifatech.labexam.utils.Prefs;

public class ChangeActivity extends AppCompatActivity {
    AppCompatButton btn_change_password,btn_delete_user;
    EditText current_password,new_password,confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change);
        Toolbar toolbar = findViewById(R.id.toolbar);
        btn_change_password = findViewById(R.id.change_password);
        btn_delete_user = findViewById(R.id.delete_user);
        new_password = findViewById(R.id.new_password);
        confirm_password = findViewById(R.id.confirm_password);
        current_password = findViewById(R.id.current_password);
        Prefs prefs = new Prefs(this);

        DbHelper dbHelper = new DbHelper(this);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Change Password");
        }
        // Handle back arrow click (navigate up)
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        btn_change_password.setOnClickListener(v->{
            String id = prefs.getUid();
            String currentPassword = dbHelper.getUserPassword(id);
            if(new_password.getText().toString().equals(confirm_password.getText().toString())){
                if(current_password.getText().toString().equals(currentPassword)){
                    boolean updated = dbHelper.updateUserPassword(id,new_password.getText().toString());
                    if(updated){
                        Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this,LoginActivity.class));
                        finish();
                    }else{
                        Toast.makeText(this, "Failed to update password", Toast.LENGTH_SHORT).show();
                    }
                }
            }else{
                Toast.makeText(this, "Password not match", Toast.LENGTH_SHORT).show();
            }
        });
        btn_delete_user.setOnClickListener(v->{
            String id = prefs.getUid();
            boolean deleted = dbHelper.deleteUser(id);
            if (deleted){
                Toast.makeText(this, "User deleted successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,LoginActivity.class));
                finish();
            } else{
                Toast.makeText(this, "Failed to delete user", Toast.LENGTH_SHORT).show();
            }
        });
    }
}