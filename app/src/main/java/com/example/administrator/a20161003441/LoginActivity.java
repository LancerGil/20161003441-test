package com.example.administrator.a20161003441;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText userid, password;
    TextView log_info;
    Button login_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userid =  this.findViewById(R.id.et_username);
        password =  this.findViewById(R.id.et_psw);
        log_info=this.findViewById(R.id.tv_err);
        login_ok =  this.findViewById(R.id.bt_login);
        login_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userid.getText().toString().trim().equals("gduf")
                        && password.getText().toString().equals("welcome")) {
                    log_info.setText("登录成功！");
                }
                else{
                    log_info.setText("登录失败！");
                }
            }
        });

    }
}
