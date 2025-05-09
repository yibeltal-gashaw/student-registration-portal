package com.estifatech.labexam.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.estifatech.labexam.model.User;


public class DbHelper extends SQLiteOpenHelper {
    public static String DBNAME = "users.db";
    public static int DB_VERSION = 1;
    Context context;
    public DbHelper(Context context){
        super(context, DBNAME,null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE user (" +
                "UID TEXT PRIMARY KEY," +
                "FULL_NAME TEXT," +
                "EMAIL TEXT," +
                "PHONE_NUMBER TEXT," +
                "ADDRESS TEXT," +
                "PORTFOLIO_WEBSITE TEXT," +
                "UNIVERSITY TEXT," +
                "CAMPUS_NAME TEXT," +
                "YEAR TEXT," +
                "PASSWORD TEXT );";
        sqLiteDatabase.execSQL(query);

    }
    public boolean registerUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // Check if user with this UID already exists
        Cursor cursor = db.rawQuery("SELECT UID FROM user WHERE UID = ?", new String[]{user.getId()});
        if (cursor.moveToFirst()) {
            Toast.makeText(context, "User with this ID already exists", Toast.LENGTH_SHORT).show();
            cursor.close();
            return false;
        }

        cursor.close();

        cv.put("UID",user.getId());
        cv.put("FULL_NAME",user.getFillName());
        cv.put("EMAIL",user.getEmail());
        cv.put("PHONE_NUMBER",user.getPhoneNumber());
        cv.put("ADDRESS",user.getAddress());
        cv.put("PORTFOLIO_WEBSITE",user.getPortfolioWebsite());
        cv.put("UNIVERSITY",user.getUniversity());
        cv.put("CAMPUS_NAME",user.getCampusName());
        cv.put("YEAR",user.getYear());
        cv.put("PASSWORD",user.getPassword());
        long result = db.insert("user",null,cv);
        if(result == -1){
            Toast.makeText(context, "Registration Failed", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public String getUserPassword(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT PASSWORD FROM user WHERE UID = ?";
        String password = null;
        Cursor cursor = db.rawQuery(query,new String[]{id});
        if(cursor.moveToFirst()){
            password = cursor.getString(cursor.getColumnIndexOrThrow("PASSWORD"));
            cursor.close();
            return password;
        }
        cursor.close();
        return  null;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user");
        onCreate(sqLiteDatabase);
    }

    public User getUser(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM user WHERE UID = ?";
        Cursor cursor = db.rawQuery(query,new String[]{id});
        if(cursor.moveToFirst()){
            String full_name = cursor.getString(cursor.getColumnIndexOrThrow("FULL_NAME"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("EMAIL"));
            String phone_number = cursor.getString(cursor.getColumnIndexOrThrow("PHONE_NUMBER"));
            String address = cursor.getString(cursor.getColumnIndexOrThrow("ADDRESS"));
            String portfolio_website = cursor.getString(cursor.getColumnIndexOrThrow("PORTFOLIO_WEBSITE"));
            String university = cursor.getString(cursor.getColumnIndexOrThrow("UNIVERSITY"));
            String campus_name = cursor.getString(cursor.getColumnIndexOrThrow("CAMPUS_NAME"));
            String year = cursor.getString(cursor.getColumnIndexOrThrow("YEAR"));
            String password = cursor.getString(cursor.getColumnIndexOrThrow("PASSWORD"));
            cursor.close();
            return new User(id,full_name,email,phone_number,address,portfolio_website,university,campus_name,year,password);
        }
        cursor.close();
        return null;
    }

    public boolean updateUserPassword(String id, String string) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("PASSWORD",string);
        int updated = db.update("user", cv, "UID = ?", new String[]{id});
        return updated > 0;
    }
    public boolean deleteUser(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        int deleted = db.delete("user","UID = ?",new String[]{id});
        return deleted > 0;
    }
}
