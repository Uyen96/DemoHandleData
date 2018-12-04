package com.example.uyen.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.uyen.myapplication.sqlite.SqliteActivity;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    private Button mButtonShared;
    private Button mButtonInternal;
    private Button mButtonExternal;
    private Button mButtonSqLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        registerListener();
    }

    private void initView() {
        mButtonShared = findViewById(R.id.button_shared_preferences);
        mButtonInternal = findViewById(R.id.button_internal);
        mButtonExternal = findViewById(R.id.button_external);
        mButtonSqLite = findViewById(R.id.button_sqlite);
    }

    public void registerListener(){
        mButtonShared.setOnClickListener(this);
        mButtonInternal.setOnClickListener(this);
        mButtonExternal.setOnClickListener(this);
        mButtonSqLite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_shared_preferences:
                Intent intent = new Intent(this,
                        SharedPreferencesActivity.class);
                startActivity(intent);
                break;
            case R.id.button_internal:
                Intent intentInternal = new Intent(this,
                        InternalStorageActivity.class);
                startActivity(intentInternal);
                break;
            case R.id.button_external:
                Intent intentExternal = new Intent(this,
                        ExternalActivity.class);
                startActivity(intentExternal);
                break;
            case R.id.button_sqlite:
                Intent intentSqlite = new Intent(this,
                        SqliteActivity.class);
                startActivity(intentSqlite);
                break;
            default:
                break;
        }
    }
}
