package tech.hisui.sign_in;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private Button btLogin;
    private EditText etPwd;
    private EditText etAccount;
    private CheckBox cbRememberPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_student);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }




        etPwd=findViewById(R.id.et_pwd);
        etAccount=findViewById(R.id.et_Account);
        cbRememberPwd=findViewById(R.id.cbRemember_pwd);
        btLogin=findViewById(R.id.bt_login);//登录界面

        btLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String spFileName = getResources().getString(R.string.shared_preference_file_name);
                String accountKey = getResources().getString(R.string.login_account_name);
                String passwordKey = getResources().getString(R.string.login_password);
                String rememberPasswordKey = getResources().getString(R.string.login_remember_password);
                SharedPreferences spFile = getSharedPreferences(spFileName, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = spFile.edit();
                if (cbRememberPwd.isChecked()) {
                    String password = etPwd.getText().toString();
                    String account = etAccount.getText().toString();
                    editor.putString(accountKey, account);
                    editor.putString(passwordKey, password);
                    editor.putBoolean(rememberPasswordKey, true);
                    editor.apply();
                } else {
                    editor.remove(accountKey);
                    editor.remove(passwordKey);
                    editor.remove(rememberPasswordKey);
                    editor.apply();
                }

            }
        }
        );
       btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //对于下面的两个参数，分别代表本页面和跳转页面的参数，不过要注意本页面的是用this，要跳转到的页面是class
                Intent intent=new Intent(   LoginActivity.this,TeacherActivity.class);
                startActivity(intent);//改。这里仅仅是跳转到老师界面
            }
        });


        String spFileName=getResources().getString(R.string.shared_preference_file_name);
        String accountKey=getResources().getString(R.string.login_account_name);
        String passwordKey=getResources().getString(R.string.login_password);
        String rememberPasswordKey=getResources().getString(R.string.login_remember_password);
        SharedPreferences spFile=getSharedPreferences(spFileName, Context.MODE_PRIVATE);
        String account=spFile.getString(accountKey,null);
        String password=spFile.getString(passwordKey,null);
        boolean rememberPassword=spFile.getBoolean(rememberPasswordKey,false);

        if(account!=null&&!TextUtils.isEmpty(account)){
            etAccount.setText(account);
        }

        if(password!=null&&!TextUtils.isEmpty(password)){
            etPwd.setText(password);
        }
        cbRememberPwd.setChecked(rememberPassword);




    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}