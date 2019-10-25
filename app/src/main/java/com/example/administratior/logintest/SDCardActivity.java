package com.example.administratior.logintest;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;

public class SDCardActivity extends AppCompatActivity implements View.OnClickListener {

    private Button sdCardBtSave;
    private Button sdCardBtJudge;
    private String TAG = "SDCardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sdcard);
        initView();
        initListener();
    }

    private void initView() {
        sdCardBtSave = (Button) findViewById(R.id.sdCardBtSave);
        sdCardBtJudge = (Button) findViewById(R.id.sdCardBtJudge);
    }

    private void initListener() {
        sdCardBtSave.setOnClickListener(this);
        sdCardBtJudge.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == sdCardBtSave){
            sdCardSave();

        }else
            if(v==sdCardBtJudge){
                sdCardJudge();
            }
    }

    private void sdCardSave() {
        File filePath = Environment.getExternalStorageDirectory();

        Log.d(TAG,"Ext-FilePath == "+filePath);
//        File filePath = new File("/storage/sdcard");
        File file = new File(filePath,"info.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write("god is watching you!".getBytes());
            fos.close();
            Log.d(TAG,"write successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void sdCardJudge() {
        String state = Environment.getExternalStorageState();
        switch (state) {
            case Environment.MEDIA_MOUNTED:
                Log.d(TAG, "SD卡已经挂载，即是可用");
                break;
            case Environment.MEDIA_UNMOUNTED:
                Log.d(TAG, "SD卡已经删除......");
                break;
            default:
                Log.d(TAG, "sdCard not found");
                break;
        }
    }

}
