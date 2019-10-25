package com.example.administratior.logintest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import cn.bmob.v3.Bmob;

//登录模块
public class MainActivity extends AppCompatActivity {
    private EditText ed_username,ed_pwd;
    private Button bt_sure,bt_cancel,bt_register;
    private String account=null, password=null;
    /*
        *即使设置了全局变量，在onDestroy的时候也会清空；
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginacitvity);
        Bmob.initialize(this, "c761a288d91442d97be69779fdd19ead");
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // File file = new File(this.getFilesDir(),"loginInformation.txt");
        try {
            FileInputStream fileInputStream = this.openFileInput("loginInformation.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String info = bufferedReader.readLine();
            // fos.write((account+"***"+password).getBytes());
            //保存数据的格式，所以我们拿到数据之后，要对数据进行切割；
            String[] splits = info.split("\\*\\*\\*");
            account = splits[0];
            password = splits[1];
            //回显数据
            ed_username.setText(account);
            ed_pwd.setText(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void init()
    {
        ed_username = (EditText) findViewById(R.id.ed_username);
        ed_pwd = (EditText)findViewById(R.id.ed_pwd);
        bt_sure = (Button)findViewById(R.id.bt_sure);
        bt_cancel = (Button)findViewById(R.id.bt_cancel);
        bt_register = (Button)findViewById(R.id.bt_register);
        bt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //登录验证
                 /*BmobUser person=new BmobUser();
                 person.setUsername(ed_username.getText().toString());
                 person.setPassword(ed_pwd.getText().toString());
                 person.login(new SaveListener<BmobUser>() {
                     @Override public void done(BmobUser person, BmobException e)
                        {
                            if(e==null)
                                {
                                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                                    Intent intent1_3 = new Intent(MainActivity.this,PersonActivity.class);
                                    startActivity(intent1_3);
                                    finish();
                                }
                            else
                                {
                                    Toast.makeText(MainActivity.this,"登录失败"+e.getMessage(),Toast.LENGTH_LONG).show();
                                    ed_pwd.setText("");
                                }
                        }
                 });*/
                account = ed_username.getText().toString();
                password = ed_pwd.getText().toString();
                if(account.length()==0||password.length()==0)
                    Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    saveInformation(account,password);
                }
            }
        });

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent4);
                finish();
            }
        });
    }

    private void saveInformation(String account, String password){
        Log.d("MainActivity","保存信息......");
        /*
            * File file = new File("./data/data/com.example.administratior.logintest/loginInformation.txt");
            * 用此方法书写的地址，太长，容易出错，下面的方法更为准确简洁；
         */
        File file = new File(this.getFilesDir(),"loginInformation.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write((account+"***"+password).getBytes());
            fos.close();
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

}

