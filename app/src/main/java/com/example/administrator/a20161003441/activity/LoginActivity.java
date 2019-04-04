package com.example.administrator.a20161003441.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.a20161003441.utils.AppConfig;
import com.example.administrator.a20161003441.R;

public class LoginActivity extends AppCompatActivity {

    EditText userid, password;
    TextView log_info;
    Button login_ok;
    CheckBox cachPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        loadStatus();
        initInteract();
    }

    private void initView(){
        userid =  this.findViewById(R.id.et_username);
        password =  this.findViewById(R.id.et_psw);
        log_info=this.findViewById(R.id.tv_err);
        login_ok =  this.findViewById(R.id.bt_login);
        cachPwd = this.findViewById(R.id.cb_remember_pwd);
    }

    private void loadStatus(){
        if(AppConfig.checkAccountCachedStatus(LoginActivity.this)){
            userid.setText(AppConfig.getCachedAccount(LoginActivity.this));
            password.setText(AppConfig.getCachedPwd(LoginActivity.this));
            cachPwd.setChecked(true);
        }
    }

    private void initInteract(){
        login_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userid.getText().toString().trim().equals("gduf")
                        && password.getText().toString().equals("welcome")) {
                    if(cachPwd.isChecked()){
                        AppConfig.cacheToken(LoginActivity.this,userid.getText().toString().trim(),password.getText().toString());
                    }else {
                        AppConfig.removeToken(LoginActivity.this);
                    }
                    log_info.setText("登录成功！");
                    Bundle bundle = new Bundle();
                    bundle.putString("userid", userid.getText().toString());  //键值对
                    Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                    intent1.putExtras(bundle);//加载参数
                    startActivity(intent1);//启动另一activity
                    finish();
                }
                else{
                    log_info.setText("登录失败！");
                }
            }
        });
    }

    public void clickLogin(View target){
        Button button=  (Button)target;
        button.setText("success");
    }

    //回调事件，无需定义及注册
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x=event.getX();
        float y=event.getY();
        Toast.makeText(this, "触摸了"+x+","+y, Toast.LENGTH_SHORT).show();
        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                break;
            case (MotionEvent.ACTION_MOVE):
                break;
            case (MotionEvent.ACTION_UP):
                break;
        }
        return super.onTouchEvent(event);
    }
}
