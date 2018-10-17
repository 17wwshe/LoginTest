package com.example.administratior.logintest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

//登录模块
public class MainActivity extends AppCompatActivity {
    private EditText ed_username,ed_pwd;
    private Button bt_sure,bt_cancel,bt_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginacitvity);
        Bmob.initialize(this, "c761a288d91442d97be69779fdd19ead");
        init();
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
                 BmobUser person=new BmobUser();
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
                 });
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

}
