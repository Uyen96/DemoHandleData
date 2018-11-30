package com.example.uyen.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SharedPreferencesActivity extends AppCompatActivity
        implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();
    private final String SHARED_PREFERENCES_NAME = "Person";
    private final String NAME = " my_name";
    private final String AGE = "age";
    private final String IS_SINGLE = "is_single";
    private final String WEIGHT = "weight";

    private Button mButtonSave;
    private Button mButtonRead;
    private Button mButtonRemoveByKey;
    private Button mButtonRemoveAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        initView();
        registerListener();
    }

    private void initView() {
        mButtonSave = findViewById(R.id.button_save);
        mButtonRead = findViewById(R.id.button_read);
        mButtonRemoveByKey = findViewById(R.id.button_remove_by_key);
        mButtonRemoveAll = findViewById(R.id.button_remove_all);
    }

    private void registerListener() {
        mButtonSave.setOnClickListener(this);
        mButtonRead.setOnClickListener(this);
        mButtonRemoveByKey.setOnClickListener(this);
        mButtonRemoveAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_save:
                saveData();
                break;
            case R.id.button_read:
                readData();
                break;
            case R.id.button_remove_by_key:
                removeByKey(NAME);
                break;
            case R.id.button_remove_all:
                removeAll();
                break;
            default:
                break;
        }

    }

    public void saveData() {
        SharedPreferences sharedPreferences =
                getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NAME, "Nguyen Thi A");
        editor.putInt(AGE, 22);
        editor.putBoolean(IS_SINGLE, false);
        editor.putLong(WEIGHT, 47);
        editor.apply();
        Toast.makeText(this, "Save success!", Toast.LENGTH_SHORT).show();
    }

    public void readData(){
        SharedPreferences sharedPreferences =
                getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(NAME, "null");
        int age = sharedPreferences.getInt(AGE, 0);
        boolean isSingle = sharedPreferences.getBoolean(IS_SINGLE, false);
        long weight = sharedPreferences.getLong(WEIGHT, 0);
        String address = sharedPreferences.getString("ADDRESS", "Ha Noi");
        Log.d(TAG,"Nguoi: " + "Name :" +name+"\nAge: "+age+
                "\nIs single : "+ isSingle+"\nweight:"+weight+"\naddress:"+address);
    }

    public void removeByKey(String key){
        SharedPreferences sharedPreferences =
                getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public void removeAll(){
        SharedPreferences sharedPreferences =
                getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}

