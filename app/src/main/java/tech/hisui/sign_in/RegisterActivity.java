package tech.hisui.sign_in;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

public class RegisterActivity extends AppCompatActivity {
    private Boolean bPwdSwitch = false;
    private EditText etPwd;
    private EditText etAccount;
    private Button btRegister;
    private Spinner sp_job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        final ImageView ivPwdSwitch = findViewById(R.id.iv_pwd_switch);
        etPwd = findViewById(R.id.et_pwd);
        etAccount = findViewById(R.id.et_Account);
        btRegister = findViewById(R.id.bt_register);
        sp_job = findViewById(R.id.sp_job);


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

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = false;
                MysqlReg msu = new MysqlReg();
                if (etAccount.getText().toString().equals("") ||
                        etPwd.getText().toString().equals(""))
                    Toast.makeText(RegisterActivity.this,
                            "不得为空", Toast.LENGTH_SHORT).show();
                else {
                    try {
                        result = msu.execute(etAccount.getText().toString(), etPwd.getText().toString(),
                                String.valueOf(sp_job.getSelectedItemId())).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (result) {
                        Toast.makeText(RegisterActivity.this, "Succeed!",
                                Toast.LENGTH_SHORT).show();
                        RegisterActivity.this.finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Error!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}