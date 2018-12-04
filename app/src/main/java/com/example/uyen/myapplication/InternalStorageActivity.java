package com.example.uyen.myapplication;

import android.content.Context;
import android.renderscript.ScriptGroup;
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
import java.io.OutputStream;

public class InternalStorageActivity extends AppCompatActivity
        implements View.OnClickListener{
    private static final String TAG = "InternalStorageActivity" ;
    private final String FILE_NAME  = "DemoBroadcast";
    private final String CONTENT = " Nguyen Thi Hong Uyen";
    private Button mButtonSaveData;
    private Button mButtonReadData;
    private TextView mTextContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
        initView();
        registerListener();
    }

    public void initView(){
        mButtonSaveData = findViewById(R.id.button_save_internal);
        mButtonReadData = findViewById(R.id.button_read_internal);
        mTextContent = findViewById(R.id.text_content);
    }

    public void registerListener(){
        mButtonSaveData.setOnClickListener(this);
        mButtonReadData.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_save_internal:
                //saveData();
                saveDataByCache();
                break;
            case R.id.button_read_internal:
               // readData();
                readDataByCache();
                break;
            default:
                break;
        }
    }

    public void saveData(){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write(CONTENT.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "Save data successfully!", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDataByCache(){
        FileOutputStream fileOutputStream = null;
        File file;
        try {
            file = new File(getCacheDir(),FILE_NAME);
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(CONTENT.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "Save data on Cache !", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData(){
        try {
            FileInputStream input = openFileInput(FILE_NAME);
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            StringBuffer buffer = new StringBuffer();
            String line = null;
            while((line = br.readLine()) != null){
                buffer.append(line).append("\n");
            }

            mTextContent.setText(buffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readDataByCache(){
        try {
            File file = new File(getCacheDir(), FILE_NAME);
            FileInputStream input = openFileInput(FILE_NAME);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file)));
            String line;
            StringBuffer buffer = new StringBuffer();

            while ((line = br.readLine()) != null){
                buffer.append(line).append("\n");
            }
            Log.d(TAG,buffer.toString());
            mTextContent.setText(buffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
