package com.example.administratior.logintest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administratior on 2018/10/14.
 */
//注册模块
public class RegisterActivity extends AppCompatActivity {

    private Context context;
    EditText ed_username,ed_pwd,ed_phone,ed_email;
    Button bt_sure,bt_cancel,bt_login;
    BmobUser person;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        context = this;
        init();
    }
    private void init()
    {
        ed_username = (EditText)findViewById(R.id.Red_username) ;
        ed_pwd = (EditText)findViewById(R.id.Red_pwd) ;
        ed_phone = (EditText)findViewById(R.id.Red_phone);
        ed_email = (EditText)findViewById(R.id.Red_email);
        bt_sure = (Button)findViewById(R.id.Rbt_sure);
        bt_cancel = (Button)findViewById(R.id.Rbt_cancel);
        bt_login = (Button)findViewById(R.id.Rbt_login) ;

        bt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = ed_username.getText().toString();
                String pwd = ed_pwd.getText().toString();
                String phone = ed_phone.getText().toString();
                String email = ed_email.getText().toString();

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)){
                    Toast.makeText(context, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                person = new BmobUser();
                person.setUsername(name);
                person.setPassword(pwd);
                person.setMobilePhoneNumber(phone);
                person.setEmail(email);
                person.signUp(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser person, BmobException e) {
                        if(e==null)
                        {
                            Log.d("signUp","success");
                            Toast.makeText(context,"注册成功",Toast.LENGTH_LONG).show();
                            Intent intent2 = new Intent(RegisterActivity.this,PersonActivity.class);
                            startActivity(intent2);
                            finish();
                        }
                        else
                        {
                            Log.d("signUp","success");
                            Toast.makeText(context,"注册失败"+e.getMessage(),Toast.LENGTH_LONG).show();
                            ed_username.setText("");
                            ed_pwd.setText("");
                            ed_phone.setText("");
                            ed_email.setText("");
                        }
                    }
                });



            }
        });


        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent5);
                finish();
            }
        });
    }

}
