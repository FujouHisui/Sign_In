package tech.hisui.sign_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {


    private Boolean bPwdSwitch = false;
    private EditText etPwd;
    private EditText etAccount;
    private CheckBox cbRemember_pwd;
    private Button btLogin;
    private Spinner spSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_login);

        final ImageView ivPwdSwitch = findViewById(R.id.iv_pwd_switch);
        etPwd = findViewById(R.id.et_pwd);
        etAccount = findViewById(R.id.et_Account);
        cbRemember_pwd = findViewById(R.id.cbRemember_pwd);
        btLogin = findViewById(R.id.bt_login);
        spSwitch = findViewById(R.id.spJob);


        ivPwdSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bPwdSwitch = !bPwdSwitch;
                if (bPwdSwitch){
                    ivPwdSwitch.setImageResource(R.drawable.ic_baseline_visibility_24);
                    etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                else {
                    etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    etPwd.setTypeface(Typeface.DEFAULT);
                }
            }

        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLoggedIn = false;
                MysqlLogin mss = new MysqlLogin();
                try {
                    isLoggedIn = mss.execute(etAccount.getText().toString(),
                            etPwd.getText().toString()).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                if (isLoggedIn) {
                    Toast.makeText(LoginActivity.this, "Logged in!",
                            Toast.LENGTH_SHORT).show();
                    if (spSwitch.getSelectedItemId() == 0){
                        Intent intent=new Intent(   LoginActivity.this,
                                TeacherActivity.class);
                        startActivity(intent);
                    }else {
                        Intent intent=new Intent(   LoginActivity.this,
                                StudentListActivity.class);
                        startActivity(intent);
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, "Error!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}