package com.example.administratior.logintest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administratior on 2018/10/14.
 */

public class PersonActivity extends AppCompatActivity {

    private Button bt_out;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person);
        bt_out = (Button)findViewById(R.id.bt_out);
        bt_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(PersonActivity.this,MainActivity.class);
                startActivity(intent3);
                finish();
            }
        });
    }
}
