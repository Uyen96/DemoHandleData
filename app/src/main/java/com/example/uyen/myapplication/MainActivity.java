package com.example.uyen.myapplication;

<<<<<<< HEAD
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mButtonShared;
=======
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

>>>>>>> develop
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        initView();
        registerListener();
    }

    private void initView() {
        mButtonShared = findViewById(R.id.button_shared_preferences);
    }

    public void registerListener(){
        mButtonShared.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_shared_preferences:
                Intent intent = new Intent(this,
                        SharedPreferencesActivity.class);
                startActivity(intent);
        }

=======
>>>>>>> develop
    }
}
