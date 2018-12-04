package com.example.uyen.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ExternalActivity extends AppCompatActivity implements
        View.OnClickListener {

    private final String fileName = "External Storage";
    private final String content = "Nguyen Thi Hong Uyen";
    private final String TAG = getClass().getSimpleName();
    private Button mButtonSaveEx;
    private Button mButtonReadeEx;
    private TextView mTextContentEx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external);
        initView();
        registerListener();
        checkAndRequestPermissions();
    }

    public void initView(){
        mButtonSaveEx = findViewById(R.id.button_save_external);
        mButtonReadeEx = findViewById(R.id.button_read_external);
        mTextContentEx = findViewById(R.id.text_content_external);
    }
    public void registerListener(){
        mButtonSaveEx.setOnClickListener(this);
        mButtonReadeEx.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_save_external:
                //TODO
                //saveData();
                saveDataInDownload();
                break;
            case R.id.button_read_external:
                //TODO
                readData();
                break;
            default:
                break;
        }
    }

    public void saveData(){
        FileOutputStream fileOutputStream;
        File file;
        file = new File(Environment.getExternalStorageDirectory(), fileName);
        Log.d(TAG,"save Data : "+ Environment.getExternalStorageDirectory());
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "Save data successfully!", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDataInDownload(){
        FileOutputStream fileOutputStream;
        File file;
        file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), fileName);
        Log.d(TAG,"save Data : "+ Environment.getExternalStorageDirectory());
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "Save data successfully!", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData(){
        if(isExternalStorageReadable()){
            BufferedReader input = null;
            File file = null;
            try {
                file = new File(Environment.getExternalStorageDirectory(), fileName);

                input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String line;
                StringBuffer buffer = new StringBuffer();
                while ((line = input.readLine()) != null) {
                    buffer.append(line);
                }
                mTextContentEx.setText(buffer.toString());
                Log.d(TAG, buffer.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            Toast.makeText(this, "Can't save file", Toast.LENGTH_SHORT).show();
        }

    }

    private void checkAndRequestPermissions() {
        String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
        }
    }


    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
